package ru.traphouse.selenium;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class TestCase17 extends BaseTest {

    @Test
    public void testCase17() {
        AuthUtils.login(driver);

        driver.findElement(By.xpath("//a[@id='user-profile-button']/div/img")).click();

        WebElement navItem = driver.findElement(By.xpath("//nav/ul/li[3]/a/span"));
        js.executeScript("arguments[0].click();", navItem);

        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("(//div[contains(@class,'s-post-summary')]//h3/a)[1]")));
        driver.findElement(By.xpath("(//div[contains(@class,'s-post-summary')]//h3/a)[1]")).click();

        WebElement upvoteBtn = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//button[@data-threadable-comment-target='upvoteArrow']")));
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", upvoteBtn);
        js.executeScript("arguments[0].click();", upvoteBtn);

        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//button[@aria-label=\"You've voted for this as a useful comment\"]")));

        assertFalse(driver.findElements(
                By.xpath("//button[@aria-label=\"You've voted for this as a useful comment\"]")).isEmpty());
    }
}
