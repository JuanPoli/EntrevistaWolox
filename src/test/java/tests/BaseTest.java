package tests;

import components.orders.LoginComponent;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import pages.HomePage;
import pages.LoginPage;

import static driver.Drivers.dispose;
import static driver.Drivers.populateDriver;
import static readproperty.ReadPropertyFile.TEST_PROPERTIES;

public class BaseTest {

    protected HomePage login() {
        LoginPage loginPage = new LoginPage();
        LoginComponent loginComponent = loginPage.getLoginComponent();
        Assert.assertFalse("The login button is enabled", loginComponent.isLoginButtonEnabled());

        loginComponent.typeUsername(TEST_PROPERTIES.getUsername());
        loginComponent.typePassword(TEST_PROPERTIES.getPassword());

        return loginComponent.clickLoginButton();
    }

    @Before
    public void startBrowser() {
        try {
            populateDriver();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @After
    public void closeBrowser() {
        dispose();
    }
}
