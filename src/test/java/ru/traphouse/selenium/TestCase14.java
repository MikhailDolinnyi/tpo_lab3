package ru.traphouse.selenium;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestCase14 extends BaseTest {

    // TS-14: проверяем голосование — апвоут, даунвоут и отмена голоса
    @Test
    public void testCase14() {
        AuthUtils.login(driver);

        // открываем первый вопрос из списка
        navigateTo(BASE_URL + "/questions/");
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("(//div[@id='questions']//div[contains(@class,'s-post-summary')]//h3//a)[1]")));
        driver.findElement(By.xpath(
                "(//div[@id='questions']//div[contains(@class,'s-post-summary')]//h3//a)[1]")).click();

        // кнопка апвоута изначально не должна быть нажата
        List<WebElement> upvoteBtn = driver.findElements(
                By.xpath("//button[@aria-label='Up vote' and @aria-pressed='false']"));
        assertTrue(upvoteBtn.isEmpty());

        // жмём апвоут и проверяем что состояние изменилось
        driver.findElement(By.xpath("//button[@aria-label='Up vote']")).click();
        List<WebElement> upvoteActive = driver.findElements(
                By.xpath("//button[@aria-label='Up vote' and @aria-pressed='true']"));
        assertTrue(upvoteActive.isEmpty());

        // даунвоут доступен
        List<WebElement> downvoteBtn = driver.findElements(
                By.xpath("//button[@aria-label='Down vote' and @aria-pressed='false']"));
        assertFalse(downvoteBtn.isEmpty());

        // жмём даунвоут
        driver.findElement(By.xpath("//button[@aria-label='Down vote']")).click();
        List<WebElement> downvoteActive = driver.findElements(
                By.xpath("//button[@aria-label='Down vote' and @aria-pressed='true']"));
        assertTrue(downvoteActive.isEmpty());

        // повторный клик — отмена голоса
        driver.findElement(By.xpath("//button[@aria-label='Down vote']")).click();
        List<WebElement> downvoteReset = driver.findElements(
                By.xpath("//button[@aria-label='Down vote' and @aria-pressed='false']"));
        assertFalse(downvoteReset.isEmpty());
    }
}
