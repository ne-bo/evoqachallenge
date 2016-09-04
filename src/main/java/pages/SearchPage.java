package pages;

import elements.SearchForm;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;
import utils.Constants;

import java.util.Map;

/**
 * Created by natasha on 9/1/16.
 */
public class SearchPage extends BasePage {

    private SearchForm searchForm;

    public SearchPage(final WebDriver driver) {
        HtmlElementLoader.populatePageObject(this, driver);
        this.driver = driver;
    }

    @Step("Fill search form")
    public SearchPage fillSearchForm(Map<Constants.SearchFormFields, String> searchData) {
        searchForm.fillSearchForm(searchData);
        return new SearchPage(driver);
    }

    @Step("Submit search form")
    public SearchResults submitSearchForm() {
        searchForm.submitSearchForm();
        return new SearchResults(driver);
    }

    @Step("Fill and submit search form")
    public SearchResults fillAndSubmitSearchForm(Map searchData) {
        fillSearchForm(searchData);
        return submitSearchForm();
    }

}
