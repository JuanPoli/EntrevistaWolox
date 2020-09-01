package tests.woloxtests;

import components.DatePickerComponent;
import components.SavedInformationModal;
import components.personalInformation.PersonalInformationComponent;
import org.junit.Test;
import pages.HomePage;
import pages.PersonalInformationPage;
import tests.BaseTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class WoloxTests extends BaseTest {

    private final static String MODAL_TEXT = "Tu información se guardó correctamente";


    @Test
    public void woloxInterviewTest() {
        HomePage homePage = login();

        PersonalInformationPage personalInformationPage = homePage.getTopBarComponent().personalInformationPage();
        PersonalInformationComponent personalInfo = personalInformationPage.getPersonalInformationComponent();
        assertFalse("Cancel button is visible", personalInfo.isCancelButtonVisible());

        personalInfo.getUploadImageComponent().uploadImage();

        personalInfo.typeName("Juan Cruz");
        assertTrue("Cancel button is not visible", personalInfo.isCancelButtonVisible());
        assertTrue("Save button is not visible: ", personalInfo.isSaveButtonVisible());

        personalInfo.typeLastName("Poli");

        personalInfo.selectCountry("Colombia");

        personalInfo.selectFemaleRadioButton();
        personalInfo.selectMaleRadioButton();

        personalInfo.selectCountry("Argentina");


        DatePickerComponent datePicker = personalInfo.calendarComponent();
        datePicker.selectDayOfBirth("1992", "Aug", "7");

        SavedInformationModal savedInformationModal = personalInfo.clickSaveButton();
        assertTrue("The message is not showing", savedInformationModal.isConfirmationMessageDisplayed());

        assertTrue("The text is not correct", savedInformationModal.getConfirmationMessageText().equals(MODAL_TEXT));

        personalInformationPage = savedInformationModal.clickOnCrossButton();

    }
}
