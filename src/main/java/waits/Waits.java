package waits;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static driver.Drivers.getWebDriverWait;

public final class Waits {

    public Waits() {

    }

    public static WebElement waitForVisibilityOf(WebElement element) {
        return waiting().until(ExpectedConditions.visibilityOf(element));
    }

    public static WebElement waitForVisibilityOf(By by) {
        return waiting().until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public static boolean waitForInvisibilityOf(By by) {
        return waiting().until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    private static WebDriverWait waiting() {
        return getWebDriverWait();
    }

}
