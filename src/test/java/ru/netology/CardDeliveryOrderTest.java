package ru.netology;

import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import javax.management.Notification;
import java.beans.PropertyEditor;
import java.lang.module.Configuration;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static java.time.Duration.ofSeconds;

public class CardDeliveryOrderTest {


    @Test
    void shouldSubmitRequest() {
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Казань");

        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        LocalDate dateofDelivery = LocalDate.now().plusDays(3);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String date = formatter.format(dateofDelivery);
        $("[data-test-id='date'] input").setValue(date);

        $("[data-test-id='name'] input").setValue("Иван Петров");
        $("[data-test-id='phone'] input").setValue("+79000000000");
        $("[data-test-id='agreement']").click();
        $$("button").find((exactText("Забронировать"))).click();
        $(byText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
        $("[data-test-id='notification'] .notification__content").shouldHave(text("Встреча успешно забронирована на " + date));
    }
}
