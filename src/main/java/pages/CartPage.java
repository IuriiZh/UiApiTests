package pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.*;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$x;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CartPage {

    //Title
    public static final SelenideElement pageTitleElement = $(".header_secondary_container .title").as("Cart Title");
    public static final String pageTitle = "Your Cart";

    @Step("Verify user is on Cart page")
    public static void isCurrentPage() {
        pageTitleElement.shouldBe(visible).shouldHave(text(pageTitle));
    }

    //BUTTONS
    public static SelenideElement removeFromCartBackpack = $("#remove-sauce-labs-backpack").as("Remove From Cart Backpack");
    private static final SelenideElement checkoutButton = $("#checkout").as("Checkout button");
    public static final SelenideElement cartButton = $(".shopping_cart_link").as("Shopping cart button");

    //Badge
    public static final SelenideElement cartBadge = $(".shopping_cart_badge").as("Shopping cart badge");

    @Step("Get current cart item count")
    public static int getCountInCart() {
        return cartBadge.is(visible) ? Integer.parseInt(cartBadge.getText()) : 0;
    }

    @Step("Navigate to shopping cart")
    public static void clickCartButton() {
        cartButton.click();
        CartPage.isCurrentPage();
    }

    @Step("Proceed to checkout")
    public static void clickToCheckoutButton() {
        checkoutButton.click();
        CheckoutInformationPage.isCurrentPage();
    }

    @Step("Remove 'Sauce Labs Backpack' from cart")
    public static void removeFromCartBackpack() {
        removeFromCartBackpack.click();
    }

    @Step("Verify cart count decreased")
    public static void verifyRemovingFromCartBackpack(int oldValueInCart) {
        removeFromCartBackpack.shouldBe(hidden);
        assertThat(oldValueInCart-1, equalTo(CartPage.getCountInCart()));
    }

    @Step("Remove all items from cart")
    public static void removeAllFromCart() {
        $$x("//button[contains(@data-test, 'remove')]").asDynamicIterable().forEach(SelenideElement::click);
    }

    @Step("Get count of products in cart")
    public static void verifyItemsInToCart(int amountItems) {
        assertThat(amountItems, equalTo(CartPage.getCountInCart()));
    }

}

