package elements;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.htmlelements.element.CheckBox;

import java.util.List;

import static utils.Constants.NOT_AN_ITEM;

/**
 * Created by natasha on 9/3/16.
 */

public class Item {


    String id;
    WebDriver driver;
    CheckBox checkBox;

    public String getId() {
        return id;
    }

    public Item(List<WebElement> row, WebDriver driver) {
        if (row.get(0).getAttribute("class").equalsIgnoreCase("msga2 pp0")) {
            this.checkBox = new CheckBox(row.get(0).findElement(By.name("mid[]")));

            this.id = checkBox.getWrappedElement().getAttribute("id");
        } else {
            this.id = NOT_AN_ITEM;
        }
        this.driver = driver;
    }

    public void select() {
        checkBox.select();
    }

    public void deselect() {
        if (checkBox.isSelected()) {
            checkBox.select();
        }
        ;
    }

    public boolean isSelected() {
        return checkBox.isSelected();
    }

    public String toString() {
        return this.id;
    }


    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Item) == false) {
            return false;
        }
        Item secondItem = ((Item) other);
        return new EqualsBuilder().append(id, secondItem.id).isEquals();
    }
}
