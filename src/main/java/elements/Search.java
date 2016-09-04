package elements;

import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.Link;

/**
 * Created by natasha on 9/1/16.
 */
@FindBy(xpath = "//*[@id=\"main_table\"]/span[2]/b[3]/a")
public class Search extends HtmlElement {

    @FindBy(xpath = "//*[@id=\"main_table\"]/span[2]/b[3]/a")
    public Link searchLink;

    public void start() {
        searchLink.click();
    }

}
