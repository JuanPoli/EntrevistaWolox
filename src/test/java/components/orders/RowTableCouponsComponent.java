package components.orders;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import web.BaseComponent;

import java.util.List;

import static components.orders.RowTableCouponsComponent.RowElements.*;

public class RowTableCouponsComponent extends BaseComponent {

    @FindBy(tagName = "td")
    private List<WebElement> fieldsList;

    @FindBy(id = "confirmation-modal")
    private WebElement modalOrderConfirmationContainer;

    private WebElement container;

    public enum RowElements {
        CODE, DATE, DESCRIPTION, USES
    }


    public RowTableCouponsComponent(WebElement container) {
        super(container);
        this.container = container;
    }

    public String getFieldText(String field) {
        field = field.toLowerCase();

        switch (field) {
            case "code":
                return fieldsList.get(CODE.ordinal()).getText();
            case "date":
                return fieldsList.get(DATE.ordinal()).getText();
            case "description":
                return fieldsList.get(DESCRIPTION.ordinal()).getText();
            case "uses":
                return fieldsList.get(USES.ordinal()).getText();
        }
        return "";
    }

    public String getEmptyCode() {
        return container.getText();
    }
}
