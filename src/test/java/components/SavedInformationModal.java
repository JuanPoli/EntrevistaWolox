package components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.PersonalInformationPage;
import tests.BaseComponent;

import static commonoperations.CommonOperations.isVisible;
import static commonoperations.CommonOperations.jsClick;
import static driver.Drivers.getDriver;

public class SavedInformationModal extends BaseComponent {

    private WebElement confirmationMessageText;
    private WebElement crossButton;

    public SavedInformationModal(WebElement container) {
        super(container);
        confirmationMessageText = getDriver().findElement(By.className("confirmation-modal-info"));
        crossButton = getDriver().findElement(By.className("close"));
    }

    public boolean isConfirmationMessageDisplayed() {
        return isVisible(confirmationMessageText);
    }

    public PersonalInformationPage clickOnCrossButton() {
        jsClick(crossButton);
        return new PersonalInformationPage();
    }

    public String getConfirmationMessageText() {
        return confirmationMessageText.getText();
    }


}
