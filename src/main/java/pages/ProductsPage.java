package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$x;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ProductsPage {

    //Title
    public static final SelenideElement pageTitleElement = $(".header_secondary_container .title").as("Product Title");
    public static final String pageTitle = "Products";

    //Buttons
    public static SelenideElement addToCartBackpack = $("#add-to-cart-sauce-labs-backpack").as("Add To Cart Backpack");
    public static SelenideElement removeFromCartBackpack = $("#remove-sauce-labs-backpack").as("Remove From Cart Backpack");
    public static ElementsCollection addToCartButtons = $$x("//button[contains(@data-test, 'add-to-cart-')]");

    @Step("Verify user is on Products page")
    public static void isCurrentPage() {
        pageTitleElement.shouldBe(visible).shouldHave(text(pageTitle));
    }

    @Step("Add 'Sauce Labs Backpack' to shopping cart")
    public static void addToCartBackpack() {
        if(removeFromCartBackpack.exists()) { removeFromCartBackpack.click(); }
        addToCartBackpack.click();
    }

    @Step("Verify 'Sauce Labs Backpack' was added to cart successfully")
    public static void verifyAddToCart() {
        removeFromCartBackpack.shouldBe(visible);
        CartPage.cartBadge.shouldBe(visible).shouldHave(text("1"));
    }

    @Step("Remove 'Sauce Labs Backpack' from cart on Products page")
    public static void removeFromCartBackpack() {
        removeFromCartBackpack.click();
    }

    @Step("Verify cart count decreased")
    public static void verifyRemovingFromCartBackpack(int oldValueInCart) {
        removeFromCartBackpack.shouldBe(hidden);
        assertThat(oldValueInCart-1, equalTo(CartPage.getCountInCart()));
    }

    @Step("Add all available products to shopping cart")
    public static void addingAllItemsToCart() {
        addToCartButtons.asFixedIterable().forEach(SelenideElement::click);
    }
}
