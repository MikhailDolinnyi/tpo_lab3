package ru.traphouse.selenium;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class TestCase1 extends BaseTest {

    @Test
    public void testCase1() {
        navigateTo(BASE_URL + "/questions");

        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//h1[contains(.,'Newest Questions')]")));

        List<WebElement> newest = driver.findElements(By.xpath("//span[contains(.,'Newest')]"));
        assertFalse(newest.isEmpty());

        List<WebElement> active = driver.findElements(By.xpath("//span[contains(.,'Active')]"));
        assertFalse(active.isEmpty());

        List<WebElement> bountied = driver.findElements(By.xpath("//span[contains(.,'Bountied')]"));
        assertFalse(bountied.isEmpty());

        List<WebElement> unanswered = driver.findElements(By.xpath("//span[contains(.,'Unanswered')]"));
        assertFalse(unanswered.isEmpty());

        List<WebElement> askQuestion = driver.findElements(By.xpath("//a[contains(.,'Ask Question')]"));
        assertFalse(askQuestion.isEmpty());

        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[contains(@class,'s-post-summary')]")));
    }
}
