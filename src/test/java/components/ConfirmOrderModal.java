package components;

import components.commons.SucceedActionModal;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import web.BaseComponent;

import java.util.List;

import static components.ConfirmOrderModal.OfferInfoElements.*;
import static driver.Drivers.getDriver;

public class ConfirmOrderModal extends BaseComponent {

    private final WebElement offerTitleLabel;
    private final WebElement offerPriceLabel;
    private final List<WebElement> offerInfo;
    private final WebElement couponNumberInput;
    private final WebElement confirmOrderButton;
    private final WebElement succeedActionModalContainer;

    public enum OfferInfoElements {
        DATE, PRICE, STORE, ADDRESS
    };

    public ConfirmOrderModal(WebElement container) {
        super(container);
        offerTitleLabel = getDriver().findElement(By.className("offer-description"));
        offerPriceLabel = getDriver().findElement(By.className("offer-price"));
        offerInfo = getDriver().findElements(By.className("info"));
        couponNumberInput = getDriver().findElement(By.className("input"));
        confirmOrderButton = getDriver().findElement(By.id("order-confirm"));
        succeedActionModalContainer = getDriver().findElement(By.id("confirmation-modal"));
    }

    public String getTitleLabel() {
        return offerTitleLabel.getText();
    }

    public String getPriceTitleValue() {
        return offerPriceLabel.getText();
    }

    public String getDateValue() {
        return offerInfo.get(DATE.ordinal()).getText().split("\n")[1];
    }

    public String getShippingPriceValue() {
        return offerInfo.get(PRICE.ordinal()).getText().split("\n")[1];
    }

    public String getStoreValue() {
        return offerInfo.get(STORE.ordinal()).getText().split("\n")[1];
    }

    public String getAddressValue() {
        return offerInfo.get(ADDRESS.ordinal()).getText().split("\n")[1];
    }

    public ConfirmOrderModal typeCouponValue(String value) {
        couponNumberInput.sendKeys(value);
        return this;
    }

    public SucceedActionModal clickConfirmOrderButton() {
        confirmOrderButton.click();
        return new SucceedActionModal(succeedActionModalContainer);
    }
}
