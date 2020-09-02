package components;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import web.BaseComponent;

public class OffersComponent extends BaseComponent {

    @FindBy(tagName = "tbody")
    private WebElement tableBodyContainer;

    public OffersComponent(WebElement container) {
        super(container);
    }

    public TableBodyComponent getTableBodyComponent() {
        return new TableBodyComponent(tableBodyContainer);
    }
}
