package ru.netology.web;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardOrder {
    @Test
    void shouldTest() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Василий");
        $("[data-test-id=phone] input").setValue("+79270000000");
        $("[data-test-id=agreement]").click();
        $(By.className("button")).click();
        $(By.className("paragraph")).shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldInvalidNameTest() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Vasilia");
        $("[data-test-id=phone] input").setValue("+79270000000");
        $("[data-test-id=agreement]").click();
        $(By.className("button")).click();
        $(".input_type_text .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldInvalidPhoneTest() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Вася-Вася Вася");
        $("[data-test-id=phone] input").setValue("789787878677677776776");
        $("[data-test-id=agreement]").click();
        $(By.className("button")).click();
        $(".input_type_tel .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldInvalidChetboxNotPressedTest() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Вася-Вася Вася");
        $("[data-test-id=phone] input").setValue("+79270000000");
        $(By.className("button")).click();
        $("[data-test-id=agreement]").click();
        $(".checkbox .checkbox__text").shouldHave(exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй"));
    }

    @Test
    void shouldNoNameTest() {
        open("http://localhost:9999");
        $("[data-test-id=phone] input").setValue("+79270000000");
        $(By.className("button")).click();
        $(".input_type_text .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldNoPhoneTest() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Вася-Вася Вася");
        $(By.className("button")).click();
        $(".input_type_tel .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldEnterNumbersInAfterNameTest() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("123");
        $("[data-test-id=phone] input").setValue("+79270000000");
        $(By.className("button")).click();
        $(".input_type_text .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldEnteringLettersInTheTelephoneTest() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Олег");
        $("[data-test-id=phone] input").setValue("+abc");
        $(By.className("button")).click();
        $(".input_type_tel .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

}
