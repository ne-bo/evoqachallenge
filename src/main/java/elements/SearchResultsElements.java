package elements;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.Link;
import ru.yandex.qatools.htmlelements.element.Select;
import ru.yandex.qatools.htmlelements.element.Table;

import java.util.List;

/**
 * Created by natasha on 9/4/16.
 */
@FindBy(css = "#page_main")
public class SearchResultsElements extends HtmlElement {

    @FindBy(xpath = "//*[@id=\"page_main\"]/tbody/tr/td/table[1]/tbody/tr/td[4]/a")
    private Link advancedSearchLink;

    @FindBy(css = "#page_main > tbody > tr > td > div.filter_second_line_dv > span:nth-child(1) > noindex > select")
    private Select mode;

    @FindBy(css = "#region_select")
    private Select region;

    @FindBy(css = "#page_main > tbody > tr > td > div.filter_second_line_dv > span:nth-child(3) > select")
    private Select typeOfDeal;

    @FindBy(xpath = "//*[@id=\"page_main\"]/tbody/tr/td/table[2]")
    private Table searchResults;

    @FindAll(@FindBy(css = "#filter_frm > table"))
    List<Table> onlySelectedSearchResults;


    @FindBy(id = "show_selected_a")
    private Link showSelectedItemsLink;

    public Link getAdvancedSearchLink() {
        return advancedSearchLink;
    }

    public Select getMode() {
        return mode;
    }

    public Select getRegion() {
        return region;
    }

    public Select getTypeOfDeal() {
        return typeOfDeal;
    }

    public List<List<WebElement>> getSearchResults() {
        List<List<WebElement>> rows = searchResults.getRows();
        return rows;
    }

    public List<List<WebElement>> getOnlySelectedSearchResults() {
        List<List<WebElement>> rows = onlySelectedSearchResults.get(0).getRows();
        for (int i = 1; i < onlySelectedSearchResults.size(); i++) {
            rows.addAll(onlySelectedSearchResults.get(i).getRows());
        }
        return rows;
    }

    public Link getShowSelectedItemsLink() {
        return showSelectedItemsLink;
    }


}
