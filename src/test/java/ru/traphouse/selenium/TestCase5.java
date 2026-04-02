package ru.traphouse.selenium;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class TestCase5 extends BaseTest {

    @Test
    public void testCase5() {
        navigateTo(BASE_URL + "/questions");

        WebElement searchInput = driver.findElement(By.xpath("//input[@name='q']"));
        searchInput.sendKeys("[python]");
        searchInput.submit();

        WebElement filterBtn = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//button[contains(.,'Filter')]")));

        OverlayUtils.handleOverlays(driver);

        try {
            filterBtn.click();
        } catch (Exception e) {
            js.executeScript("arguments[0].click();", filterBtn);
        }

        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//button[contains(.,'Apply filter')]")));

        List<WebElement> pythonTags = driver.findElements(
                By.xpath("//a[contains(@href,'/questions/tagged/python')]"));
        assertFalse(pythonTags.isEmpty());
    }
}
