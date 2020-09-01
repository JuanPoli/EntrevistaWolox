package components;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import tests.BaseComponent;

import java.util.List;

public class Select extends BaseComponent {

    @FindBy(tagName = "option")
    private List<WebElement> options;

    public Select(WebElement container) {
        super(container);
    }

    public Select selectByValue(String value) {
        options.stream().filter(element -> element.getText().equals(value)).findFirst().get().click();
        return this;
    }


}
