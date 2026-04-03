package ru.traphouse.selenium;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class TestCase17 extends BaseTest {

    // TS-17: голосуем за комментарий и проверяем что кнопка меняет состояние
    @Test
    public void testCase17() {
        AuthUtils.login(driver);

        // используем конкретный вопрос — там гарантированно есть комментарий
        driver.get(BASE_URL + "/staging-ground/79919244");

        // кнопка апвоута у комментария может быть вне viewport — прокручиваем
        WebElement upvoteBtn = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//button[@data-threadable-comment-target='upvoteArrow']")));
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", upvoteBtn);
        js.executeScript("arguments[0].click();", upvoteBtn);

        // после клика aria-label кнопки должен смениться на "You've voted..."
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//button[@aria-label=\"You've voted for this as a useful comment\"]")));

        assertFalse(driver.findElements(
                By.xpath("//button[@aria-label=\"You've voted for this as a useful comment\"]")).isEmpty());
    }
}
