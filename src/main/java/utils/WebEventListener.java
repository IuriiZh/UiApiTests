package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class WebEventListener implements WebDriverListener {

    private static final Logger logger = LogManager.getLogger(WebEventListener.class);

    @Override
    public void beforeGet(WebDriver driver, String url) {
        logger.info("Navigating to: '" + url + "'");
    }

    @Override
    public void afterGet(WebDriver driver, String url) {
        logger.info("Navigated to:'" + url + "'");
    }

    @Override
    public void beforeClick(WebElement element) {
        logger.info("Clicking on button: " + element.toString());
    }

    @Override
    public void afterClick(WebElement element) {
        logger.info("Clicked on: " + element.toString());
    }

    @Override
    public void beforeSendKeys(WebElement element, CharSequence... keysToSend) {
        logger.info("Entering value in the field input: " + element.toString());
    }

    @Override
    public void afterSendKeys(WebElement element, CharSequence... keysToSend) {
        logger.info("Element value changed to: " + element.toString());
    }

    @Override
    public void beforeBack(WebDriver.Navigation navigation) {
        System.out.println("Navigating back to previous page");
    }

    @Override
    public void afterBack(WebDriver.Navigation navigation) {
        System.out.println("Navigated back to previous page");
    }

    @Override
    public void beforeForward(WebDriver.Navigation navigation) {
        System.out.println("Navigating forward to next page");
    }

    @Override
    public void afterForward(WebDriver.Navigation navigation) {
        System.out.println("Navigated forward to next page");
    }

    @Override
    public void beforeFindElement(WebDriver driver, By locator) {
        logger.info("Trying to find Element By : " + locator.toString());
    }

    @Override
    public void afterFindElement(WebDriver driver, By locator, WebElement result) {
        logger.info("Found Element By : " + locator.toString());
    }

    @Override
    public void beforeFindElement(WebElement element, By locator) {
        logger.info("Trying to find Element By : " + locator.toString());
    }

    @Override
    public void afterFindElement(WebElement element, By locator, WebElement result) {
        logger.info("Found Element By : " + locator.toString());
    }
}