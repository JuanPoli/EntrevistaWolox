package tests.woloxtests;

import components.ConfirmOrderModal;
import components.CouponModalComponent;
import components.RowTableComponent;
import components.TableBodyComponent;
import components.commons.DatePickerComponent;
import components.commons.SucceedActionModal;
import components.commons.TableComponent;
import components.commons.TopBarComponent;
import components.personalInformation.PersonalInformationComponent;
import components.personalInformation.UploadImageComponent;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import pages.CouponsPage;
import pages.HomePage;
import pages.OrdersPage;
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
    private final static String VARIABLE_OPTION = "variable";
    private int ordersCount = -1;


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

        TableComponent tableComponent = homePage.getOffersTableComponent();
        TableBodyComponent tableBodyComponent = tableComponent.getTableBodyComponent();

        TopBarComponent topBarComponent = homePage.getTopBarComponent();

        RowTableComponent rowTableComponent = tableBodyComponent.getRowTableComponent(0);
        makeAndCheckOrders(rowTableComponent, topBarComponent);

        rowTableComponent = tableBodyComponent.getRowTableComponent(1);
        makeAndCheckOrders(rowTableComponent, topBarComponent);

        rowTableComponent = tableBodyComponent.getRowTableComponent(2);
        makeAndCheckOrders(rowTableComponent, topBarComponent);

        topBarComponent.personalInformationPage();
        CouponModalComponent couponModalComponent = topBarComponent.clickObtainWelcomeCoupons();
        String couponCodeValue = couponModalComponent.getCouponCode();


        CouponsPage couponsPage = couponModalComponent.clickCloseButton();
        couponsPage.getTopBarComponent().homePage();
        rowTableComponent = tableBodyComponent.getRowTableComponent(2);
        makeAndCheckOrdersWithWelcomeCoupon(rowTableComponent, topBarComponent, couponCodeValue);

        rowTableComponent = tableBodyComponent.getRowTableComponent(1);
        makeAndCheckOrdersWithWelcomeCoupon(rowTableComponent, topBarComponent, couponCodeValue);

    }


    private void makeAndCheckOrders(RowTableComponent rowTableComponent, TopBarComponent topBarComponent) {
        ordersCount++;

        //Duplicate Code * ///////////////////////////////////////////
        List<String> listOfOffersFields = Arrays.asList(
                rowTableComponent.getFieldText(DATE), rowTableComponent.getFieldText(DESCRIPTION), rowTableComponent.getFieldText(PRICE),
                rowTableComponent.getFieldText(SHIPPING_COST), rowTableComponent.getFieldText(STORE), rowTableComponent.getFieldText(ADDRESS)
        );

        ConfirmOrderModal confirmOrderModal = rowTableComponent.clickRequestButton();

        Assertions.assertAll("ALl fields are correct",
                () -> assertEquals("The ", listOfOffersFields.get(0), confirmOrderModal.getDateValue()),
                () -> assertEquals("The ", listOfOffersFields.get(1), confirmOrderModal.getTitleLabel()),
                () -> assertEquals("The ", listOfOffersFields.get(2), confirmOrderModal.getPriceTitleValue()),
                () -> assertEquals("The ", listOfOffersFields.get(3), confirmOrderModal.getShippingPriceValue()),
                () -> assertEquals("The ", listOfOffersFields.get(4), confirmOrderModal.getStoreValue()),
                () -> assertEquals("The ", listOfOffersFields.get(5), confirmOrderModal.getAddressValue())
        );

        //////////////////////////////////////////////////////

        SucceedActionModal succeedActionModal = confirmOrderModal.clickConfirmOrderButton();
        succeedActionModal.clickOnCrossButton();

        OrdersPage ordersPage = topBarComponent.myOrdersPage();
        checkOrdersList(listOfOffersFields, ordersPage);

    }

    private void checkOrdersList(List<String> lisOfFields, OrdersPage ordersPage) {

        RowTableComponent ordersRowTableComponent = ordersPage.getOrdersTableComponent().getTableBodyComponent().getRowTableComponent(ordersCount);

        // Duplicate Code * ///////////////////////////////////
        List<String> listOfOrderFields = Arrays.asList(
                ordersRowTableComponent.getFieldText(DATE), ordersRowTableComponent.getFieldText(DESCRIPTION), ordersRowTableComponent.getFieldText(PRICE),
                ordersRowTableComponent.getFieldText(SHIPPING_COST), ordersRowTableComponent.getFieldText(STORE), ordersRowTableComponent.getFieldText(ADDRESS)
        );

        Assertions.assertAll("ALl fields are correct",
                () -> assertEquals("The ", listOfOrderFields.get(0), lisOfFields.get(0)),
                () -> assertEquals("The ", listOfOrderFields.get(1), lisOfFields.get(1)),
                () -> assertEquals("The ", listOfOrderFields.get(2), lisOfFields.get(2)),
                () -> assertEquals("The ", listOfOrderFields.get(3), lisOfFields.get(3)),
                () -> assertEquals("The ", listOfOrderFields.get(4), lisOfFields.get(4)),
                () -> assertEquals("The ", listOfOrderFields.get(5), lisOfFields.get(5))
        );

        //////////////////////////////////////////////////////

        ordersPage.getTopBarComponent().homePage();

    }

    private void makeAndCheckOrdersWithWelcomeCoupon(RowTableComponent rowTableComponent, TopBarComponent topBarComponent, String couponValue) {
        ordersCount++;

        //Duplicate Code * ///////////////////////////////////////////
        List<String> listOfOffersFields = Arrays.asList(
                rowTableComponent.getFieldText(DATE), rowTableComponent.getFieldText(DESCRIPTION), rowTableComponent.getFieldText(PRICE),
                rowTableComponent.getFieldText(SHIPPING_COST), rowTableComponent.getFieldText(STORE), rowTableComponent.getFieldText(ADDRESS)
        );

        ConfirmOrderModal confirmOrderModal = rowTableComponent.clickRequestButton();

        Assertions.assertAll("ALl fields are correct",
                () -> assertEquals("The ", listOfOffersFields.get(0), confirmOrderModal.getDateValue()),
                () -> assertEquals("The ", listOfOffersFields.get(1), confirmOrderModal.getTitleLabel()),
                () -> assertEquals("The ", listOfOffersFields.get(2), confirmOrderModal.getPriceTitleValue()),
                () -> assertEquals("The ", listOfOffersFields.get(3), confirmOrderModal.getShippingPriceValue()),
                () -> assertEquals("The ", listOfOffersFields.get(4), confirmOrderModal.getStoreValue()),
                () -> assertEquals("The ", listOfOffersFields.get(5), confirmOrderModal.getAddressValue())
        );

        //////////////////////////////////////////////////////

        confirmOrderModal.typeCouponValue(couponValue);
        SucceedActionModal succeedActionModal = confirmOrderModal.clickConfirmOrderButton();
        succeedActionModal.clickOnCrossButton();

        OrdersPage ordersPage = topBarComponent.myOrdersPage();
        checkOrdersListWithCoupons(listOfOffersFields, ordersPage);
    }

    public void checkOrdersListWithCoupons(List<String> lisOfFields, OrdersPage ordersPage) {

        RowTableComponent ordersRowTableComponent = ordersPage.getOrdersTableComponent().getTableBodyComponent().getRowTableComponent(ordersCount);

        // Duplicate Code * ///////////////////////////////////
        List<String> listOfOrderFields = Arrays.asList(
                ordersRowTableComponent.getFieldText(DATE), ordersRowTableComponent.getFieldText(DESCRIPTION), ordersRowTableComponent.getFieldText(PRICE),
                ordersRowTableComponent.getFieldText(SHIPPING_COST), ordersRowTableComponent.getFieldText(STORE), ordersRowTableComponent.getFieldText(ADDRESS),
                ordersRowTableComponent.getFieldText(VARIABLE_OPTION)
        );

        Assertions.assertAll("ALl fields are correct",
                () -> assertEquals("The ", listOfOrderFields.get(0), lisOfFields.get(0)),
                () -> assertEquals("The ", listOfOrderFields.get(1), lisOfFields.get(1)),
                () -> assertEquals("The ", listOfOrderFields.get(2), lisOfFields.get(2)),
                () -> assertEquals("The ", listOfOrderFields.get(3), lisOfFields.get(3)),
                () -> assertEquals("The ", listOfOrderFields.get(4), lisOfFields.get(4)),
                () -> assertEquals("The ", listOfOrderFields.get(5), lisOfFields.get(5)),
                () -> assertEquals("The ", listOfOrderFields.get(6), "Si")
        );

        //////////////////////////////////////////////////////

        ordersPage.getTopBarComponent().homePage();

    }
}
