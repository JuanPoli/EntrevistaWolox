package components.personalInformation;

import components.commons.DatePickerComponent;
import components.commons.SucceedActionModal;
import components.commons.Select;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import web.BaseComponent;

import static commonoperations.CommonOperations.getInputValue;
import static commonoperations.CommonOperations.isVisible;

public class PersonalInformationComponent extends BaseComponent {

    @FindBy(className = "file-upload-container")
    private WebElement uploadImageComponentContainer;

    @FindBy(css = "modal-content")
    private WebElement succeedActionModalContainer;

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

    @FindBy(id = "M")
    private WebElement radioButtonMaleGender;

    @FindBy(id = "F")
    private WebElement radioButtonFemaleGender;

    @FindBy(className = "selector")
    private WebElement CalendarComponentContainer;

    @FindBy(id = "cancel-changes")
    private WebElement cancelButton;

    @FindBy(id = "save-profile")
    private WebElement saveButton;

    private int selectedCountryIndex;

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
        selectedCountryIndex = selector.getIndexOfSelectedItem();
        return this;
    }

    public PersonalInformationComponent selectMaleRadioButton() {
        radioButtonMaleGender.click();
        return this;
    }

    public PersonalInformationComponent selectFemaleRadioButton() {
        radioButtonFemaleGender.click();
        return this;
    }

    public UploadImageComponent getUploadImageComponent() {
        return new UploadImageComponent(uploadImageComponentContainer);
    }

    public SucceedActionModal clickSaveButton() {
        saveButton.click();
        return new SucceedActionModal(succeedActionModalContainer);
    }

    public boolean isCancelButtonVisible() {
        return isVisible(By.id("cancel-changes"));
    }

    public boolean isSaveButtonVisible() {
        return saveButton.isDisplayed();
    }

    public String getNameInputText() {
        return getInputValue(nameInput);
    }

    public String getLastNameInputText() {
        return getInputValue(lastNameInput);
    }

    public String getBirthDateInputText() {
        return getInputValue(dateOfBirthInput);
    }

    public String getCountrySelectorText() {
        return getInputValue(countrySelectorContainer);
    }

    public String getSelectedCountryIndex() {
        return String.valueOf(selectedCountryIndex);
    }

}
