package com.superit.smart.qa.core;

import com.superit.smart.qa.core.playwright.Configuration;
import com.superit.smart.qa.core.playwright.PlaywrightWrapper;
import com.superit.smart.qa.reporting.pojo.TestResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.extension.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.superit.smart.qa.utils.StringUtils.buildFileNameSuffixFromTestName;
import static com.superit.smart.qa.utils.StringUtils.isValidEmail;

@Slf4j
public class PWContextExtension implements BeforeEachCallback, AfterEachCallback, AfterAllCallback, TestWatcher {
    private final List<TestResult> testResults = new ArrayList<>();
    private String testName;

    @Override
    public void beforeEach(ExtensionContext context) {
        this.testName = buildFileNameSuffixFromTestName(context.getDisplayName());
        PlaywrightWrapper.initTestContext(testName);
    }

    @Override
    public void afterEach(ExtensionContext context) {
        PlaywrightWrapper.takeScreenshotOnTestFail(context, testName);
        PlaywrightWrapper.takePageContentOnTestFail(context, testName);
        PlaywrightWrapper.closeContext(testName);
        PlaywrightWrapper.deleteTraceFilesOnTestPass(context, testName);

        buildTestOwnerData(context);
    }

    @Override
    public void afterAll(ExtensionContext extensionContext) {
        writeTestOwnerDataTo(testResults, Configuration.OWNER_DATA_FILE_PATH);
    }


    private void buildTestOwnerData(ExtensionContext context) {
        String displayName = context.getDisplayName();
        Optional<Throwable> exception = context.getExecutionException();

        if (exception.isPresent()) {
            String owner = getOwnerEmailFrom(context.getTags()).orElse("undefined");
            String className = context.getRequiredTestClass().getName();

            TestResult result = new TestResult(className, displayName, owner);
            testResults.add(result);
        }
    }

    private void writeTestOwnerDataTo(List<TestResult> testResults, @NotNull String filePath) {
        try (FileWriter out = new FileWriter(filePath, true);
             CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT)) {
            for (TestResult result : testResults) {
                //Note. Add quotes to display name, since it may contain commas.
                printer.printRecord(result.getClassName(), "\"" + result.getDisplayName() + "\"", result.getOwner());
            }
        } catch (IOException e) {
            log.error("Can't write test owner data to file: {}", filePath);
        }
    }

    private Optional<String> getOwnerEmailFrom(Set<String> tags) {
        log.debug("Getting owner from @Tag");
        for (String tag : tags) {
            if (isValidEmail(tag)) {
                return Optional.of(tag);
            }
        }

        return Optional.ofNullable(null);
    }
}
