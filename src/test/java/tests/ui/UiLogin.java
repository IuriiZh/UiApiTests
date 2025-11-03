package tests.ui;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import io.qameta.allure.*;
import pages.*;
import utils.UIBaseTest;

@Epic("UI Authentication")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("UI: Login")
@Tag("ui")
public class UiLogin extends UIBaseTest {

    @Feature("User Authentication")
    @Story("As a user, I want to login to access the application")
    @Severity(SeverityLevel.CRITICAL)
    @Link("https://www.saucedemo.com/")
    @Test
    @Order(2)
    @DisplayName("Unsuccessful login")
    @Description("Test Unsuccessful Login functionality")
    public void testUnsuccessfulLogin() {
        LoginPage.openApplication();
        LoginPage.loginWithWrongPassword("Wrong password here");
        LoginPage.verifyErrorMessage();
    }

    @Feature("User Authentication")
    @Story("As a user, I should see error messages for invalid credentials")
    @Severity(SeverityLevel.NORMAL)
    @Link("https://www.saucedemo.com/")
    @ParameterizedTest(name="with user: {0}")
    @CsvFileSource(resources = "/login-data.csv", numLinesToSkip = 1)
    @Order(3)
    @DisplayName("Successfully Login")
    @Description("Test successfully login functionality")
    public void testLogin(String username, String password) {
        LoginPage.openApplication();
        LoginPage.loginAs(username, password);
    }
}
