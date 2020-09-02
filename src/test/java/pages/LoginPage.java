package pages;

import components.LoginComponent;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import web.BasePage;

public class LoginPage extends BasePage {

    @FindBy(className = "login-box")
    private WebElement loginComponentContainer;

    public LoginPage() {
        super();
    }

    public LoginComponent getLoginComponent() {
        return new LoginComponent(loginComponentContainer);
    }

}
