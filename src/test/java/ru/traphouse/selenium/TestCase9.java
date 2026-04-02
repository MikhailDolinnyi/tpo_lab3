package ru.traphouse.selenium;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class TestCase9 extends BaseTest {

    @Test
    public void testCase9() {
        navigateTo(BASE_URL + "/");

        driver.findElement(By.xpath("(//a[contains(text(),'Log in')])[2]")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='email']")));

        List<WebElement> emailField = driver.findElements(By.xpath("//input[@id='email']"));
        assertFalse(emailField.isEmpty());

        List<WebElement> passwordField = driver.findElements(By.xpath("//input[@id='password']"));
        assertFalse(passwordField.isEmpty());

        List<WebElement> oauthButtons = driver.findElements(By.xpath("//div[@id='openid-buttons']"));
        assertFalse(oauthButtons.isEmpty());

        List<WebElement> googleBtn = driver.findElements(
                By.xpath("//div[@id='openid-buttons']//button[@data-provider='google']"));
        assertFalse(googleBtn.isEmpty());

        List<WebElement> githubBtn = driver.findElements(
                By.xpath("//div[@id='openid-buttons']//button[@data-provider='github']"));
        assertFalse(githubBtn.isEmpty());

        List<WebElement> facebookBtn = driver.findElements(
                By.xpath("//div[@id='openid-buttons']//button[@data-provider='facebook']"));
        assertFalse(facebookBtn.isEmpty());
    }
}
