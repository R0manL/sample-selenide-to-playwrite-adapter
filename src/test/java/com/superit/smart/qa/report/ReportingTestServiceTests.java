package com.superit.smart.qa.report;

import com.superit.smart.qa.core.playwright.Configuration;
import com.superit.smart.qa.reporting.pojo.TestResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class ReportingTestServiceTests {
    private static final String REPORT_PREFIX = "TEST-";

    @Tag("ui")
    @Tag("smartbox")
    @Tag("add_owner")
    @Test
    void addOwnerToJunitXMLReports() {
        Path reportXML = Paths.get(Configuration.OWNER_DATA_FILE_PATH);

        if (Files.exists(reportXML) && Files.isRegularFile(reportXML)) {
            List<TestResult> testResults = readTestsOwnerDataCsvFile(reportXML);

            getListOfFilesStartWith(REPORT_PREFIX, Paths.get(Configuration.ATTACHMENT_DIR))
                    .forEach(path -> {
                        try {
                            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                            DocumentBuilder builder = factory.newDocumentBuilder();

                            File file = path.toFile();
                            log.debug("Parse Junit report xml file: {}", path);

                            // Parse the XML file
                            Document doc = builder.parse(file);

                            // Normalize the XML structure
                            doc.getDocumentElement().normalize();

                            // Get all <testcase> elements
                            NodeList testCaseList = doc.getElementsByTagName("testcase");

                            // Loop through the <testcase> elements to find the unique name
                            for (int i = 0; i < testCaseList.getLength(); i++) {
                                Node testCaseNode = testCaseList.item(i);

                                if (testCaseNode.getNodeType() == Node.ELEMENT_NODE) {
                                    Element testCaseElement = (Element) testCaseNode;
                                    String className = testCaseElement.getAttribute("classname");
                                    String testCaseName = testCaseElement.getAttribute("name");

                                    testResults
                                            .stream()
                                            .filter(testResult ->
                                                    className.equals(testResult.getClassName())
                                                            && testCaseName.contains(testResult.getDisplayName().replace("\"", "")))
                                            .findFirst()
                                            .ifPresentOrElse(
                                                    testResult -> testCaseElement.setAttribute("owner", testResult.getOwner()),
                                                    () -> log.error("Can't find owner record for test: '{}' '{}'", className, testCaseName));
                                } else {
                                    log.warn("No test case element has been found in node: {}\nDetails: {}", i, testCaseNode);
                                }
                            }

                            // Save the updated XML to file
                            TransformerFactory transformerFactory = TransformerFactory.newInstance();
                            Transformer transformer = transformerFactory.newTransformer();
                            DOMSource domSource = new DOMSource(doc);
                            StreamResult streamResult = new StreamResult(file);
                            transformer.transform(domSource, streamResult);

                            log.debug("Owner value has been added to test report: {}", file.getName());
                        } catch (Exception e) {
                            throw new IllegalStateException("Can't add owner tag to test report file.\nError: " + e.getMessage());
                        }
                    });
        } else {
            log.warn("Owner data does not exist: {}", reportXML);
        }
    }

    private Set<Path> getListOfFilesStartWith(@NotNull String prefix, Path dir) {
        log.debug("Get list of files with prefix: {} in dir: {}", prefix, dir);
        try (Stream<Path> files = Files.list(dir)) {
            return files
                    .filter(path -> path.getFileName().toString().startsWith(prefix))
                    .collect(Collectors.toSet());
        } catch (IOException e) {
            throw new IllegalStateException("Can't read a list of files in directory:" + dir + "\nError: " + e.getMessage());
        }
    }

    private List<TestResult> readTestsOwnerDataCsvFile(Path filePath) {
        log.debug("Read test owner data from csv file: {}", filePath.toString());
        List<TestResult> testResults = new ArrayList<>();

        try {
            File file = filePath.toFile();
            FileReader reader = new FileReader(file);
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);

            for (CSVRecord csvRecord : csvParser) {
                String className = csvRecord.get(0);
                String displayName = csvRecord.get(1);
                String owner = csvRecord.get(2);

                TestResult testResult = new TestResult(className, displayName, owner);
                testResults.add(testResult);
            }
        } catch (IOException e) {
           throw new IllegalStateException("Can't read test owner data from csv file.\nError: " + e.getMessage());
        }

        return testResults;
    }
}
