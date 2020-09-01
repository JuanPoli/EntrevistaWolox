package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static java.util.concurrent.TimeUnit.SECONDS;
import static readproperty.ReadPropertyFile.TEST_PROPERTIES;

public class Drivers {

    public Drivers() {

    }

    public static void populateDriver() {
        if (webDriver == null) {

            webDriver = new ChromeDriver();
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.setBinary("EjercicioEntrevistaWolox\\chromedriver.exe");
            chromeOptions.addArguments("--start-maximized");
            webDriver.navigate().to(TEST_PROPERTIES.getBaseUrl());
            webDriver.manage().timeouts().pageLoadTimeout(10, SECONDS);
            webDriver.manage().timeouts().setScriptTimeout(10, SECONDS);
            webDriver.manage().timeouts().implicitlyWait(10, SECONDS);
            webDriver.manage().window().maximize();
        }
    }


    public static WebDriver getDriver() {
        return webDriver;
    }

    public static void dispose() {
        webDriver.quit();
    }

    private static WebDriver webDriver;
}
