package pages.components;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.allOf;
import static com.codeborne.selenide.Condition.attributeMatching;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CalendarComponent {

    private final SelenideElement root = $("[class$=month-container]"),
            monthSelect = $("[class$=month-select]"),
            yearSelect = $("[class$=year-select]");

    private ElementsCollection days = $$(".react-datepicker__day");

    public void shouldBeVisible() {
        this.root.shouldBe(visible);
    }

    public void setDate(String day, String month, String year) {
        this.yearSelect.selectOption(year);
        this.monthSelect.selectOption(month);
        this.days.findBy(allOf(attributeMatching("aria-label", ".*%s.*".formatted(month)), exactText(day))).click();
    }
}
