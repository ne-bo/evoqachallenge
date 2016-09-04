import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.BasePage;
import pages.MainPage;
import pages.SearchPage;
import pages.SearchResults;
import steps.CommonSteps;
import utils.Constants;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static ru.yandex.qatools.matchers.collection.HasSameItemsAsListMatcher.hasSameItemsAsList;
import static utils.Constants.DEFAULT_SEARCH_DATA;
import static utils.Constants.FiltersForSearchResults.TYPE_OF_DEAL;
import static utils.Constants.Language.RU;
import static utils.Constants.SearchFormFields.*;
import static utils.LocalisationUtils.*;

/**
 * Created by natasha on 8/29/16.
 */
public class ShowSelectedGoods {
    private final int MIN_RESULTS_COUNT;

    public WebDriver driver = new FirefoxDriver();

    private CommonSteps commonSteps = new CommonSteps();

    public ShowSelectedGoods() {
        MIN_RESULTS_COUNT = 3;
    }


    @Before
    public void loadStartPage() {
        driver.get("https://www.ss.lv");
    }

    @Test
    public void afterSearchingUserShouldSeSearchResults() {
        Constants.Language desiredLanguage = RU;

        Map<Constants.SearchFormFields, String> searchData = new HashMap(DEFAULT_SEARCH_DATA);
        searchData.put(KEYWORD, "Компьютер");
        searchData.put(MIN_PRICE, "10");

        Map<Constants.SearchFormFields, String> advancedSearchData = new HashMap(searchData);
        searchData.put(MIN_PRICE, "0");
        searchData.put(MAX_PRICE, "300");

        MainPage startPage = new MainPage(driver);
        MainPage mainPageWithChangedLanguage = startPage.changeLanguage(desiredLanguage);

        assertThat(mainPageWithChangedLanguage.getLanguage(), equalTo(desiredLanguage));

        BasePage electronics = mainPageWithChangedLanguage.selectBlock(getStringForElectronics(desiredLanguage));

        SearchPage searchPage = electronics.startSearch();

        SearchResults searchResults = searchPage.fillAndSubmitSearchForm(searchData);

        searchResults.sortResultsBy(getStringForPrice(desiredLanguage));

        searchResults.filterResultsBy(TYPE_OF_DEAL, getStringForSale(desiredLanguage));

        SearchPage advancedSearchPage = searchResults.goToAdvancedSearchForm();

        SearchResults advancedSearchResults = advancedSearchPage.fillAndSubmitSearchForm(searchData);

        advancedSearchResults.selectRandomResultsAtLeast(MIN_RESULTS_COUNT);

        advancedSearchResults.showSelectedItems();

        assertThat("Only selected items should be shown! ",
                advancedSearchResults.getSelectedItems(),
                hasSameItemsAsList(advancedSearchResults.getItemsInSelectedSearchResults()));

        commonSteps.waitForSeconds(5);
    }

    @After
    public void killWebDriver() {
        driver.quit();
    }
}
