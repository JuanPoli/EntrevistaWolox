package components.personalInformation;

import components.DatePickerComponent;
import components.SavedInformationModal;
import components.Select;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import tests.BaseComponent;

import java.util.List;

import static commonoperations.CommonOperations.isVisible;
import static components.personalInformation.PersonalInformationComponent.Genders.FEMALE;
import static components.personalInformation.PersonalInformationComponent.Genders.MALE;

public class PersonalInformationComponent extends BaseComponent {

    @FindBy(className = "file-upload-container")
    private WebElement uploadImageComponentContainer;

    @FindBy(css = "#confirmation-modal > div")
    private WebElement savedInformationModalContainer;

    @FindBy(id = "name")
    private WebElement nameInput;

    @FindBy(id = "lastName")
    private WebElement lastNameInput;

    @FindBy(id = "bornDate")
    private WebElement dateOfBirthInput;

    @FindBy(className = "btnpicker")
    private WebElement datePickerButton;

    @FindBy(className = "select-container")
    private WebElement countrySelector;

    @FindBy(id = "country")
    private WebElement countrySelectorContainer;

    @FindBy(className = "radio")
    private List<WebElement> radioButtonsGender;

    @FindBy(className = "selector")
    private WebElement CalendarComponentContainer;

    @FindBy(id = "cancel-changes")
    private WebElement cancelButton;

    @FindBy(id = "save-profile")
    private WebElement saveButton;


    public enum Genders {
        MALE, FEMALE
    }

    public PersonalInformationComponent(WebElement container) {
        super(container);
    }

    public PersonalInformationComponent typeName(String name) {
        nameInput.clear();
        nameInput.sendKeys(name);
        return this;
    }

    public PersonalInformationComponent typeLastName(String name) {
        lastNameInput.clear();
        lastNameInput.sendKeys(name);
        return this;
    }

    public DatePickerComponent calendarComponent() {
        datePickerButton.click();
        return new DatePickerComponent(CalendarComponentContainer);
    }

    public PersonalInformationComponent selectCountry(String country) {
        countrySelector.click();
        Select selector = new Select(countrySelectorContainer);
        selector.selectByValue(country);
        return this;
    }

    public PersonalInformationComponent selectMaleRadioButton() {
        radioButtonsGender.get(MALE.ordinal()).click();
        return this;
    }

    public PersonalInformationComponent selectFemaleRadioButton() {
        radioButtonsGender.get(FEMALE.ordinal()).click();
        return this;
    }

    public UploadImageComponent getUploadImageComponent() {
        return new UploadImageComponent(uploadImageComponentContainer);
    }

    public SavedInformationModal clickSaveButton() {
        saveButton.click();
        return new SavedInformationModal(savedInformationModalContainer);
    }

    public boolean isCancelButtonVisible() {
        return isVisible(By.id("cancel-changes"));
    }

    public boolean isSaveButtonVisible() {
        return saveButton.isDisplayed();
    }
}
