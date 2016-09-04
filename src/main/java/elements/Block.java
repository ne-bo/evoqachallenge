package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import utils.Constants;

import static utils.Constants.blocksClasses;
import static utils.LocalisationUtils.getBlockNameInEnglish;

/**
 * Created by natasha on 8/31/16.
 */
public class Block extends HtmlElement {

    String blockName;
    String blockNameInEnglish;
    WebDriver driver;
    Class classOfBlockPage;

    public Block(String blockName, WebDriver driver, Constants.Language language) {
        this.blockName = blockName;
        this.blockNameInEnglish = getBlockNameInEnglish(blockName, language);
        this.classOfBlockPage = blocksClasses.get(blockNameInEnglish);
        this.driver = driver;
    }

    public void selectBlock() {
        driver.findElement(By.linkText(blockName)).click();
    }

    public String getName() {
        return blockName;
    }

    public String toString() {
        return blockName;
    }

    public Class getClassOfBlockPage() {
        return classOfBlockPage;
    }
}
