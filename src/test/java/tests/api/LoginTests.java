package tests.api;

import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import static api.setup.RequestSpecs.login;
import static utils.PropertyReader.*;

@Epic("API Authentication")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("API:Login tests")
@Tag("api")
public class LoginTests {
    @AllureId("1")
    @Story("As a user, I want to login with valid credentials")
    @Severity(SeverityLevel.BLOCKER)
    @Feature("Authentication")
    @Test
    @Order(1)
    @DisplayName("Successful Login POST")
    @Description("Successful Login functionality POST")
    public void successfulLogin() {
        login(ApiEmail, ApiPassword, 200,"");
    }

    @Story("As a user, I should receive error messages for missing email or username")
    @Severity(SeverityLevel.MINOR)
    @Feature("Authentication")
    @Test
    @Order(2)
    @DisplayName("unSuccessfulLogin: Missing email POST")
    @Description("unSuccessful Login Missing email functionality POST")
    public void unSuccessfulLoginMissingEmail() {
        login("","", 400, "Missing email or username");
    }

    @Story("As a user, I should receive error messages for missing password")
    @Feature("Authentication")
    @Severity(SeverityLevel.MINOR)
    @Test
    @Order(3)
    @DisplayName("unSuccessfulLogin: Missing password POST")
    @Description("unSuccessful Login Missing password functionality POST")
    public void unSuccessfulLoginMissingPassword() {
        login(ApiEmail,"", 400, "Missing password");
    }

    @Story("As a user, I should receive error messages for wrong password")
    @Feature("Authentication")
    @Severity(SeverityLevel.MINOR)
    @Test
    @Order(4)
    @DisplayName("unSuccessfulLogin: Wrong password POST")
    @Description("unSuccessful Login Wrong password functionality POST")
    public void unSuccessfulLoginWrongPassword() {
        login(ApiEmail, ApiPassword + ApiPassword, 400, "Wrong password");
    }

    @Story("As a user, I should receive error messages for nonexists user")
    @Severity(SeverityLevel.MINOR)
    @Feature("Authentication")
    @Test
    @Order(5)
    @DisplayName("unSuccessfulLogin: User not found POST")
    @Description("unSuccessful Login User not found functionality POST")
    public void unSuccessfulLoginUserNotFound() {
        login("_"+ ApiEmail, ApiPassword, 400, "user not found");
    }
}
