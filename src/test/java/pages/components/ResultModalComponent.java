package pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class ResultModalComponent {

    private final SelenideElement root = $(".modal-content"),
            title = $(".modal-title"),
            studentName = $(byText("Student Name")).sibling(0),
            studentEmail = $(byText("Student Email")).sibling(0),
            gender = $("table").$(byText("Gender")).sibling(0),
            phoneNumber = $("table").$(byText("Mobile")).sibling(0),
            birthdayDate = $("table").$(byText("Date of Birth")).sibling(0),
            subjects = $("table").$(byText("Subjects")).sibling(0),
            hobbies = $("table").$(byText("Hobbies")).sibling(0),
            pictureName = $("table").$(byText("Picture")).sibling(0),
            address = $(byText("Address")).sibling(0),
            stateAndCity = $("table").$(byText("State and City")).sibling(0);

    public void shouldBeVisible() {
        this.root.shouldBe(visible);
    }

    public void shouldHaveName(String name) {
        this.studentName.shouldHave(text(name));
    }

    public void shouldHaveEmail(String email) {
        this.studentEmail.shouldHave(text(email));
    }

    public void shouldHaveGender(String gender) {
        this.gender.shouldHave(text(gender));
    }

    public void shouldHavePhone(String phone) {
        this.phoneNumber.shouldHave(text(phone));
    }

    public void shouldHaveSubjects(String subjects) {
        this.subjects.shouldHave(text(subjects));
    }

    public void shouldHaveBirthday(String birthday) {
        this.birthdayDate.shouldHave(text(birthday));
    }

    public void shouldHaveHobbies(String hobbies) {
        this.hobbies.shouldHave(text(hobbies));
    }

    public void shouldHavePictureName(String pictureName) {
        this.pictureName.shouldHave(text(pictureName));
    }

    public void shouldHaveAddress(String address) {
        this.address.shouldHave(text(address));
    }

    public void shouldHaveStateAndCity(String stateAndCity) {
        this.stateAndCity.shouldHave(text(stateAndCity));
    }
}
