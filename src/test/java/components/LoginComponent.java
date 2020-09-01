package components;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.HomePage;
import tests.BaseComponent;

public class LoginComponent extends BaseComponent {

    @FindBy(id = "username")
    private WebElement usernameInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "button-login")
    private WebElement loginButton;

    public LoginComponent(WebElement container) {
        super(container);
    }

    public LoginComponent typeUsername(String username) {
        usernameInput.sendKeys(username);
        return this;
    }

    public LoginComponent typePassword(String password) {
        passwordInput.sendKeys(password);
        return this;
    }

    public HomePage clickLoginButton() {
        loginButton.click();
        return new HomePage();
    }

    public boolean isLoginButtonEnabled() {
        return loginButton.isEnabled();
    }


}
