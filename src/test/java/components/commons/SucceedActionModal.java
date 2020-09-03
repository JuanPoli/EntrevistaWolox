package components.commons;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.HomePage;
import waits.Waits;
import web.BaseComponent;

import static commonoperations.CommonOperations.isVisible;
import static commonoperations.CommonOperations.jsClick;
import static driver.Drivers.getDriver;

public class SucceedActionModal extends BaseComponent {

    private WebElement confirmationMessageText;
    private WebElement crossButton;

    public SucceedActionModal(WebElement container) {
        super(container);
        confirmationMessageText = getDriver().findElement(By.className("confirmation-modal-info"));
        crossButton = container.findElement(By.className("close"));
        Waits.waitForInvisibilityOf(By.className("lds-dual-ring"));
    }

    public boolean isConfirmationMessageDisplayed() {
        return isVisible(confirmationMessageText);
    }

    public HomePage clickOnCrossButton() {
        jsClick(crossButton);
        return new HomePage();
    }

    public String getConfirmationMessageText() {
        return confirmationMessageText.getText();
    }

}
