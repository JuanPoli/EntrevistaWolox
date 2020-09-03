package components.orders;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import web.BaseComponent;

import java.util.List;

import static components.orders.RowTableComponent.RowElements.*;

public class RowTableComponent extends BaseComponent {

    @FindBy(tagName = "td")
    private List<WebElement> fieldsList;

    @FindBy(id = "confirmation-modal")
    private WebElement modalOrderConfirmationContainer;


    public enum RowElements {
        DATE, DESCRIPTION, PRICE, SHIPPING_COST, STORE, ADDRESS, VARIABLE_OPTION
    }

    ;

    public RowTableComponent(WebElement container) {
        super(container);
    }

    public String getFieldText(String field) {
        field = field.toLowerCase();

        if (field.equals("date")) {
            return fieldsList.get(DATE.ordinal()).getText();
        } else if (field.equals("description")) {
            return fieldsList.get(DESCRIPTION.ordinal()).getText();
        } else if (field.equals("price")) {
            return fieldsList.get(PRICE.ordinal()).getText();
        } else if (field.equals("shipping")) {
            return fieldsList.get(SHIPPING_COST.ordinal()).getText();
        } else if (field.equals("store")) {
            return fieldsList.get(STORE.ordinal()).getText();
        } else if (field.equals("address")) {
            return fieldsList.get(ADDRESS.ordinal()).getText();
        } else if (field.equals("action")) {
            fieldsList.get(VARIABLE_OPTION.ordinal()).click();
        } else if (field.equals("coupon")) {
            return fieldsList.get(VARIABLE_OPTION.ordinal()).getText();
        }

        return "";
    }

    public ConfirmOrderModal clickRequestButton() {
        fieldsList.get(VARIABLE_OPTION.ordinal()).click();
        return new ConfirmOrderModal(modalOrderConfirmationContainer);
    }
}
