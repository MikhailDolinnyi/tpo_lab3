package ru.traphouse.selenium;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class TestCase11 extends BaseTest {

    @Test
    public void testCase11() {
        AuthUtils.login(driver);

        driver.findElement(By.xpath("//a[contains(text(),'Ask Question')]")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[@id='tags-component']")));
    }
}
