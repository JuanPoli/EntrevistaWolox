package components.commons;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import web.BaseComponent;

import java.util.List;
import java.util.Optional;

import static components.commons.DatePickerComponent.Months.*;

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

    private boolean finder;
    private int selectedMonth;

    public enum Months {
        JANUARY, FEBRUARY, MARCH, APRIL, MAY, JUNE, JULY, AUGUST, SEPTEMBER, OCTOBER, NOVEMBER, DECEMBER

    }

    ;

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

    public int getMonthNumber(String month) {

        if (month.toLowerCase().equals("jan")) {
            return JANUARY.ordinal()+1;
        } else if (month.toLowerCase().equals("feb")) {
            return FEBRUARY.ordinal()+1;
        } else if (month.toLowerCase().equals("mar")) {
            return MARCH.ordinal()+1;
        } else if (month.toLowerCase().equals("apr")) {
            return APRIL.ordinal()+1;
        } else if (month.toLowerCase().equals("may")) {
            return MAY.ordinal()+1;
        } else if (month.toLowerCase().equals("jun")) {
            return JUNE.ordinal()+1;
        } else if (month.toLowerCase().equals("jul")) {
            return JULY.ordinal()+1;
        } else if (month.toLowerCase().equals("aug")) {
            return AUGUST.ordinal()+1;
        } else if (month.toLowerCase().equals("sep")) {
            return SEPTEMBER.ordinal()+1;
        } else if (month.toLowerCase().equals("oct")) {
            return OCTOBER.ordinal()+1;
        } else if (month.toLowerCase().equals("nov")) {
            return NOVEMBER.ordinal()+1;
        } else if (month.toLowerCase().equals("dec")) {
            return DECEMBER.ordinal()+1;
        }

        return 0;
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
}
