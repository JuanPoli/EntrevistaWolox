package pages;

import components.orders.RowTableCouponsComponent;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CouponsPage extends BaseWappiPage {

    @FindBy(className = "app-table")
    private WebElement rowTableCouponsComponentContainer;

    public CouponsPage() {

    }

    public RowTableCouponsComponent getRowTableCouponsComponent() {
        return new RowTableCouponsComponent(rowTableCouponsComponentContainer);
    }
}
