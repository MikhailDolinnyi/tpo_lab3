package ru.traphouse.selenium;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class TestCase7 extends BaseTest {

    // TS-07: находим тег python через фильтр и переходим на страницу с вопросами по нему
    @Test
    public void testCase7() {
        navigateTo(BASE_URL + "/tags");

        // вводим в поиск тегов "python" и жмём Enter
        WebElement tagFilter = driver.findElement(By.xpath("//input[@id='tagfilter']"));
        tagFilter.sendKeys("python\n");

        // кликаем по тегу python в отфильтрованном списке
        WebElement pythonTag = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//a[contains(@class,'post-tag') and text()='python']")));
        pythonTag.click();

        // сортируем по новым
        driver.findElement(By.xpath("//a[contains(.,'Newest')]")).click();

        // убеждаемся что вопросы загрузились
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[contains(@class,'s-post-summary')]")));
    }
}
