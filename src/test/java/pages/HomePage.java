package pages;

import components.OffersComponent;
import components.commons.TopBarComponent;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import web.BasePage;

public class HomePage extends BasePage {

    @FindBy(className = "nav-bar")
    private WebElement topBarComponentContainer;

    @FindBy(className = "app-table")
    private WebElement offersTableComponentContainer;

    public HomePage() {
        super();
    }

    public TopBarComponent getTopBarComponent() {
        return new TopBarComponent(topBarComponentContainer);
    }

    public OffersComponent getOffersTableComponent() {
        return new OffersComponent(offersTableComponentContainer);
    }
}
