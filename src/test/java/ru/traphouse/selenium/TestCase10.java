package ru.traphouse.selenium;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class TestCase10 extends BaseTest {

    // TS-10: открываем форму регистрации и проверяем наличие способов создания аккаунта
    @Test
    public void testCase10() {
        navigateTo(BASE_URL + "/questions");

        // берём вторую ссылку Sign up
        driver.findElement(By.xpath("(//a[contains(text(),'Sign up')])[2]")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//button[@data-provider='Google']")));

        // OAuth-кнопки
        List<WebElement> googleBtn = driver.findElements(By.xpath("//button[@data-provider='Google']"));
        assertFalse(googleBtn.isEmpty());

        List<WebElement> githubBtn = driver.findElements(By.xpath("//button[@data-provider='GitHub']"));
        assertFalse(githubBtn.isEmpty());

        // поля для регистрации через email
        List<WebElement> emailField = driver.findElements(By.xpath("//input[@id='signup-modal-email']"));
        assertFalse(emailField.isEmpty());

        List<WebElement> passwordField = driver.findElements(By.xpath("//input[@id='signup-modal-password']"));
        assertFalse(passwordField.isEmpty());
    }
}
