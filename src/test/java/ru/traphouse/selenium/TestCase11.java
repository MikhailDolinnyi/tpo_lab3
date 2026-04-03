package ru.traphouse.selenium;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class TestCase11 extends BaseTest {

    // TS-11: авторизуемся и проверяем что форма создания вопроса открывается
    @Test
    public void testCase11() {
        AuthUtils.login(driver);

        driver.findElement(By.xpath("//a[contains(text(),'Ask Question')]")).click();
        // блок тегов — признак того что форма полностью загрузилась
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[@id='tags-component']")));
    }
}
