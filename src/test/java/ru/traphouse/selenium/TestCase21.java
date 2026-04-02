package ru.traphouse.selenium;

import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestCase21 extends BaseTest {

    @Test
    public void testCase21() {
        Dotenv dotenv = Dotenv.load();
        String email = dotenv.get("TEST_REGISTRATION_EMAIL");
        String password = dotenv.get("TEST_PASSWORD");
        assertNotNull(email, "TEST_REGISTRATION_EMAIL is not set in .env");
        assertNotNull(password, "TEST_PASSWORD is not set in .env");

        navigateTo(BASE_URL + "/questions");
        OverlayUtils.handleOverlays(driver);

        driver.findElement(By.xpath("(//a[contains(text(),'Sign up')])[2]")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@id='signup-modal-email']")));
        driver.findElement(By.xpath("//input[@id='signup-modal-email']")).sendKeys(email);
        driver.findElement(By.xpath("//input[@id='signup-modal-password']")).sendKeys(password);
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//form[@id='signup-modal-signup-form']/div[3]/button"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h2[contains(.,'Check your email')]")));

        assertFalse(driver.findElements(
                By.xpath("//h2[contains(.,'Check your email')]")).isEmpty());
    }
}
