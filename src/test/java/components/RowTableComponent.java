package components;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import web.BaseComponent;

import java.util.List;

import static components.RowTableComponent.RowElements.*;

public class RowTableComponent extends BaseComponent {

    @FindBy(tagName = "td")
    private List<WebElement> fieldsList;

    @FindBy(className = "modal-content")
    private WebElement modalOrderConfirmationContainer;


    public enum RowElements {
        DATE, DESCRIPTION, PRICE, SHIPPING_COST, STORE, ADDRESS, ACTIONS
    };

    public RowTableComponent(WebElement container) {
        super(container);
    }

    public String getFieldText(String field) {
        field = field.toLowerCase();

        switch (field) {
            case "date":
                return fieldsList.get(DATE.ordinal()).getText();
            case "description":
                return fieldsList.get(DESCRIPTION.ordinal()).getText();
            case "price":
                return fieldsList.get(PRICE.ordinal()).getText();
            case "shipping":
                return fieldsList.get(SHIPPING_COST.ordinal()).getText();
            case "store":
                return fieldsList.get(STORE.ordinal()).getText();
            case "address":
                return fieldsList.get(ADDRESS.ordinal()).getText();
            case "actions":
                fieldsList.get(ACTIONS.ordinal()).click();
                break;
        }

        return "";
    }

    public ConfirmOrderModal clickRequestButton() {
        fieldsList.get(ACTIONS.ordinal()).click();
        return new ConfirmOrderModal(modalOrderConfirmationContainer);
    }
}
