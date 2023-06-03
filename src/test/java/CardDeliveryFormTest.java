import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryFormTest {

    private String generateDate(int daysToAdd, String pattern) {
        return LocalDate.now().plusDays(daysToAdd).format(DateTimeFormatter.ofPattern(pattern));
    }

    @Test
    public void shouldTestForm() {
        open("http://localhost:9999/");
        $("[data-test-id= 'city'] input").setValue("Владивосток");
        $("[data-test-id= 'date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id= 'date'] input").setValue(generateDate(3, "dd.MM.yyyy"));
        $("[data-test-id= 'name'] input").setValue("Игнатов Олег");
        $("[data-test-id= 'phone'] input").setValue("+78542360124");
        $("[data-test-id= 'agreement']").click();
        $$("[type= 'button']").filter(Condition.visible).last().click();
        $("[data-test-id= 'notification']").shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

}
