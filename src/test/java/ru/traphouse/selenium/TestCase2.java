package ru.traphouse.selenium;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class TestCase2 extends BaseTest {

    // TS-02: переключаем вкладки и проверяем, что активная вкладка меняется
    @Test
    public void testCase2() {
        navigateTo(BASE_URL + "/questions");

        // ждём загрузки страницы перед первым кликом
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//a[contains(.,'Active')]")));
        driver.findElement(By.xpath("//a[contains(.,'Active')]")).click();
        // вкладка должна получить класс is-selected
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//a[contains(.,'Active') and contains(@class,'is-selected')]")));

        driver.findElement(By.xpath("//a[contains(.,'Unanswered')]")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//a[contains(.,'Unanswered') and contains(@class,'is-selected')]")));

        driver.findElement(By.xpath("//a[contains(.,'Bountied')]")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//a[contains(.,'Bountied') and contains(@class,'is-selected')]")));
    }
}
