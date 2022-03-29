package ru.netology.card;

import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryTest {

    @Test
    public void shouldFrd() {
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Самара");
        $("[data-test-id='date'] input").doubleClick().sendKeys("09.05.2022");
        $("[data-test-id='name'] input").setValue("Семенов Сергей");
        $("[data-test-id='phone'] input").setValue("+78009994573");
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Забронировать")).click();
        $(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
    }
}
