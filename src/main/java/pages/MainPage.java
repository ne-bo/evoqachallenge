package pages;

import elements.Block;
import elements.LanguageOfPage;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;
import utils.Constants;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by natasha on 8/30/16.
 */
public class MainPage extends BasePage {

    private LanguageOfPage language;

    public MainPage(final WebDriver driver) {
        HtmlElementLoader.populatePageObject(this, driver);
        this.driver = driver;
    }

    @Step("Change language")
    public MainPage changeLanguage() {
        this.language.change();
        return new MainPage(driver);
    }

    @Step("Change language to  desired language")
    public MainPage changeLanguage(Constants.Language language) {
        this.language.change(language);
        return new MainPage(driver);
    }

    public Constants.Language getLanguage() {
        return this.language.getLanguage();
    }

    @Step("Select block with name")
    public <T extends BasePage> T selectBlock(String blockName) {
        Block block = new Block(blockName, driver, language.getLanguage());
        block.selectBlock();
        BasePage newPage = new BasePage();
        try {
            newPage = (T) block.getClassOfBlockPage().getConstructor(WebDriver.class)
                    .newInstance(driver);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return (T) newPage;
    }

}
