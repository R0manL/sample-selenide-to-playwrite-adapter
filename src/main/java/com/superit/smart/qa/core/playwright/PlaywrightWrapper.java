package com.superit.smart.qa.core.playwright;

import com.superit.smart.qa.ui.smartbox.pages.MainPage;
import com.microsoft.playwright.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;

import static com.superit.smart.qa.configs.EnvConfig.ENV_CONFIG;
import static com.superit.smart.qa.utils.FileUtils.deleteAllFilesWith;
import static com.superit.smart.qa.utils.FileUtils.writeToFile;

@Slf4j
public class PlaywrightWrapper {

    private static final ConcurrentHashMap<Long, PWContainer> pw = new ConcurrentHashMap<>();

    public static PWContainer pw() {
        return pw.computeIfAbsent(Thread.currentThread().getId(), k -> {
            var playwright = Playwright.create();
            var browser = playwright.chromium().launch(
                    new BrowserType.LaunchOptions()
                            .setHeadless(Configuration.HEADLESS)
                            .setTimeout(Configuration.BROWSER_TO_START_TIMEOUT)
                            .setSlowMo(Configuration.SLOW_MOTION_DELAY)
                            .setTracesDir(Paths.get(Configuration.ATTACHMENT_DIR))
            );
            return new PWContainer(null, null, playwright, browser);
        });
    }

    public static void close() {
        log.debug("Close playwright.");
        long threadId = Thread.currentThread().getId();

        if (pw.containsKey(threadId)) {
            pw.get(threadId).getPage().close();
            pw.get(threadId).getContext().close();
            pw.get(threadId).getBrowser().close();
            pw.get(threadId).getPlaywright().close();
            pw.remove(threadId);
        }

    }

    public static void initTestContext(String testName) {
        log.debug("Initiate test context");
        var newContextOptions = new Browser.NewContextOptions();
        newContextOptions.baseURL = Configuration.BASE_URL;
        newContextOptions.setViewportSize(Configuration.BROWSER_WIDTH, Configuration.BROWSER_HEIGHT);

        var pw = pw();
        var browserContext = pw.getBrowser().newContext(newContextOptions);

        browserContext.setDefaultNavigationTimeout(ENV_CONFIG.pageLoadDuration().toMillis());

        if (Configuration.SAVE_TRACES) {
            log.debug("TRACE file: {}", testName + ".zip");
            browserContext
                    .tracing()
                    .start(new Tracing.StartOptions()
                        .setTitle(testName)
                        .setName(testName + ".zip")
                        .setScreenshots(Configuration.SCREENSHOT)
                        .setSnapshots(Configuration.SNAPSHOT)
                        .setSources(Configuration.SOURCES)
            );
        }
        var targetPage = browserContext.newPage();

        pw.setContext(browserContext);
        pw.setPage(targetPage);
        pw.setPages(new LinkedList<>(Arrays.asList(targetPage)));
    }

    public static void closeContext(@NotNull String testName) {
        log.debug("Close context.");
        var pw = pw();

        BrowserContext targetContext = pw.getContext();
        if (Configuration.SAVE_TRACES) {
            saveTraceAs(testName, targetContext);
        }

        pw.getPage().close();
        targetContext.close();
    }

    /**
     * Method save trace into *.zip file.
     * @param fileName - file name
     * @param targetContext - context where trace is located.
     */
    public static void saveTraceAs(@NotNull String fileName, BrowserContext targetContext) {
        Path traceFilePath = buildTraceZipFilePathFor(fileName);
        log.debug("Trace: file://{}", traceFilePath);
        targetContext
                .tracing()
                .stop(new Tracing.StopOptions()
                .setPath(traceFilePath)
        );
    }

    public static void deleteTraceFilesOnTestPass(ExtensionContext context, @NotNull String traceFileName) {
        boolean testFailed = context.getExecutionException().isPresent();

        if (!testFailed) {
            Path dirPath = Paths.get(Configuration.ATTACHMENT_DIR);
            deleteAllFilesWith(traceFileName, dirPath);
        }

    }

    public static void takeScreenshotOnTestFail(ExtensionContext context, @NotNull String fileName) {
        boolean testFailed = context.getExecutionException().isPresent();
        if (testFailed) {
            Path screenshotFilePath = Paths.get(Configuration.ATTACHMENT_DIR, fileName + ".png");
            pw()
                    .getPage()
                    .screenshot(new Page.ScreenshotOptions()
                            .setPath(screenshotFilePath)
                            .setFullPage(true));

            log.debug("Screenshot: file://" + screenshotFilePath);
        }
    }

    public static void takePageContentOnTestFail(ExtensionContext context, @NotNull String fileName) {
        boolean testFailed = context.getExecutionException().isPresent();
        if (testFailed) {
            Path htmlFilePath = Paths.get(Configuration.ATTACHMENT_DIR, fileName + ".html");
            String html = pw()
                    .getPage()
                    .content();

            writeToFile(html, htmlFilePath);

            log.debug("Page context: file://" + htmlFilePath);
        }
    }

    public static void sleep(Duration duration) {
        sleep(duration.toMillis());
    }

    public static void sleep(long timeout) {
        log.debug("Sleep for: {} ms.", timeout);
        pw().getPage().waitForTimeout(timeout);
    }

    public static void sleepForWebElmRefreshDuration() {
        sleep(ENV_CONFIG.webElmRefreshDuration());
    }

    public static void sleepForWebElmRefreshDuration(int durationMultiplier) {
        sleep(ENV_CONFIG.webElmRefreshDuration().multipliedBy(durationMultiplier));
    }

    public static void open(String url) {
        pw().getPage().navigate(url);
    }

    public static void openNewPage(String url) {
        var newPage = pw().getContext().waitForPage(() -> pw().getContext().newPage().navigate(url));
        pw().addPage(newPage);
    }

    public static void refresh() {
        pw().getPage().reload();
    }

    public static WebElm activeElement() {
        return $("*:focus");
    }


    public static WebElm $(String selector) {
        return find(selector);
    }

    public static WebElm $$(String selector) {
        return new WebElm(selector);
    }

    public static WebElm find(String selector) {
        return new WebElm(selector);
    }

    public static void removeFocus() {
        new MainPage().clickAtTopRightCorner();
    }

    public static Keyboard keyboard() {
        return pw().getPage().keyboard();
    }

    public static Mouse mouse() {
        return pw().getPage().mouse();
    }

    /**
     * Method for switching between pages/tabs
     * @param index - page/tab order number, starting
     */
    public static void switchToPage(int index) {
        log.debug("Switch to tab > {}", index);
        Page pageSwitchTo = pw().context.pages().get(index);
        pw().setPage(pageSwitchTo);
        pageSwitchTo.bringToFront();
    }

    public static void closePage(int index) {
        pw().getPages().get(index).close();
    }

    /**
     * Close active page.
     */
    public static void closePage() {
        Page activePage = pw().getPage();
        LinkedList<Page> pages = pw().getPages();
        for (int i = 0; i < pages.size(); i++) {
            if (activePage.equals(pages.get(i))) {
                try {
                    pw().getPages().remove(i);
                } catch (IndexOutOfBoundsException e) {
                    throw new IllegalStateException("Can't close page with index: " + i + ". Page does not exist.");
                }
            }
        }

    }

    @Data
    public static class PWContainer {

        private BrowserContext context;
        private Page page; // Note. Store active page.
        private LinkedList<Page> pages; // Note. Store all open pages.
        private final Playwright playwright;
        private final Browser browser;

        public PWContainer(BrowserContext browserContext, Page page, Playwright playwright, Browser browser) {
            this.context = browserContext;
            this.page = page;
            this.playwright = playwright;
            this.browser = browser;
        }

        public void addPage(Page page) {
            this.pages.addLast(page);
        }
    }

    private static Path buildTraceZipFilePathFor(@NotNull String testName) {
        return Paths.get(Configuration.ATTACHMENT_DIR, testName + ".zip");
    }

    private PlaywrightWrapper() {
        // NONE
    }
}
