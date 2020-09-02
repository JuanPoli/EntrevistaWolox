package components.commons;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import web.BaseComponent;

import java.util.List;

public class Select extends BaseComponent {

    @FindBy(tagName = "option")
    private List<WebElement> options;

    private int indexOfSelectedItem ;

    public Select(WebElement container) {
        super(container);
    }

    public Select selectByValue(String value) {
        options.stream().filter(element -> element.getText().equals(value)).findFirst().get().click();
        indexOfSelectedItem = options.indexOf(options.stream().filter(element -> element.getText().equals(value)).findFirst().get());
        return this;
    }

    public int getIndexOfSelectedItem() {
        return indexOfSelectedItem;
    }


}
