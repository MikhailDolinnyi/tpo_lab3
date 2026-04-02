package ru.traphouse.selenium;

import java.time.Duration;

import io.github.cdimascio.dotenv.Dotenv;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AuthUtils {

    public static void login(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(BaseTest.TIMEOUT));

        navigateTo(driver, BaseTest.BASE_URL + "/questions");
        OverlayUtils.handleOverlays(driver);

        if (isLoggedIn(driver)) {
            return;
        }

        loginWithForm(driver, wait);
    }

    /**
     * Публичный метод для тестов, проверяющих форму логина с произвольными кредами.
     * Сам выполняет навигацию и заполняет форму, НЕ ждёт успешного входа.
     */
    public static void loginWithCredentials(WebDriver driver, String email, String password) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(BaseTest.TIMEOUT));

        navigateTo(driver, BaseTest.BASE_URL + "/questions");
        OverlayUtils.handleOverlays(driver);

        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(//a[contains(text(),'Log in')])[2]"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='email']")));
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys(email);
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys(password);
        driver.findElement(By.xpath("//button[@id='submit-button']")).click();
    }

    private static void loginWithForm(WebDriver driver, WebDriverWait wait) {
        Dotenv dotenv = Dotenv.load();
        String email = dotenv.get("TEST_EMAIL");
        String password = dotenv.get("TEST_PASSWORD");
        assertNotNull(email, "TEST_EMAIL is not set in .env");
        assertNotNull(password, "TEST_PASSWORD is not set in .env");

        // уже на /questions после navigateTo в login(), повторный переход не нужен
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(//a[contains(text(),'Log in')])[2]"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='email']")));
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys(email);
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys(password);
        driver.findElement(By.xpath("//button[@id='submit-button']")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//a[@id='user-profile-button']")));
        OverlayUtils.handleOverlays(driver);
    }

    private static boolean isLoggedIn(WebDriver driver) {
        return !driver.findElements(By.xpath("//a[@id='user-profile-button']")).isEmpty();
    }

    private static void navigateTo(WebDriver driver, String url) {
        driver.get(url);
    }
}
