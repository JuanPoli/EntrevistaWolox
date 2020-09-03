package components.commons;

import components.TableBodyComponent;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import web.BaseComponent;

public class TableComponent extends BaseComponent {

    @FindBy(tagName = "tbody")
    private WebElement tableBodyContainer;

    public TableComponent(WebElement container) {
        super(container);
    }

    public TableBodyComponent getTableBodyComponent() {
        return new TableBodyComponent(tableBodyContainer);
    }
}
