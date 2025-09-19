package com.superit.smart.qa.ui.smartbox.pages.components.list;

import com.superit.smart.qa.core.playwright.WebElm;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.util.*;

import static com.superit.smart.qa.core.playwright.conditions.Condition.attribute;
import static com.superit.smart.qa.core.playwright.conditions.Condition.visibleAtLeastOne;

/**
 * Created by R0manL.
 */

@Slf4j
public class ListOfElmsWithScroller {
    public static final String END_OF_LIST_MSG = "You reach the end of the list.";
    public static final int MAX_NUM_OF_SCROLLS = 200;
    public static final String COLUMN_ID_ATTRIBUTE = "col-id";

    private final WebElm listOfElms;


    public ListOfElmsWithScroller(WebElm listOfElms) {
        listOfElms.shouldBe(visibleAtLeastOne);
        this.listOfElms = listOfElms;
    }

    public WebElm findFirstBy(@NotNull String attributeName, @NotNull String attributeValue) {
        log.info("Find first element in lazy loaded list with attribute and value: {}={}.", attributeName, attributeValue);

        WebElm result = listOfElms.first();

        for (int i = 0; i <= MAX_NUM_OF_SCROLLS; i++) {
            for (int j = 0; j <= listOfElms.size(); j++) {
                result = listOfElms.get(j);
                if (result.isDisplayed() && result.hasAttribute(attributeName, attributeValue)) {
                    return result;
                }
            }

            if (scrollAndCheckIfEndOfList()) {
                log.debug(END_OF_LIST_MSG);
                break;
            }
        }

        return result.shouldHas(attribute(attributeName, attributeValue)).scrollIntoView();
    }

    /**
     * Method scrolls through a lazy loading list and exits when element was found or end of the list was reached.
     * ATTENTION. Method does not verify if element exists or visible.
     *
     * @param text of the element that should be found.
     * @return found element
     */
    public WebElm findFirstChildBy(@NotNull String text) {
        log.info("Find child element in lazy loaded list by text: {}.", text);

        WebElm result = listOfElms.filterByText(text).first();

        for (int i = 0; i <= MAX_NUM_OF_SCROLLS; i++) {
            if (result.isDisplayed()) {
                return result;
            }

            if (scrollAndCheckIfEndOfList()) {
                log.debug(END_OF_LIST_MSG);
                break;
            }
        }

        return result;
    }

    public WebElm findFirstChildByExactText(@NotNull String text) {
        log.info("Find child element in lazy loaded list by exact text: {}.", text);

        WebElm result = listOfElms.getByExactText(text);

        for (int i = 0; i <= MAX_NUM_OF_SCROLLS; i++) {
            if (result.isDisplayed()) {
                return result;
            }

            if (scrollAndCheckIfEndOfList()) {
                log.debug(END_OF_LIST_MSG);
                break;
            }
        }

        return result;
    }

    /**
     * Method scroll and store unique texts from list of elements.
     *
     * @return unique texts from list of elements
     */
    public List<String> getTexts() {
        return getTextsOf(this.listOfElms);
    }

    /**
     * Method scroll and store unique texts from list of elements.
     *
     * @return unique texts from list of elements
     */
    public List<String> getTextsOf(WebElm elms) {
        log.info("Getting text of elements in lazy loaded list.");

        Set<String> result = new HashSet<>();

        for (int i = 0; i <= MAX_NUM_OF_SCROLLS; i++) {
            result.addAll(elms.ownTexts());

            if (scrollAndCheckIfEndOfList()) {
                log.debug(END_OF_LIST_MSG);
                break;
            }
        }

        return new ArrayList<>(result);
    }

    public Map<String, String> getTextsAndAttrValues(WebElm elms, @NotNull String attrName) {
        log.info("Getting not unique text and '{}' attribute of elements in lazy loaded list.", attrName);
        Map<String, String> result = new HashMap<>();

        for (int i = 0; i <= MAX_NUM_OF_SCROLLS; i++) {
            elms.getLocator().all()
                    .forEach(locator -> {
                        if (!result.containsKey(locator.getAttribute(attrName))) {
                            String text = locator.innerText();
                            result.put(locator.getAttribute(attrName), text);
                        }
                    });

            if (scrollAndCheckIfEndOfList()) {
                log.debug(END_OF_LIST_MSG);
                break;
            }
        }

        return result;
    }

    /**
     * Method scroll and check if new items appears after scrolling.
     *
     * @return true - new items exist, false - no new items (end of the list).
     */
    public boolean scrollAndCheckIfEndOfList() {
        if (listOfElms.all().isEmpty()) {
            return true;
        }

        WebElm lastElmInList = listOfElms.last();
        String attribute = COLUMN_ID_ATTRIBUTE;
        boolean isTable = lastElmInList.hasAttribute(attribute);

        String oldHashCode = isTable ? lastElmInList.getAttribute(attribute) : String.valueOf(lastElmInList.hashCode());
        lastElmInList.scrollIntoView();
        String newHashCode = isTable ? lastElmInList.getAttribute(attribute) : String.valueOf(lastElmInList.hashCode());

        return oldHashCode.equals(newHashCode);
    }
}
