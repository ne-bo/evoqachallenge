package pages;

import elements.Item;
import elements.SearchResultsElements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.htmlelements.element.Select;
import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;
import utils.Constants;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static utils.Constants.NOT_AN_ITEM;

/**
 * Created by natasha on 9/1/16.
 */
public class SearchResults extends BasePage {

    private SearchResultsElements searchResults;

    private List<Item> selectedItems;

    SearchResults(final WebDriver driver) {
        HtmlElementLoader.populatePageObject(this, driver);
        this.driver = driver;
    }

    @Step("Go to advanced search form")
    public SearchPage goToAdvancedSearchForm() {
        this.searchResults.getAdvancedSearchLink().click();
        return new SearchPage(driver);
    }

    @Step("Sort results by price")
    public SearchResults sortResultsBy(String parameterForSorting) {
        driver.findElement(By.linkText(parameterForSorting)).click();
        return new SearchResults(driver);
    }

    private Select getFilterBy(Constants.FiltersForSearchResults filter) {
        switch (filter) {
            case MODE:
                return searchResults.getMode();
            case REGION:
                return searchResults.getRegion();
            case TYPE_OF_DEAL:
                return searchResults.getTypeOfDeal();
            default:
                throw new AssertionError("You should select existing filter! One of " +
                        Constants.FiltersForSearchResults.values());
        }
    }

    @Step("Filter results")
    public SearchResults filterResultsBy(Constants.FiltersForSearchResults filter, String value) {
        getFilterBy(filter).selectByVisibleText(value);
        return this;
    }

    private int getNumberOfRowsToChoose(int minNumberOfResults, int numberOfRows) {
        int numberOfRowsToChoose = 0;
        if (minNumberOfResults <= numberOfRows) {
            int randomValue = new Random().nextInt(numberOfRows - minNumberOfResults);
            numberOfRowsToChoose = minNumberOfResults + randomValue;
        } else {
            throw new AssertionError("There is only " + numberOfRows + " search results on this page.\n" +
                    "But at least " + minNumberOfResults + " are required to proceed with this test!");
        }
        return numberOfRowsToChoose;
    }

    private List<Item> chooseSelectedItems(int minNumberOfResults) {
        List<List<WebElement>> allRows = searchResults.getSearchResults();

        int numberOfRows = allRows.size();
        int numberOfRowsToChoose = getNumberOfRowsToChoose(minNumberOfResults, numberOfRows);

        List<Item> allItems = getItems(allRows);
        List<Item> selectedItems = new LinkedList<>();

        for (int i = 1; i <= numberOfRowsToChoose; i++) {
            int randomValue = new Random().nextInt(allItems.size());
            selectedItems.add(allItems.get(randomValue));
            allItems.remove(allItems.get(randomValue));
        }

        return selectedItems;
    }

    @Step("Select few random results")
    public SearchResults selectRandomResultsAtLeast(int minNumberOfResults) {

        List<Item> selectedItems = chooseSelectedItems(minNumberOfResults);
        selectedItems.forEach(Item::select);
        this.selectedItems = selectedItems;
        return this;
    }

    @Step("Show only selected items")
    public SearchResults showSelectedItems() {
        searchResults.getShowSelectedItemsLink().click();
        return new SearchResults(driver);
    }

    private List<Item> getItems(List<List<WebElement>> rows) {
        List<Item> items = rows.stream().map(row ->
                new Item(row, driver)).collect(Collectors.toList());
        items.removeIf(item -> item.getId().equalsIgnoreCase(NOT_AN_ITEM));
        return items;
    }

    public List<Item> getItemsInSelectedSearchResults() {
        List<List<WebElement>> selectedSearchResultsRows = searchResults.getOnlySelectedSearchResults();
        return getItems(selectedSearchResultsRows);
    }

    public List<Item> getSelectedItems() {
        return selectedItems;
    }

}
