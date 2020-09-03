package tests.woloxtests;

import components.orders.ConfirmOrderModal;
import components.orders.CouponModalComponent;
import components.orders.RowTableComponent;
import components.commons.TableBodyComponent;
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

import static java.lang.Integer.parseInt;
import static java.lang.String.valueOf;
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
    private final static String ACTION = "action";
    private final static String COUPON = "coupon";
    private final static String YES = "Si";
    private final static String NO = "No";
    private final static String COUPON_CODE = "code";
    private final static String COUPON_USES = "uses";
    private int ordersCount = -1;
    private int couponUsesCount = 2;
    private final static String EMPTY_COUPONS_TEXT = "No se encontraron registros";
    private final static String ERROR_MESSAGE = "Cupón inválido";

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
        String couponCode = couponsPage.getRowTableCouponsComponent().getFieldText(COUPON_CODE);

        Assertions.assertAll("ALl fields are correct",
                () -> assertEquals("The coupon code is not the same ", couponCode, couponCodeValue),
                () -> assertEquals("The number of times of coupon uses is not correct ", parseInt(couponsPage.getRowTableCouponsComponent()
                        .getFieldText(COUPON_USES)), couponUsesCount)
        );

        couponsPage.getTopBarComponent().homePage();
        rowTableComponent = tableBodyComponent.getRowTableComponent(2);
        makeAndCheckOrdersWithWelcomeCoupon(rowTableComponent, topBarComponent, couponCodeValue);

        topBarComponent.couponsPage();
        assertEquals("The number of times that the coupons was used is not correct", valueOf(couponUsesCount), couponsPage.getRowTableCouponsComponent()
                .getFieldText(COUPON_USES));

        couponsPage.getTopBarComponent().homePage();
        rowTableComponent = tableBodyComponent.getRowTableComponent(1);
        makeAndCheckOrdersWithWelcomeCoupon(rowTableComponent, topBarComponent, couponCodeValue);

        topBarComponent.couponsPage();
        assertEquals("The table still shows a valid coupon", couponsPage.getRowTableCouponsComponent().getEmptyCode().split("\n")[1], EMPTY_COUPONS_TEXT);

        couponsPage.getTopBarComponent().homePage();
        rowTableComponent = tableBodyComponent.getRowTableComponent(0);
        makeAndCheckOrdersWithWelcomeCoupon(rowTableComponent, topBarComponent, couponCodeValue);
    }


    private void makeAndCheckOrders(RowTableComponent rowTableComponent, TopBarComponent topBarComponent) {
        ordersCount++;

        List<String> listOfOffersFields = getOffersPageFieldsTableList(rowTableComponent);

        ConfirmOrderModal confirmOrderModal = fieldsModalComparision(rowTableComponent, listOfOffersFields);

        SucceedActionModal succeedActionModal = confirmOrderModal.clickConfirmOrderButton();
        succeedActionModal.clickOnCrossButton();

        OrdersPage ordersPage = topBarComponent.myOrdersPage();
        checkOrdersListOffersPage(listOfOffersFields, ordersPage);

    }

    private void checkOrdersListOffersPage(List<String> lisOfFields, OrdersPage ordersPage) {

        RowTableComponent ordersRowTableComponent = ordersPage.getOrdersTableComponent().getTableBodyComponent().getRowTableComponent(ordersCount);
        fieldsCouponComparision(ordersRowTableComponent, lisOfFields, NO);

        ordersPage.getTopBarComponent().homePage();
    }

    private void makeAndCheckOrdersWithWelcomeCoupon(RowTableComponent rowTableComponent, TopBarComponent topBarComponent, String couponValue) {
        ordersCount++;
        couponUsesCount--;
        if (couponUsesCount < -1)
            couponUsesCount = 2;

        List<String> listOfOffersFields = getOffersPageFieldsTableList(rowTableComponent);

        ConfirmOrderModal confirmOrderModal = fieldsModalComparision(rowTableComponent, listOfOffersFields);

        confirmOrderModal.typeCouponValue(couponValue);

        if (couponUsesCount == -1) {
            confirmOrderModal.clickConfirmOrderButton();
            assertEquals("The error message is not shown or is invalid", confirmOrderModal.getInvalidCouponErrorMessage(), ERROR_MESSAGE);
            confirmOrderModal.clickCloseButton();
        } else {
            SucceedActionModal succeedActionModal = confirmOrderModal.clickConfirmOrderButton();
            succeedActionModal.clickOnCrossButton();

            OrdersPage ordersPage = topBarComponent.myOrdersPage();
            checkOrdersListWithWelcomeCouponCouponsPage(listOfOffersFields, ordersPage);
        }
    }

    private void checkOrdersListWithWelcomeCouponCouponsPage(List<String> lisOfFields, OrdersPage ordersPage) {
        RowTableComponent ordersRowTableComponent = ordersPage.getOrdersTableComponent().getTableBodyComponent().getRowTableComponent(ordersCount);
        List<String> listOfOrderFields = getOffersPageFieldsTableList(ordersRowTableComponent);

        fieldsCouponComparision(ordersRowTableComponent, listOfOrderFields, YES);
        ordersPage.getTopBarComponent().homePage();
    }

    private List<String> getOffersPageFieldsTableList(RowTableComponent rowTableComponent) {
        List<String> listOfOffersFields = Arrays.asList(
                rowTableComponent.getFieldText(DATE), rowTableComponent.getFieldText(DESCRIPTION), rowTableComponent.getFieldText(PRICE),
                rowTableComponent.getFieldText(SHIPPING_COST), rowTableComponent.getFieldText(STORE), rowTableComponent.getFieldText(ADDRESS),
                rowTableComponent.getFieldText(COUPON)
        );
        return listOfOffersFields;
    }

    private ConfirmOrderModal fieldsModalComparision(RowTableComponent rowTableComponent, List<String> listOfOffersFields) {

        ConfirmOrderModal confirmOrderModal = rowTableComponent.clickRequestButton();
        Assertions.assertAll("Some field(s) failed",
                () -> assertEquals("The table date value is not the same as the confirmationModal", listOfOffersFields.get(0), confirmOrderModal.getDateValue()),
                () -> assertEquals("The table title label is not the same as the confirmationModal", listOfOffersFields.get(1), confirmOrderModal.getTitleLabel()),
                () -> assertEquals("The table price title is not the same as the confirmationModal", listOfOffersFields.get(2), confirmOrderModal.getPriceTitleValue()),
                () -> assertEquals("The table shipping price value is not the same as the confirmationModal", listOfOffersFields.get(3), confirmOrderModal.getShippingPriceValue()),
                () -> assertEquals("The store value is not the same as the confirmationModal", listOfOffersFields.get(4), confirmOrderModal.getStoreValue()),
                () -> assertEquals("The address value is not the same as the confirmationModal", listOfOffersFields.get(5), confirmOrderModal.getAddressValue())
        );
        return confirmOrderModal;
    }

    private void fieldsCouponComparision(RowTableComponent rowTableComponent, List<String> listOfOffersFields, String withCoupon) {

        List<String> lisOfFields = getOffersPageFieldsTableList(rowTableComponent);
        Assertions.assertAll("Some field(s) failed",
                () -> assertEquals("The table date value is not the same as the coupons page table", listOfOffersFields.get(0), lisOfFields.get(0)),
                () -> assertEquals("The table date value is not the same as the coupons page table", listOfOffersFields.get(1), lisOfFields.get(1)),
                () -> assertEquals("The table date value is not the same as the coupons page table", listOfOffersFields.get(2), lisOfFields.get(2)),
                () -> assertEquals("The table date value is not the same as the coupons page table", listOfOffersFields.get(3), lisOfFields.get(3)),
                () -> assertEquals("The table date value is not the same as the coupons page table", listOfOffersFields.get(4), lisOfFields.get(4)),
                () -> assertEquals("The table date value is not the same as the coupons page table", listOfOffersFields.get(5), lisOfFields.get(5)),
                () -> assertEquals("The coupon usage information is not the correct one", lisOfFields.get(6), withCoupon)
        );
    }
}
