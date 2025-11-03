package utils;

import com.codeborne.selenide.junit5.ScreenShooterExtension;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.junit5.AllureJunit5;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;
import static com.codeborne.selenide.Configuration.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.addListener;
import static utils.PropertyReader.UiUrl;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
@ExtendWith(AllureJunit5.class)
public class UIBaseTest {

    @RegisterExtension
    static ScreenShooterExtension screenshotEmAll = new ScreenShooterExtension(true).
            to("resources/screenshots");

    @BeforeAll
    public static void setUp() {
        browser = PropertyReader.getInstance().getBrowser();
        browserSize = "1920x1080";
        headless = Boolean.parseBoolean(PropertyReader.getInstance().getHeadless());
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().
                screenshots(true).savePageSource(true));
        addListener(new WebEventListener());
    }

    @BeforeEach
    public void init() {
        open(UiUrl);
    }

    @AfterAll
    public static void closeDriver() {
        closeWebDriver();
    }
}
