package commonoperations;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static driver.Drivers.getDriver;

public class CommonOperations {

    public CommonOperations() {

    }

    public static boolean isVisible(By by) {
        try {
            getDriver().findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public static boolean isVisible(WebElement element) {
        try {
            ExpectedConditions.visibilityOf(element);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public static void jsClick(WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) getDriver();
        executor.executeScript("arguments[0].click();", element);
    }

    public static String getInputValue(WebElement element) {
        return element.getAttribute("value");
    }
}
