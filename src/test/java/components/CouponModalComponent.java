package components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.CouponsPage;
import web.BaseComponent;

import static driver.Drivers.getDriver;

public class CouponModalComponent extends BaseComponent {

    private WebElement couponCodeValue;
    private WebElement closeButton;

    public CouponModalComponent(WebElement container) {
        super(container);
        couponCodeValue = getDriver().findElement(By.id("coupon-code"));
        closeButton = getDriver().findElement(By.className("close"));
    }

    public String getCouponCode() {
        return couponCodeValue.getText();
    }

    public CouponsPage clickCloseButton () {
        closeButton.click();
        return new CouponsPage();
    }

}
