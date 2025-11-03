package tests.ui;

import org.junit.jupiter.api.*;
import static com.codeborne.selenide.Selenide.$$x;
import io.qameta.allure.*;
import pages.*;
import utils.UIBaseTest;

@Epic("UI Shopping Cart")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("UI: Cart")
@Tag("ui")
public class UiCart extends UIBaseTest {

    @Story("As a user, I want to add products to my cart")
    @Severity(SeverityLevel.NORMAL)
    @Feature("Shopping Cart Management")
    @Link("https://www.saucedemo.com/inventory.html")
    @Test
    @Order(4)
    @DisplayName("Add to cart")
    @Description("Test add to cart functionality")
    public void addToCart() {
        LoginPage.openApplication();
        LoginPage.loginWithValidCredentials();
        ProductsPage.addToCartBackpack();
        ProductsPage.verifyAddToCart();
    }

    @Story("As a user, I want to cancel my purchase")
    @Severity(SeverityLevel.NORMAL)
    @Feature("Shopping Cart Management")
    @Link("https://www.saucedemo.com/inventory.html")
    @Test
    @Order(5)
    @DisplayName("Cancellation of purchase")
    @Description("Test of Cancellation of purchase functionality")
    public void testCancellationOfPurchase() {
        LoginPage.openApplication();
        LoginPage.loginWithValidCredentials();
        ProductsPage.addToCartBackpack();
        CartPage.clickCartButton();
        CartPage.clickToCheckoutButton();
        CheckoutInformationPage.fillFields();
        CheckoutInformationPage.clickContinueButton();
        CheckoutOverviewPage.clickToCancelButton();
    }

    @Story("As a user, I want to remove products from my cart from Products page")
    @Severity(SeverityLevel.NORMAL)
    @Feature("Shopping Cart Management")
    @Link("https://www.saucedemo.com/inventory.html")
    @Test
    @Order(6)
    @DisplayName("Remove from cart from page")
    @Description("Test Remove from cart from page functionality")
    public void removeFromCartFromPage() {
        LoginPage.openApplication();
        LoginPage.loginWithValidCredentials();
        ProductsPage.addToCartBackpack();
        int oldValueInCart = CartPage.getCountInCart();
        ProductsPage.removeFromCartBackpack();
        ProductsPage.verifyRemovingFromCartBackpack(oldValueInCart);
    }

    @Story("As a user, I want to remove products from my cart")
    @Severity(SeverityLevel.NORMAL)
    @Feature("Shopping Cart Management")
    @Link("https://www.saucedemo.com/cart.html")
    @Test
    @Order(7)
    @DisplayName("Remove from cart")
    @Description("Test Remove from cart functionality")
    public void removeFromCart() {
        LoginPage.openApplication();
        LoginPage.loginWithValidCredentials();
        ProductsPage.addToCartBackpack();
        int oldValueInCart = CartPage.getCountInCart();
        CartPage.removeFromCartBackpack();
        CartPage.verifyRemovingFromCartBackpack(oldValueInCart);
    }

    @Story("Verifying counting of cart")
    @Severity(SeverityLevel.NORMAL)
    @Feature("Shopping Cart Management")
    @Link("https://www.saucedemo.com/checkout-step-two.html")
    @Test
    @Order(9)
    @DisplayName("Calculation of items")
    @Description("Test of Calculation of the items functionality")
    public void testCalculationOfTheTotalAmountOfItems(){
        LoginPage.openApplication();
        LoginPage.loginWithValidCredentials();
        CartPage.removeAllFromCart();
        int amountItems = $$x("//button[contains(@data-test, 'add-to-cart-')]").size();
        ProductsPage.addingAllItemsToCart();
        CartPage.verifyItemsInToCart(amountItems);
    }
}
