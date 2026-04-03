package ru.traphouse.selenium;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class TestCase5 extends BaseTest {

    // TS-05: поиск через синтаксис тега [python], проверяем что результаты с тегом python
    @Test
    public void testCase5() {
        navigateTo(BASE_URL + "/questions");

        WebElement searchInput = driver.findElement(By.xpath("//input[@name='q']"));
        searchInput.sendKeys("[python]");
        searchInput.submit();

        WebElement filterBtn = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//button[contains(.,'Filter')]")));

        // иногда всплывает оверлей, убираем его перед кликом
        OverlayUtils.handleOverlays(driver);

        try {
            filterBtn.click();
        } catch (Exception e) {
            // если клик заблокирован — жмём через JS
            js.executeScript("arguments[0].click();", filterBtn);
        }

        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//button[contains(.,'Apply filter')]")));

        // в результатах должны быть ссылки с тегом python
        List<WebElement> pythonTags = driver.findElements(
                By.xpath("//a[contains(@href,'/questions/tagged/python')]"));
        assertFalse(pythonTags.isEmpty());
    }
}
