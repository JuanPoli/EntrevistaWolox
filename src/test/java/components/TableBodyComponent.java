package components;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import web.BaseComponent;

import java.util.List;

public class TableBodyComponent extends BaseComponent {

    @FindBy(tagName = "tr" )
    private List<WebElement> rowListContainer;

    @FindBy(tagName = "td")
    private List<WebElement> fieldElementsList;

    private RowTableComponent rowTableComponent;
    private int rowIndex;

    public TableBodyComponent(WebElement container) {
        super(container);
    }

    public RowTableComponent getRowTableComponent(int idx) {
        return rowTableComponent = new RowTableComponent(rowListContainer.get(idx));
    }

}
