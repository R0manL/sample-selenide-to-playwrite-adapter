package com.superit.smart.qa.core.playwright;

import com.superit.smart.qa.core.playwright.conditions.Condition;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.BoundingBox;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.*;
import java.util.function.Predicate;
import java.util.regex.Pattern;

import static com.superit.smart.qa.core.playwright.PlaywrightWrapper.*;

@Slf4j
public class WebElm {
    private static final String LOCATOR_PREFIX = "Locator@";
    private static final String LOCATOR_PATH_SPLITTER = " >> ";
    private final List<String> selectors;

    private static final String CLASS = "class";

    private WebElm(List<String> selectors) {
        this.selectors = new ArrayList<>(selectors);
    }

    public WebElm(String selector) {
        this(Arrays.asList(selector));
    }

    /**
     * ---------------- Locators ----------------
     **/
    public Locator getLocator() {
        Locator result = pw().getPage().locator(selectors.get(0));
        for (int i = 1; i < selectors.size(); i++) {
            result = result.locator(selectors.get(i));
        }

        return result;
    }

    public WebElm $(@NotNull String selector) {
        List<String> result = new ArrayList<>(this.selectors);
        result.add(selector);
        return new WebElm(result);
    }

    public WebElm $$(@NotNull String selector) {
        return $(selector);
    }


    /**
     * ---------------- Single elm actions ----------------
     **/
    private WebElm fill(String text) {
        log.debug("Fill text: {}", text);
        getLocator().fill(text);
        return this;
    }

    public WebElm setValue(String text) {
        return fill(text);
    }

    public void val(@NotNull String text) {
        fill(text);
    }

    public void click() {
        log.debug("Click at: " + getLocator());
        getLocator().click();
    }

    public void clickIfVisible() {
        Locator elm = getLocator();
        if (elm.isVisible()) {
            elm.click();
        } else {
            log.warn("Element is not visible. Do not click.");
        }
    }

    public void clickIfEnabled() {
        Locator elm = getLocator();
        if (elm.isEnabled()) {
            elm.click();
        } else {
            log.warn("Element is not enabled. Skip clicking.");
        }
    }

    /**
     * Click with opening a new page.
     */
    public void clickWithNewPage() {
        var newPage = pw().getContext().waitForPage(() -> getLocator().click());
        pw().addPage(newPage);
    }

    public Response clickAndWaitOnResponse(Predicate<Response> responsePredicate) {
        log.debug("Click and wait on response.");
        return pw().getPage().waitForResponse(responsePredicate, this::click);
    }

    public WebElm doubleClick() {
        log.debug("double click");
        getLocator().dblclick();
        return this;
    }

    public WebElm scrollIntoView() {
        log.debug("Scroll into view if needed");
        getLocator().scrollIntoViewIfNeeded();
        return this;
    }

    public void press(String key) {
        log.debug("Press key: {}", key);
        getLocator().press(key);
    }

    public WebElm dragAndDropTo(WebElm target) {
        hover();
        mouse().down();
        BoundingBox targetBox = target.getLocator().boundingBox();
        mouse().move(targetBox.x + targetBox.width / 2, targetBox.y + targetBox.height / 2);
        target.hover();
        mouse().up();

        return this;
    }

    public void pressLeftArrow() {
        press(ModifierKey.ARROW_LEFT.toString());
    }

    public void pressTab() {
        press(ModifierKey.TAB.toString());
    }

    public void pressBackspace() {
        press(ModifierKey.BACKSPACE.toString());
    }

    public void pressEscape() {
        press(ModifierKey.ESCAPE.toString());
    }

    public void pressEnter() {
        press(ModifierKey.ENTER.toString());
    }

    public void pressDelete() {
        press(ModifierKey.DELETE.toString());
    }

    public void pressF9() {
        press(ModifierKey.F9.toString());
    }

    public void pressEnd() {
        press(ModifierKey.END.toString());
    }

    public void sendKeys(@NotNull String keys) {
        log.debug("Send keys: {}", keys);
        getLocator().pressSequentially(keys);
    }

    public void sendKeys(@NotNull String keys, ModifierKey withPressed) {
        keyboard().down(withPressed.toString());
        sendKeys(keys);
        keyboard().up(withPressed.toString());
    }

    public WebElm clear() {
        log.debug("Clear text");
        getLocator().clear();
        return this;
    }

    public WebElm hover() {
        log.debug("Hover");
        getLocator().hover();
        return this;
    }

    @NotNull
    public String getText() {
        log.debug("Get inner text");
        String result = getLocator().innerText();
        checkIfNotNull(result, "No text for: " + getLocator());
        return result.trim();
    }

    @NotNull
    public String getValue() {
        log.debug("Get input value");
        String value = getLocator().inputValue();
        checkIfNotNull(value, "No value for input: " + getLocator());
        return value.trim();
    }

    @NotNull
    public String getAttribute(@NotNull String name) {
        log.debug("Get attribute: {}", name);
        String attr = getLocator().getAttribute(name);
        return checkIfNotNull(attr, "No '" + name + "' attribute for element: " + getLocator()).trim();
    }

    @NotNull
    public String getPlaceholder() {
        return getAttribute("placeholder");
    }

    public String getOwnText() {
        log.debug("Get own text");
        return getLocator()
                .textContent()
                .trim();
    }

    public boolean hasOwnText(@NotNull String expectedText) {
        log.debug("Check if has own text: {}", expectedText);
        return expectedText.equals(getOwnText());
    }

    public boolean hasText(@NotNull String expectedText) {
        log.debug("Check if has text: {}", expectedText);
        return expectedText.equals(getText());
    }

    public boolean containsText(@NotNull String expectedText) {
        log.debug("Check if has text: {}", expectedText);
        return getText().contains(expectedText);
    }

    @NotNull
    public String getCssClass() {
        return getAttribute(CLASS);
    }

    public boolean hasCssClass(@NotNull String expectedName) {
        log.debug("Check if css class has name: {}", expectedName);
        return expectedName.equals(getLocator().getAttribute(CLASS));
    }

    public boolean hasCssClass(@NotNull Pattern regex) {
        log.debug("Check if css class has name matched regex: {}", regex);
        String className = getLocator().getAttribute(CLASS);
        return regex.matcher(className).matches();
    }

    public boolean containsCssClass(@NotNull String expectedName) {
        log.debug("Check if css class contains name: {}", expectedName);
        String className = getLocator().getAttribute(CLASS);
        checkIfNotNull(className, "No class attribute has been found for: " + getLocator());
        return className.contains(expectedName);
    }

    public boolean hasAttribute(@NotNull String attributeName, @NotNull String expectedValue) {
        log.debug("Check if element has attribute: '{}' with value: '{}'", attributeName, expectedValue);
        return expectedValue.equals(getLocator().getAttribute(attributeName));
    }

    public boolean hasAttribute(@NotNull String attributeName) {
        log.debug("Check if element has attribute: {}", attributeName);
        return !Objects.isNull(getLocator().getAttribute(attributeName));
    }

    public File download() {
        log.debug("Download file");
        Download download = pw().getPage().waitForDownload(
                new Page.WaitForDownloadOptions().setTimeout(Configuration.DOWNLOAD_TIMEOUT),
                this::click);

        download.saveAs(Paths.get(Configuration.DOWNLOAD_DIR, download.suggestedFilename()));

        return download.path().toFile();
    }

    public void uploadFile(Path filePath) {
        log.debug("Upload file: {}", filePath.toString());
        getLocator().setInputFiles(filePath);
    }

    public WebElm getActiveElement() {
        log.debug("Get active element");
        return new WebElm("*:focus");
    }

    public WebElm getByText(@NotNull String text) {
        log.debug("Find elements by text: {}", text);
        return action(getLocator().getByText(text));
    }

    public WebElm getByExactText(@NotNull String text) {
        log.debug("Find elements by exact text: {}", text);
        return action(getLocator().getByText(text, new Locator.GetByTextOptions().setExact(true)));
    }

    public WebElm getByTextStartWith(@NotNull String prefix) {
        log.debug("Find elements by text that starts with: {}", prefix);
        return $("//*[starts-with(text(),'" + prefix + "')]");
    }

    /**
     * ---------------- Multiple elms actions ----------------
     **/
    public List<WebElm> all() {
        log.debug("Get all elements");
        return getLocator()
                .all()
                .stream()
                .map(this::action)
                .toList();
    }

    public List<String> texts() {
        log.debug("Get texts");
        return this
                .shouldHas(Condition.visibleAtLeastOne)
                .all()
                .stream()
                .map(locatorActions -> locatorActions.getText().trim())
                .toList();
    }

    public List<String> ownTexts() {
        log.debug("Get own texts");
        return all()
                .stream()
                .map(locatorActions -> locatorActions.getOwnText().trim())
                .toList();
    }

    public List<String> values() {
        log.debug("Get input values");
        return all()
                .stream()
                .map(locatorActions -> locatorActions.getValue().trim())
                .toList();
    }

    public int size() {
        log.debug("Get size");
        return getLocator().count();
    }

    public WebElm get(int i) {
        log.debug("Get element with index: '{}'", i);
        return action(getLocator().nth(i));
    }

    public WebElm first() {
        log.debug("Get first element");
        return action(getLocator().first());
    }

    public WebElm last() {
        log.debug("Get last element");
        return action(getLocator().last());
    }

    public WebElm parent() {
        log.debug("Get parent");
        return $("..");
    }

    /**
     * Finds the ancestor element that matches the given XPath expression.
     * <p>
     * Usage:
     * <pre>{@code
     * find("$child").closest("ancestor").find("button").click();
     * }</pre>
     *
     * @param xpathSelector the XPath expression representing the ancestor element to search for
     * @return an instance of LocatorActions for method chaining
     */
    public WebElm ancestor(@NotNull String xpathSelector) {
        getLocator().locator("//ancestor::" + xpathSelector);
        return this;
    }

    public void setValue(int index, @NotNull String value) {
        log.debug("Set value: '{}', into input with index '{}'", value, index);
        getLocator().nth(index).fill(value);
    }

    /**
     * Get index of elm in a list.
     *
     * @param webElm - elm's to find.
     * @return index or -1 if not found.
     */
    public int indexOf(WebElm webElm) {
        Locator expectElmLocator = webElm.getLocator();
        log.debug("Get index of elm: '{}' in the list.", expectElmLocator);

        List<Locator> existingElms = getLocator().all();

        for (int i = 0; i < existingElms.size(); i++) {
            ElementHandle elm1 = existingElms.get(i).elementHandle();
            ElementHandle elm2 = expectElmLocator.elementHandle();

            Map<String, ElementHandle> arg = new HashMap<>();
            arg.put("elm1", elm1);
            arg.put("elm2", elm2);

            boolean exist = (boolean) pw().getPage().evaluate("o => o.elm1.isEqualNode(o.elm2)", arg);
            if (exist) {
                return i;
            }
        }

        return -1;
    }

    public WebElm filterByChild(@NotNull String selector) {
        log.debug("Filter elements by child element: {}", selector);
        Locator child = pw().getPage().locator(selector);
        return action(getLocator().filter(new Locator.FilterOptions().setHas(child)));
    }

    /*  */
    public WebElm filterByText(@NotNull String text) {
        log.debug("Filter elements by text: {}", text);
        return action(getLocator().filter(new Locator.FilterOptions().setHasText(text)));
    }

    public WebElm filterByText(Pattern pattern) {
        log.debug("Filter elements by pattern: {}", pattern);
        return action(getLocator().filter(new Locator.FilterOptions().setHasText(pattern)));
    }

    public WebElm filterByNotEmptyText() {
        log.debug("Filter elements have not empty text");
        Pattern empty = Pattern.compile("^\\s*$");
        return action(getLocator().filter(new Locator.FilterOptions().setHasNotText(empty)));
    }

    public WebElm filterByVisibilityIs(boolean value) {
        log.debug("Filter by visibility = {}", value);
        return this.$("visible=" + value);
    }

    public WebElm andChildWithText(@NotNull String text) {
        return this.filterByChild("//*[normalize-space(text())='" + text + "']");
    }

    /**
     * ---------------- Conditions ----------------
     **/
    public boolean isDisplayed() {
        log.debug("Check if displayed");
        return getLocator().isVisible();
    }

    public boolean areDisplayed() {
        log.debug("Check if at least one has displayed");
        return filterByVisibilityIs(true).getLocator().nth(0).isVisible();
    }

    public boolean exist() {
        log.debug("Check if at leas one exist");
        return !getLocator().all().isEmpty();
    }

    // Opposite to isDisplayed.
    public boolean isHidden() {
        log.debug("Check if hidden");
        return getLocator().isHidden();
    }

    public boolean areHidden() {
        log.debug("Check if all elements are hidden");
        return !getLocator().nth(0).isVisible();
    }

    public boolean isSelected() {
        log.debug("Check if checkbox has selected");
        return getLocator().isChecked();
    }

    public WebElm should(Condition condition) {
        condition.verify(this);
        return this;
    }

    public WebElm should(Condition condition, Duration duration) {
        condition.verify(this, duration);
        return this;
    }

    public WebElm has(Condition condition) {
        condition.verify(this);
        return this;
    }

    public WebElm shouldBe(Condition condition) {
        condition.verify(this);
        return this;
    }

    public WebElm shouldBe(Condition condition, Duration duration) {
        condition.verify(this, duration);
        return this;
    }

    public WebElm shouldHas(Condition condition) {
        condition.verify(this);
        return this;
    }

    public WebElm shouldHas(Condition condition, Duration duration) {
        condition.verify(this, duration);
        return this;
    }

    public WebElm shouldHave(Condition condition) {
        condition.verify(this);
        return this;
    }

    public WebElm shouldHave(Condition condition, Duration duration) {
        condition.verify(this, duration);
        return this;
    }

    @Override
    @NotNull
    public String toString() {
        return this.getLocator().toString();
    }

    private String checkIfNotNull(@Nullable String value, @NotNull String errMsg) {
        return Objects.requireNonNull(value, errMsg);
    }

    /* add a new action to a list of selectors */
    private WebElm action(Locator locator) {
        List<String> selectors = getSelectorsFrom(locator);
        return new WebElm(selectors);
    }

    private List<String> getSelectorsFrom(Locator locator) {
        return Arrays.stream(locator.toString().substring(LOCATOR_PREFIX.length()).split(LOCATOR_PATH_SPLITTER)).toList();
    }
}
