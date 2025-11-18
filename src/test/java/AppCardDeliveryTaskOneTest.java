import org.openqa.selenium.Keys;
import org.junit.jupiter.api.BeforeEach;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;


import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


import static com.codeborne.selenide.Selenide.*;


public class AppCardDeliveryTaskOneTest {

    @BeforeEach
    void setup() {
        Selenide.open("http://localhost:9999");
    }

    private String generateDate(long addDays, String pattern) {
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));

    }

    @Test
    public void shouldBeSuccessfullyCompleted() {
        $("[data-test-id= 'city'] input").setValue("Рязань");
        String planningDate = generateDate(4, "dd.MM.yyyy");
        $("[data-test-id='date'] input").press(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE)
                .setValue(planningDate);
        $("[data-test-id='name'] input").setValue("Макаров Сергей");
        $("[data-test-id='phone'] input").setValue("+79051234545");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $("[data-test-id='notification']")
                .should(Condition.visible, Duration.ofSeconds(15))
                .should(Condition.text("Встреча успешно забронирована на " + planningDate));
    }

}





