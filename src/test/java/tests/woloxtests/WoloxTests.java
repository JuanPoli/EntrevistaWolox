package tests.woloxtests;

import components.*;
import components.commons.DatePickerComponent;
import components.commons.SucceedActionModal;
import components.personalInformation.PersonalInformationComponent;
import components.personalInformation.UploadImageComponent;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import pages.HomePage;
import pages.PersonalInformationPage;
import tests.BaseTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class WoloxTests extends BaseTest {

    private final static String MODAL_TEXT = "Tu información se guardó correctamente";
    private String oldImageName;
    private final static String NAME = "Juan Cruz";
    private final static String LAST_NAME = "Poli";
    private final static String COLOMBIA = "Colombia";
    private final static String ARGENTINA = "Argentina";
    private final static String YEAR = "1992";
    private final static String MONTH = "Aug";
    private final static String DAY = "7";
    private final static String DATE = "date";
    private final static String DESCRIPTION = "description";
    private final static String PRICE = "price";
    private final static String SHIPPING_COST = "shipping";
    private final static String STORE = "store";
    private final static String ADDRESS = "address";
    private final static String ACTIONS = "actions";


    @Test
    public void woloxInterviewPersonalInformationTest() {
        HomePage homePage = login();

        PersonalInformationPage personalInformationPage = homePage.getTopBarComponent().personalInformationPage();
        PersonalInformationComponent personalInfo = personalInformationPage.getPersonalInformationComponent();
        assertFalse("Cancel button is visible", personalInfo.isCancelButtonVisible());

        UploadImageComponent uploadImageComponent = personalInfo.getUploadImageComponent();
        oldImageName = uploadImageComponent.getImageName();
        uploadImageComponent.uploadImage();

        personalInfo.typeName(NAME);
        assertTrue("Cancel button is not visible", personalInfo.isCancelButtonVisible());
        assertTrue("Save button is not visible: ", personalInfo.isSaveButtonVisible());

        personalInfo.typeLastName(LAST_NAME);

        personalInfo.selectCountry(COLOMBIA);

        personalInfo.selectFemaleRadioButton();
        personalInfo.selectMaleRadioButton();

        personalInfo.selectCountry(ARGENTINA);


        DatePickerComponent datePicker = personalInfo.calendarComponent();
        datePicker.selectDayOfBirth(YEAR, MONTH, DAY);
        int monthNumber = datePicker.getMonthNumber(MONTH);
        String completeDate = DAY + "/" + monthNumber + "/" + YEAR;

        SucceedActionModal succeedActionModal = personalInfo.clickSaveButton();
        assertTrue("The message is not showing", succeedActionModal.isConfirmationMessageDisplayed());

        assertEquals("The text is not correct", succeedActionModal.getConfirmationMessageText(), MODAL_TEXT);

        homePage = succeedActionModal.clickOnCrossButton();

        homePage.getTopBarComponent().personalInformationPage();

        Assertions.assertAll("Changes in personal information asserts",
                () -> assertNotEquals("The image doesn't change", oldImageName, uploadImageComponent.getImageName()),
                () -> assertEquals("The name doesn't change", NAME, personalInfo.getNameInputText()),
                () -> assertEquals("The last name doesn't change", LAST_NAME, personalInfo.getLastNameInputText()),
                () -> assertEquals("The country doesn't change", personalInfo.getSelectedCountryIndex(), personalInfo.getCountrySelectorText()),
                () -> assertEquals("The birth date doesn't change", completeDate, personalInfo.getBirthDateInputText().replace("0", ""))
        );

    }

    @Test
    public void woloxInterviewRequestTest() {
        HomePage homePage = login();

        OffersComponent offersComponent = homePage.getOffersTableComponent();
        TableBodyComponent tableBodyComponent = offersComponent.getTableBodyComponent();

        RowTableComponent rowTableComponent = tableBodyComponent.getRowTableComponent(0);
        List<String> listOfOrderFields = Arrays.asList(
                rowTableComponent.getFieldText(DATE), rowTableComponent.getFieldText(DESCRIPTION), rowTableComponent.getFieldText(PRICE),
                rowTableComponent.getFieldText(SHIPPING_COST), rowTableComponent.getFieldText(STORE), rowTableComponent.getFieldText(ADDRESS)
        );

        ConfirmOrderModal confirmOrderModal = rowTableComponent.clickRequestButton();

        Assertions.assertAll("ALl fields are correct",
                () -> assertEquals("The ", listOfOrderFields.get(0), confirmOrderModal.getDateValue()),
                () -> assertEquals("The ", listOfOrderFields.get(1), confirmOrderModal.getTitleLabel()),
                () -> assertEquals("The ", listOfOrderFields.get(2), confirmOrderModal.getPriceTitleValue()),
                () -> assertEquals("The ", listOfOrderFields.get(3), confirmOrderModal.getShippingPriceValue()),
                () -> assertEquals("The ", listOfOrderFields.get(4), confirmOrderModal.getStoreValue()),
                () -> assertEquals("The ", listOfOrderFields.get(5), confirmOrderModal.getAddressValue())
        );


        SucceedActionModal succeedActionModal = confirmOrderModal.clickConfirmOrderButton();
        succeedActionModal.clickOnCrossButton();

    }
}
