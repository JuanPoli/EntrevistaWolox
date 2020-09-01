package tests;

import driver.Drivers;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

    public BasePage() {
        WebDriver driver = Drivers.getDriver();
        PageFactory.initElements(driver, this);
    }
}
