package ru.netology.card;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryTest {

    String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @Test
    public void shouldFrd() {
        String planningDay = generateDate(5);
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Самара");
        $("[data-test-id='date'] input").doubleClick().sendKeys(planningDay);
        $("[data-test-id='name'] input").setValue("Семенов Сергей");
        $("[data-test-id='phone'] input").setValue("+78009994573");
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Забронировать")).click();
        $(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
        $("[class='notification__content']").shouldHave(Condition.text("Встреча успешно забронирована на " + planningDay), Duration.ofSeconds(15));
    }
}
