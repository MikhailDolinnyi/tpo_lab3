package ru.traphouse.selenium;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class TestCase14 extends BaseTest {

    @Test
    public void testCase14() {
        AuthUtils.login(driver);

        navigateTo(BASE_URL + "/questions/");
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("(//div[@id='questions']//div[contains(@class,'s-post-summary')]//h3//a)[1]")));
        driver.findElement(By.xpath(
                "(//div[@id='questions']//div[contains(@class,'s-post-summary')]//h3//a)[1]")).click();

        List<WebElement> upvoteBtn = driver.findElements(
                By.xpath("//button[@aria-label='Up vote' and @aria-pressed='false']"));
        assertFalse(upvoteBtn.isEmpty());

        driver.findElement(By.xpath("//button[@aria-label='Up vote']")).click();
        List<WebElement> upvoteActive = driver.findElements(
                By.xpath("//button[@aria-label='Up vote' and @aria-pressed='true']"));
        assertFalse(upvoteActive.isEmpty());

        List<WebElement> downvoteBtn = driver.findElements(
                By.xpath("//button[@aria-label='Down vote' and @aria-pressed='false']"));
        assertFalse(downvoteBtn.isEmpty());

        driver.findElement(By.xpath("//button[@aria-label='Down vote']")).click();
        List<WebElement> downvoteActive = driver.findElements(
                By.xpath("//button[@aria-label='Down vote' and @aria-pressed='true']"));
        assertFalse(downvoteActive.isEmpty());

        driver.findElement(By.xpath("//button[@aria-label='Down vote']")).click();
        List<WebElement> downvoteReset = driver.findElements(
                By.xpath("//button[@aria-label='Down vote' and @aria-pressed='false']"));
        assertFalse(downvoteReset.isEmpty());
    }
}
