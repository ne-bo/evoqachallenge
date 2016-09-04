package pages;

import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.allure.annotations.Step;

/**
 * Created by natasha on 9/1/16.
 */
public class BasePage {

    WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Start search")
    public SearchPage startSearch() {
        return new SearchPage(driver);
    }

}
