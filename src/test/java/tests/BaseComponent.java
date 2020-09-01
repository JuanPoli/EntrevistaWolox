package tests;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

public class BaseComponent {

    private WebElement container;

    protected BaseComponent() {

    }

    protected BaseComponent(WebElement container)
    {
        this.container = container;
        PageFactory.initElements(new DefaultElementLocatorFactory(container), this);
    }
}
