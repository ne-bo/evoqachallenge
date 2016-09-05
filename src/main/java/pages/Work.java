package pages;

import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;

/**
 * Created by natasha on 9/1/16.
 */
public class Work extends BasePage {

    public Work(final WebDriver driver) {
        HtmlElementLoader.populatePageObject(this, driver);
        this.driver = driver;
    }

    /*Some code here*/

    /*This calss was added jus to show that we can work with different blocks, not only with Electronics.*/
}
