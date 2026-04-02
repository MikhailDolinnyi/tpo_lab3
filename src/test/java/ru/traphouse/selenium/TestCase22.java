package ru.traphouse.selenium;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class TestCase22 extends BaseTest {

    @Test
    public void testCase22() {
        navigateTo(BASE_URL + "/questions");

        driver.findElement(By.xpath("//a[contains(text(),'Ask Question')]")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//p[contains(.,'You must be logged in to ask a question on Stack Overflow')]")));

        assertFalse(driver.findElements(
                By.xpath("//p[contains(.,'You must be logged in to ask a question on Stack Overflow')]")).isEmpty());
    }
}
