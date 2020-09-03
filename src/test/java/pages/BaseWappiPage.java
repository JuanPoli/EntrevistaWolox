package pages;

import components.commons.TopBarComponent;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import web.BasePage;

public class BaseWappiPage extends BasePage {

    @FindBy(className = "nav-bar")
    private WebElement topBarComponentContainer;

    @FindBy(className = "app-table")
    protected WebElement tableComponentContainer;

    public BaseWappiPage() {
        super();
    }


    public TopBarComponent getTopBarComponent() {
        return new TopBarComponent(topBarComponentContainer);
    }
}
