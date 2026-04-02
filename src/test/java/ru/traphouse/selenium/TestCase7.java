package ru.traphouse.selenium;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class TestCase7 extends BaseTest {

    @Test
    public void testCase7() {
        navigateTo(BASE_URL + "/tags");

        WebElement tagFilter = driver.findElement(By.xpath("//input[@id='tagfilter']"));
        tagFilter.sendKeys("python\n");

        WebElement pythonTag = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//a[contains(@class,'post-tag') and text()='python']")));
        pythonTag.click();

        driver.findElement(By.xpath("//a[contains(.,'Newest')]")).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[contains(@class,'s-post-summary')]")));
    }
}
