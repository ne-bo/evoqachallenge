import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.BasePage;
import pages.MainPage;
import pages.SearchPage;
import pages.SearchResults;
import utils.Constants;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static ru.yandex.qatools.matchers.collection.HasSameItemsAsListMatcher.hasSameItemsAsList;
import static utils.Constants.DEFAULT_SEARCH_DATA;
import static utils.Constants.FiltersForSearchResults.TYPE_OF_DEAL;
import static utils.Constants.Language.RU;
import static utils.Constants.SearchFormFields.*;
import static utils.Constants.URL;
import static utils.LocalisationUtils.*;

/**
 * Created by natasha on 8/29/16.
 */
public class ShowSelectedGoods {


    public WebDriver driver = new FirefoxDriver();

    @Before
    public void loadStartPage() {
        driver.get(URL);
    }

    @Test
    public void afterSearchingUserShouldSeSearchResults() {
        // data is specific for this test, so it is in test, not in @Before
        int minResultsCount = 3;
        Constants.Language desiredLanguage = RU;

        Map<Constants.SearchFormFields, String> searchData = new HashMap(DEFAULT_SEARCH_DATA);
        searchData.put(KEYWORD, "Компьютер");
        searchData.put(MIN_PRICE, "10");

        Map<Constants.SearchFormFields, String> advancedSearchData = new HashMap(searchData);
        searchData.put(MIN_PRICE, "0");
        searchData.put(MAX_PRICE, "300");

        //Start of scenario
        MainPage mainPageWithChangedLanguage = new MainPage(driver).changeLanguage(desiredLanguage);

        BasePage electronics = mainPageWithChangedLanguage.selectBlock(getStringForElectronics(desiredLanguage));

        SearchPage searchPage = electronics.startSearch();

        SearchResults searchResults = searchPage.fillAndSubmitSearchForm(searchData);

        searchResults.sortResultsBy(getStringForPrice(desiredLanguage));

        searchResults.filterResultsBy(TYPE_OF_DEAL, getStringForSale(desiredLanguage));

        SearchPage advancedSearchPage = searchResults.goToAdvancedSearchForm();

        SearchResults advancedSearchResults = advancedSearchPage.fillAndSubmitSearchForm(advancedSearchData);

        advancedSearchResults.selectRandomResultsAtLeast(minResultsCount);

        advancedSearchResults.showSelectedItems();

        //Final check (I prefer style with 1 asset per test)
        assertThat("Selected items and only them should be shown! ",
                advancedSearchResults.getSelectedItems(),
                hasSameItemsAsList(advancedSearchResults.getItemsInSelectedSearchResults()));

    }

    @After
    public void killWebDriver() {
        driver.quit();
    }
}
