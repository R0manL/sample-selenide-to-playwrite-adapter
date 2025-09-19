package com.superit.smart.qa.core.playwright.conditions;


import com.superit.smart.qa.core.playwright.WebElm;
import org.jetbrains.annotations.NotNull;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public interface Condition {
    Condition visible = new VisibleCondition();
    Condition hidden = new HiddenCondition(); // Wait on element to be hidden. Similar to disappear.
    Condition exist = new ExistCondition();
    Condition appear = new VisibleCondition();
    Condition disappear = new HiddenCondition();
    Condition enabled = new EnabledCondition();
    Condition disabled = new DisabledCondition();
    Condition checked = new CheckedCondition();
    Condition unChecked = new UnCheckedCondition();
    Condition visibleAtLeastOne = new AnyVisible();  //Note. Return not empty list of visible elements.
    Condition empty = new Empty();
    Condition notEmpty = new NotEmpty();

    String CLASS = "class";

    static Condition attribute(@NotNull String attributeName, @NotNull String expectedAttributeValue) {
        return new AttributeCondition(attributeName, expectedAttributeValue);
    }

    static Condition attribute(@NotNull String attributeName, Pattern value) {
        return new AttributeMatchingCondition(attributeName, value);
    }

    /** Check if element has child with exact text **/
    static Condition childWithExactText(@NotNull String expectedText) {
        return new ChildWithExactTextCondition(expectedText);
    }

    /** Check if element has child with text (contains) **/
    static Condition childWithText(@NotNull String expectedText) {
        return new ChildWithTextCondition(expectedText);
    }

    static Condition css(@NotNull String expectedName, @NotNull String expectedValue) {
        return new CssCondition(expectedName, expectedValue);
    }

    static Condition cssClass(@NotNull String expectedValue) {
        return new AttributeCondition(CLASS, expectedValue);
    }

    static Condition cssClassContains(@NotNull String value) {
        return new AttributePatternCondition(CLASS, Pattern.compile("\\b" + value + "\\b"));
    }

    static Condition date(@NotNull LocalDate value) {
        return new DateCondition(value);
    }

    static Condition dateValue(@NotNull LocalDate value) {
        return new DateValueCondition(value);
    }

    static Condition exactText(@NotNull String expectedText) {
        return new ExactTextCondition(expectedText);
    }

    static Condition exactTexts(@NotNull String... expectedTexts) {
        return new ExactTextsCondition(expectedTexts);
    }

    static Condition exactTexts(List<String> expectedTexts) {
        return new ExactTextsCondition(expectedTexts.toArray(new String[0]));
    }

    /** Check if list of elements contains all elements with exact text in any order **/
    static Condition exactTextsContainInAnyOrderCondition(@NotNull List<String> expectedTexts) {
        return new ExactTextsContainInAnyOrderCondition(expectedTexts);
    }

    static Condition exactTextsIgnoreCase(List<String> expectedTexts) {
        return new ExactTextsIgnoreCaseCondition(expectedTexts.toArray(new String[0]));
    }

    static Condition exactTextsInAnyOrderCondition(@NotNull String... expectedTexts) {
        return new ExactTextsInAnyOrderCondition(Arrays.stream(expectedTexts).toList());
    }

    /** Check if list of elements have only elements with exact text in any order **/
    static Condition exactTextsInAnyOrderCondition(@NotNull List<String> expectedTexts) {
        return new ExactTextsInAnyOrderCondition(expectedTexts);
    }

    static Condition exactTextsOneOf(@NotNull String... texts) {
        return new ExactTextsOneOfCondition(texts);
    }

    static Condition inputPlaceholderWith(@NotNull String value) {
        return new InputPlaceholderCondition(value);
    }

    static Condition inputPlaceholderWith(Pattern value) {
        return new InputPlaceholderPatternCondition(value);
    }

    /** None of the elements present in DOM **/
    static Condition noneVisible() {
        return new NoneVisible();
    }

    static Condition notAttribute(@NotNull String attributeName, @NotNull String value) {
        return new NotAttributeCondition(attributeName, value);
    }

    static Condition notCssClassContains(@NotNull String value) {
        return new NotAttributeByPatternCondition(CLASS, Pattern.compile("\\b" + value + "\\b"));
    }

    static Condition notExactText(@NotNull String expectedText) {
        return new NotExactTextCondition(expectedText);
    }

    static Condition notOwnText(@NotNull String expectedText) {
        return new NotOwnTextCondition(expectedText);
    }

    static Condition notOwnText(Pattern pattern) {
        return new NotOwnTextPatternCondition(pattern);
    }

    static Condition notText(@NotNull String expectedText) {
        return new NotTextCondition(expectedText);
    }

    static Condition notValue(@NotNull String expectedValue) {
        return new ValueNotCondition(expectedValue);
    }

    /** Contains own text **/
    static Condition ownText(@NotNull String expectedText) {
        return new OwnTextCondition(expectedText);
    }

    static Condition ownText(@NotNull Pattern pattern) {
        return new OwnTextPatternCondition(pattern);
    }

    static Condition ownTextNotEmpty() {
        return new OwnTextNotEmpty();
    }

    static Condition single() {
        return new SizeCondition(1);
    }

    static Condition size(int expectedSize) {
        return new SizeCondition(expectedSize);
    }

    static Condition sizeGreaterThan(int expectedSize) {
        return new SizeGreaterThanCondition(expectedSize);
    }

    static Condition text(@NotNull String expectedText) {
        return new TextCondition(expectedText);
    }

    static Condition text(Pattern pattern) {
        return new TextPatternCondition(pattern);
    }

    /** Contains inner text **/
    static Condition textIgnoreCase(@NotNull String expectedText) {
        return new TextIgnoreCaseCondition(expectedText);
    }

    static Condition texts(@NotNull String... expectedTexts) {
        return new TextsCondition(expectedTexts);
    }

    static Condition texts(List<String> expectedTexts) {
        return new TextsCondition(expectedTexts.toArray(new String[0]));
    }

    static Condition textsEachContains(@NotNull String expectedText) {
        return new TextsEachContainsCondition(expectedText);
    }

    /** Check if list of elements have all elements with text in any order. Duplicate texts are not allowed **/
    static Condition textsInAnyOrder(@NotNull List<String> expectedTexts) {
        return new TextsInAnyOrderCondition(expectedTexts);
    }

    /** Check if list of elements have all elements with text in any order. Duplicate allowed **/
    static Condition textsInAnyOrderWithDuplicates(@NotNull List<String> expectedTexts) {
        return new TextsInAnyOrderWithDuplatesCondition(expectedTexts);
    }

    static Condition value(@NotNull String expectedValue) {
        return new ValueCondition(expectedValue);
    }

    static Condition value(Pattern pattern) {
        return new ValuePatternCondition(pattern);
    }

    static Condition valueContains(@NotNull String text) {
        return new ValueContainsCondition(text);
    }

    static Condition valueEndWith(@NotNull String text) {
        return new ValueEndWithCondition(text);
    }

    static Condition valueNotContains(@NotNull String text) {
        return new NotValueContainsCondition(text);
    }

    static Condition visibleOr(WebElm webElm) {
        return new VisibleOrCondition(webElm);
    }

    void verify(WebElm webElm);
    void verify(WebElm webElm, Duration timeout);
}
