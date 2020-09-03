package components.commons;

import components.orders.CouponModalComponent;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.*;
import web.BaseComponent;

import java.util.List;

import static components.commons.TopBarComponent.Options.*;

public class TopBarComponent extends BaseComponent {

    @FindBy(className = "nav-bar-item")
    private List<WebElement> topBarOptions;

    @FindBy(id = "coupon-modal")
    private WebElement couponModalContainer;

    public enum Options {
        HOME, COUPONS, MY_ORDERS, PERSONAL_INFORMATION, WELCOME_COUPONS, LOG_OUT
    }

    public TopBarComponent(WebElement container) {
        super(container);
    }

    public CouponsPage couponsPage() {
        topBarOptions.get(COUPONS.ordinal()).click();
        return new CouponsPage();
    }

    public OrdersPage myOrdersPage() {
        topBarOptions.get(MY_ORDERS.ordinal()).click();
        return new OrdersPage();
    }

    public PersonalInformationPage personalInformationPage() {
        topBarOptions.get(PERSONAL_INFORMATION.ordinal()).click();
        return new PersonalInformationPage();
    }

    public CouponModalComponent clickObtainWelcomeCoupons() {
        topBarOptions.get(WELCOME_COUPONS.ordinal()).click();
        return new CouponModalComponent(couponModalContainer);
    }

    public LoginPage logout() {
        topBarOptions.get(LOG_OUT.ordinal()).click();
        return new LoginPage();
    }

    public HomePage homePage() {
        topBarOptions.get(HOME.ordinal()).click();
        return new HomePage();
    }
}
