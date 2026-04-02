package ru.traphouse.selenium;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class TestCase4 extends BaseTest {

    @Test
    public void testCase4() {
        navigateTo(BASE_URL + "/questions");

        WebElement searchInput = driver.findElement(By.xpath("//input[@name='q']"));
        searchInput.sendKeys("selenium webdriver");
        searchInput.submit();

        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[contains(@class,'s-post-summary')]")));
    }
}
