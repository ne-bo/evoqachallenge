package pages;

import elements.Search;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;

/**
 * Created by natasha on 9/1/16.
 */
public class Electronics extends BasePage {

    private Search search;

    public Electronics(final WebDriver driver) {
        HtmlElementLoader.populatePageObject(this, driver);
        this.driver = driver;
    }

    @Step("Start search from Electronics page")
    public SearchPage startSearch() {
        this.search.start();
        return new SearchPage(driver);
    }
}
