package elements;

import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.Link;
import utils.Constants;

import static utils.Constants.Language.LV;
import static utils.Constants.Language.RU;

/**
 * Created by natasha on 8/30/16.
 */
@FindBy(className = "menu_lang")
public class LanguageOfPage extends HtmlElement {

    @FindBy(css = "#main_table > span.menu_lang > a")
    private Link changeLanguageLink;

    public void change() {
        changeLanguageLink.click();
    }

    public void change(Constants.Language language) {
        if (!this.getLanguage().equals(language)) {
            changeLanguageLink.click();
        }
    }

    public Constants.Language getLanguage() {
        if (changeLanguageLink.getText().equalsIgnoreCase(LV.toString())) {
            return RU;
        } else {
            if (changeLanguageLink.getText().equalsIgnoreCase(RU.toString())) {
                return LV;
            } else {
                throw new AssertionError("Unexpected language!");
            }
        }
    }
}