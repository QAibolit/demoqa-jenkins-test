package pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.components.CalendarComponent;
import pages.components.ResultModalComponent;

import static com.codeborne.selenide.Condition.cssValue;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.open;

public class PracticeFormPage {

    private final SelenideElement header = $(".text-center"),
            firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            emailInput = $("#userEmail"),
            phoneInput = $("#userNumber"),
            dateOfBirthInput = $("#dateOfBirthInput"),
            subjectsInput = $("#subjectsInput"),
            uploadPictureInput = $("#uploadPicture"),
            addressInput = $("#currentAddress"),
            stateSelect = $("#state"),
            citySelect = $("#city"),
            submitButton = $("#submit");


    private final ElementsCollection genderRadioButtons = $$(".custom-radio .custom-control-label"),
            hobbiesCheckboxes = $$(".custom-checkbox"),
            stateOptions = $$("[id*= select-3-option]"),
            cityOptions = $$("[id*= select-4-option]");

    private final CalendarComponent calendar = new CalendarComponent();

    private final ResultModalComponent resultModalWindow = new ResultModalComponent();

    @Step("Открыть страницу 'Practice Form'")
    public PracticeFormPage openPage() {
        open("/automation-practice-form");
        this.header.shouldHave(text("Practice Form"));
        return this;
    }

    @Step("Закрыть рекламные баннеры")
    public PracticeFormPage closeBanners() {
        executeJavaScript("$('footer').remove();");
        executeJavaScript("$('#fixedban').remove();");
        return this;
    }

    @Step("Ввести имя")
    public PracticeFormPage setFirstName(String firstName) {
        this.firstNameInput.setValue(firstName);
        return this;
    }

    @Step("Ввести фамилию")
    public PracticeFormPage setLastName(String lastName) {
        this.lastNameInput.setValue(lastName);
        return this;
    }

    @Step("Ввести email")
    public PracticeFormPage setEmail(String email) {
        emailInput.setValue(email);
        return this;
    }

    @Step("Выбрать пол")
    public PracticeFormPage setGender(String gender) {
        genderRadioButtons.findBy(text(gender)).parent().click();
        return this;
    }

    @Step("Ввести номер телефона")
    public PracticeFormPage setPhone(String phone) {
        phoneInput.setValue(phone);
        return this;
    }

    @Step("Ввести дату рождения")
    public PracticeFormPage setBirthdayDate(String day, String month, String year) {
        dateOfBirthInput.click();
        calendar.shouldBeVisible();
        calendar.setDate(day, month, year);
        return this;
    }

    @Step("Ввести изучаемый предмет")
    public PracticeFormPage setSubject(String subject) {
        subjectsInput.setValue(subject).pressEnter();
        return this;
    }

    @Step("Выбрать хобби")
    public PracticeFormPage setHobbies(String... hobbies) {
        for (String hobby : hobbies) {
            hobbiesCheckboxes.findBy(text(hobby)).click();
        }
        return this;
    }

    @Step("Загрузить изображение")
    public PracticeFormPage uploadPicture(String pathToPicture) {
        uploadPictureInput.uploadFromClasspath(pathToPicture);
        return this;
    }

    @Step("Ввести текущий адрес проживания")
    public PracticeFormPage setCurrentAddress(String address) {
        addressInput.setValue(address);
        return this;
    }

    @Step("Выбрать штат проживания")
    public PracticeFormPage selectState(String state) {
        stateSelect.scrollTo().click();
        stateOptions.findBy(text(state)).click();
        return this;
    }

    @Step("Выбрать город проживания")
    public PracticeFormPage selectCity(String city) {
        citySelect.click();
        cityOptions.findBy(text(city)).click();
        return this;
    }

    @Step("Нажать на кнопку 'Submit'")
    public PracticeFormPage submit() {
        submitButton.scrollTo().click();
        return this;
    }

    @Step("Проверить, что цвет поля для ввода имени равен '{expectedColor}'")
    public PracticeFormPage checkFirstNameFieldColor(String expectedColor) {
        this.firstNameInput.shouldHave(cssValue("border-color", expectedColor));
        return this;
    }

    @Step("Проверить, что цвет поля для ввода фамилии равен '{expectedColor}'")
    public PracticeFormPage checkLastNameFieldColor(String expectedColor) {
        this.lastNameInput.shouldHave(cssValue("border-color", expectedColor));
        return this;
    }

    @Step("Проверить, что цвет поля для ввода номера телефона равен '{expectedColor}'")
    public PracticeFormPage checkPhoneFieldColor(String expectedColor) {
        this.phoneInput.shouldHave(cssValue("border-color", expectedColor));
        return this;
    }

    @Step("Проверить, что цвет радио-кнопок для выбора пола равен '{expectedColor}'")
    public PracticeFormPage checkGenderRadiobuttonsColor(String expectedColor) {
        this.genderRadioButtons.shouldHave(CollectionCondition.allMatch(
                "Все элементы коллекции должны иметь атрибут 'border-color' со значением " + expectedColor,
                element -> element.getCssValue("border-color").equals(expectedColor)));
        return this;
    }

    @Step("Проверить, что отобразилось модальное окно с введенными данными")
    public PracticeFormPage checkResultModalIsVisible() {
        resultModalWindow.shouldBeVisible();
        return this;
    }

    @Step("Проверить, что имя и фамилия в модальном окне равны '{name}'")
    public PracticeFormPage checkNameInResultModal(String name) {
        resultModalWindow.shouldHaveName(name);
        return this;
    }

    @Step("Проверить, что email в модальном окне равен '{email}'")
    public PracticeFormPage checkEmailInResultModal(String email) {
        resultModalWindow.shouldHaveEmail(email);
        return this;
    }

    @Step("Проверить, что пол в модальном окне равен '{gender}'")
    public PracticeFormPage checkGenderInResultModal(String gender) {
        resultModalWindow.shouldHaveGender(gender);
        return this;
    }

    @Step("Проверить, что номер телефона в модальном окне равен '{phone}'")
    public PracticeFormPage checkPhoneInResultModal(String phone) {
        resultModalWindow.shouldHavePhone(phone);
        return this;
    }

    @Step("Проверить, что дата рождения в модальном окне равна '{birthday}'")
    public PracticeFormPage checkBirthdayInResultModal(String birthday) {
        resultModalWindow.shouldHaveBirthday(birthday);
        return this;
    }

    @Step("Проверить, что дата рождения в модальном окне равна '{birthday}'")
    public PracticeFormPage checkSubjectsInResultModal(String subjects) {
        resultModalWindow.shouldHaveSubjects(subjects);
        return this;
    }

    @Step("Проверить, что хобби в модальном окне равно '{hobbies}'")
    public PracticeFormPage checkHobbiesInResultModal(String hobbies) {
        resultModalWindow.shouldHaveHobbies(hobbies);
        return this;
    }

    @Step("Проверить, что название загруженного изображения в модальном окне равно '{pictureName}'")
    public PracticeFormPage checkPictureNameInResultModal(String pictureName) {
        resultModalWindow.shouldHavePictureName(pictureName);
        return this;
    }

    @Step("Проверить, что текущий адрес в модальном окне равен '{address}'")
    public PracticeFormPage checkAddressInResultModal(String address) {
        resultModalWindow.shouldHaveAddress(address);
        return this;
    }

    @Step("Проверить, что штат и город проживания в модальном окне равны '{stateAndCity}'")
    public PracticeFormPage checkStateAndCityInResultModal(String stateAndCity) {
        resultModalWindow.shouldHaveStateAndCity(stateAndCity);
        return this;
    }
}
