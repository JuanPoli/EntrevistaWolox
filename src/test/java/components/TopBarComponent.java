package components;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.HomePage;
import pages.LoginPage;
import pages.PersonalInformationPage;
import tests.BaseComponent;

import java.util.List;

import static components.TopBarComponent.Options.*;

public class TopBarComponent extends BaseComponent {

    @FindBy(className = "nav-bar-item")
    private List<WebElement> topBarOptions;

    public enum Options {
        HOME, COUPONS, MY_ORDERS, PERSONAL_INFORMATION, WELCOME_COUPONS, LOG_OUT
    }

    public TopBarComponent(WebElement container) {
        super(container);
    }

    /*public CouponsPage couponsPage() {
        topBarOptions.get(COUPONS.ordinal()).click();
        return new CouponsPage();
    }

    public MyOrdersPage myOrdersPage() {
        topBarOptions.get(MY_ORDERS.ordinal()).click();
        return new MyOrdersPage();
    }*/

    public PersonalInformationPage personalInformationPage() {
        topBarOptions.get(PERSONAL_INFORMATION.ordinal()).click();
        return new PersonalInformationPage();
    }

    /*public WelcomeCouponsPage welcomeCouponsPage() {
        topBarOptions.get(WELCOME_COUPONS.ordinal()).click();
        return new WelcomeCouponsPage();
    }*/

    public LoginPage logout() {
        topBarOptions.get(LOG_OUT.ordinal()).click();
        return new LoginPage();
    }

    public HomePage homePage() {
        topBarOptions.get(HOME.ordinal()).click();
        return new HomePage();
    }
}
