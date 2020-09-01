package pages;

import components.personalInformation.PersonalInformationComponent;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import tests.BasePage;

public class PersonalInformationPage extends BasePage {

    @FindBy(className = "profile-form")
    private WebElement personalInformationComponentContainer;

    @FindBy(className = "app-title")
    private WebElement personalInformationTitle;

    public PersonalInformationPage() {
        super();
    }

    public PersonalInformationComponent getPersonalInformationComponent() {
        return new PersonalInformationComponent(personalInformationComponentContainer);
    }

    public boolean isPersonalInformationTitleDisplayed() {
        return personalInformationTitle.isDisplayed();
    }
}
