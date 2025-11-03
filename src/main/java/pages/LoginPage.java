package pages;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import io.qameta.allure.Step;
import utils.PropertyReader;

public class LoginPage {

    //LOGIN PAGE
    public static final SelenideElement loginLogo = $(".login_logo").as("Landing logo");

    //LOGIN FORM FIELDS
    public static final SelenideElement usernameField = $("#user-name").as("Username Field");
    public static final SelenideElement passwordField = $("#password").as("Password Field");

    //BUTTON
    public static final SelenideElement loginButton = $("#login-button").as("Login Button");

    //DATA
    public static final String UIUsername = (PropertyReader.getInstance().getUIUsername());
    public static final String UIPassword = (PropertyReader.getInstance().getUIPassword());

    //MESSAGE
    public static SelenideElement errorLoginMessage = $(".error-message-container h3").as("Error login message");

    @Step("Open application")
    public static void openApplication() {
        //open(PropertyReader.getInstance().getUiUrl());
        isCurrentPage();
    }

    @Step("Verify that current page is first page")
    public static void isCurrentPage(){
        loginLogo.shouldBe(visible);
        usernameField.shouldBe(visible);
        passwordField.shouldBe(visible);
        loginButton.shouldBe(visible);
    }

    @Step("Login with username")
    public static void login(String username, String password) {
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        loginButton.click();
    }

    public static void loginAs(String username, String wrongPassword) {
        login(username, wrongPassword);
        ProductsPage.isCurrentPage();
    }

    public static void loginWithWrongPassword(String wrongPassword) {
        login(UIUsername, wrongPassword);
    }

    public static void loginWithValidCredentials() {
        login(UIUsername, UIPassword);
    }
    @Step("Verify error message is displayed")
    public static void verifyErrorMessage() {
        errorLoginMessage.shouldBe(visible);
    }
}

