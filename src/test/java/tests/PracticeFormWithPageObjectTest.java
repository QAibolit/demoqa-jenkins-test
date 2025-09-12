package tests;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.PracticeFormPage;

@Tag("demoqa-tests")
public class PracticeFormWithPageObjectTest extends BaseTest {

    String firstName = "Ivan",
            lastName = "Petrov",
            email = "test123@test.ru",
            gender = "Male",
            phone = "1234567891",
            fullName = "Ivan Petrov",
            subject = "English",
            hobby = "Reading",
            address = "Lenin street, 1",
            redColor = "rgb(220, 53, 69)";

    PracticeFormPage practiceFormPage = new PracticeFormPage();

    @Test
    @Feature("Сайт DemoQA")
    @Story("Страница 'Practice Form'")
    @DisplayName("Проверка полного заполнения формы регистрации")
    public void fillInAllFieldsOfPracticeFormTest() {
        practiceFormPage.openPage()
                .closeBanners()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setGender(gender)
                .setPhone(phone)
                .setBirthdayDate("3", "January", "2000")
                .setSubject(subject)
                .setHobbies(hobby)
                .uploadPicture("uploadFiles/hedgehog.jpg")
                .setCurrentAddress(address)
                .selectState("Uttar Pradesh")
                .selectCity("Merrut")
                .submit()
                .checkResultModalIsVisible()
                .checkNameInResultModal(fullName)
                .checkEmailInResultModal(email)
                .checkGenderInResultModal(gender)
                .checkPhoneInResultModal(phone)
                .checkBirthdayInResultModal("03 January,2000")
                .checkSubjectsInResultModal(subject)
                .checkHobbiesInResultModal(hobby)
                .checkPictureNameInResultModal("hedgehog.jpg")
                .checkAddressInResultModal(address)
                .checkStateAndCityInResultModal("Uttar Pradesh Merrut");
    }

    @Test
    @Feature("Сайт DemoQA")
    @Story("Страница 'Practice Form'")
    @DisplayName("Проверка заполнения обязательных полей формы регистрации")
    public void fillInRequiredFieldsOfPracticeFormTest() {
        practiceFormPage.openPage()
                .closeBanners()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setGender(gender)
                .setPhone(phone)
                .submit()
                .checkResultModalIsVisible()
                .checkNameInResultModal(fullName)
                .checkGenderInResultModal(gender)
                .checkPhoneInResultModal(phone);

    }

    @Test
    @Feature("Сайт DemoQA")
    @Story("Страница 'Practice Form'")
    @DisplayName("Проверка частичного заполнения обязательных полей формы регистрации")
    public void fillInRequiredFieldsOfPracticeFormPartiallyTest() {
        practiceFormPage.openPage()
                .closeBanners()
                .submit()
                .checkFirstNameFieldColor(redColor)
                .checkLastNameFieldColor(redColor)
                .checkPhoneFieldColor(redColor)
                .checkGenderRadiobuttonsColor(redColor);
    }
}
