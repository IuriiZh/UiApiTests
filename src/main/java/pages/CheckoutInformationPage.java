package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class CheckoutInformationPage {

    public static final SelenideElement pageTitleElement = $(".header_secondary_container .title").as("Checkout: Your Information Title");
    public static final String pageTitle = "Checkout: Your Information";

    @Step("Verify user is on Checkout Information page")
    public static void isCurrentPage() {
        pageTitleElement.shouldBe(visible).shouldHave(text(pageTitle));
    }

    //CHECKOUT FORM FIELDS
    private static final SelenideElement firstNameField = $("#first-name").as("First Name Field");
    private static final SelenideElement lastNameField = $("#last-name").as("Last Name Field");
    private static final SelenideElement zipCodeField = $("#postal-code").as("Zip Postal Code Field");

    //BUTTON
    private static final SelenideElement continueButton = $("#continue").as("Continue Button");

    //DATA
    private static final CharSequence firstName = ("Iurii");
    private static final CharSequence lastName = ("No given name");
    private static final CharSequence zipCode = ("90210");

    @Step("Fill input fields")
    public static void fillFields() {
        firstNameField.sendKeys(firstName);
        lastNameField.sendKeys(lastName);
        zipCodeField.sendKeys(zipCode);
    }

    @Step("Click continue button")
    public static void clickContinueButton() {
        continueButton.click();
        CheckoutOverviewPage.isCurrentPage();
    }
}