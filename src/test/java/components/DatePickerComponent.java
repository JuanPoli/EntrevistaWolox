package components;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import tests.BaseComponent;

import java.util.List;
import java.util.Optional;

public class DatePickerComponent extends BaseComponent {

    @FindBy(css = ".headerlabelbtn.monthlabel")
    private WebElement selectMonthButton;

    @FindBy(css = ".headerlabelbtn.yearlabel")
    private WebElement selectYearButton;

    @FindBy(className = "monthcell")
    private List<WebElement> monthCells;

    @FindBy(className = "yearcell")
    private List<WebElement> yearCells;

    @FindBy(className = "yearchangebtn")
    private WebElement yearChangeButton;

    @FindBy(css = ".daycell.currmonth")
    private List<WebElement> dayCells;

    public DatePickerComponent(WebElement container) {
        super(container);
    }

    public DatePickerComponent selectDayOfBirth(String year, String month, String day) {
        selectYearButton.click();
        finder = false;
        while (!finder) {
            if (!findYear(year)) {
                yearChangeButton.click();
                findYear(year);
            }
        }

        selectMonthButton.click();
        monthCells.stream().filter(cell -> cell.getText().equals(month)).findFirst().get().click();

        dayCells.stream().filter(dayCell -> dayCell.getText().equals(day)).findFirst().get().click();
        return this;
    }

    private boolean findYear(String year) {

        Optional<WebElement> element = yearCells.stream().filter(cell -> cell.getText().equals(year)).findFirst();
        WebElement optional = element.orElse(null);
        if (optional != null) {
            element.get().click();
            finder = true;
        }

        return finder;
    }

    private boolean finder;
}
