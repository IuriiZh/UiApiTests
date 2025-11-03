package tests.ui;

import org.junit.jupiter.api.*;
import io.qameta.allure.*;
import pages.*;
import utils.UIBaseTest;
import java.io.IOException;

@Epic("UI Landing Page")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("UI: Landing")
@Tag("ui")
public class UiLanding extends UIBaseTest {

    @Feature("Application Access")
    @Story("As a user, I want to access the landing page")
    @Severity(SeverityLevel.NORMAL)
    @Link("https://www.saucedemo.com/")
    @Test
    @Order(1)
    @DisplayName("Landing")
    @Description("Test Landing page functionality")
    public void testLanding() throws IOException {
        LandingPage.accessLanding();
        LandingPage.verifyTitle();
        LoginPage.isCurrentPage();
    }
}
