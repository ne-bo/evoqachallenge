package elements;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Form;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import utils.Constants;

import java.util.Map;

import static utils.Constants.SearchFormFields.*;

/**
 * Created by natasha on 9/4/16.
 */
@FindBy(css = "#page_main")
public class SearchForm extends HtmlElement {

    @FindBy(name = "ffrm")
    private Form searchForm;

    public void fillSearchForm(Map<Constants.SearchFormFields, String> searchData) {
        Map<String, Object> searchDataForForm = Maps.newHashMap(
                ImmutableMap.<String, String>builder()
                        .put("txt", searchData.get(KEYWORD))
                        .put("topt[8][min]", searchData.get(MIN_PRICE))
                        .put("topt[8][max]", searchData.get(MAX_PRICE))
                        .build());
        this.searchForm.fill(searchDataForForm);
    }

    public void submitSearchForm() {
        searchForm.submit();
    }

}
