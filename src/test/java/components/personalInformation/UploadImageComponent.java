package components.personalInformation;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import tests.BaseComponent;

import java.io.File;

import static readproperty.ReadPropertyFile.TEST_PROPERTIES;

public class UploadImageComponent extends BaseComponent {

    @FindBy(css = ".file-upload input")
    private WebElement uploadImageButton;

    public UploadImageComponent(WebElement container) {
        super(container);
    }

    public UploadImageComponent uploadImage() {
        File file = new File(TEST_PROPERTIES.getImagePath());
        uploadImageButton.sendKeys(file.getAbsolutePath());
        return this;
    }

}
