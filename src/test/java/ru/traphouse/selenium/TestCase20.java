package ru.traphouse.selenium;

import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class TestCase20 extends BaseTest {

    @Test
    public void testCase20_wrongEmail() {
        AuthUtils.loginWithCredentials(driver, "nonexistent@example.com", "somepassword123");

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//p[contains(.,'No user found with matching email')]")));

        assertFalse(driver.findElements(
                By.xpath("//p[contains(.,'No user found with matching email')]")).isEmpty());
    }

    @Test
    public void testCase20_wrongPassword() {
        String email = Dotenv.load().get("TEST_EMAIL");

        AuthUtils.loginWithCredentials(driver, email, "wrongpassword123");

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//p[contains(.,'The email or password is incorrect.')]")));

        assertFalse(driver.findElements(
                By.xpath("//p[contains(.,'The email or password is incorrect.')]")).isEmpty());
    }
}
