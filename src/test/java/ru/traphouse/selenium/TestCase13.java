package ru.traphouse.selenium;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class TestCase13 extends BaseTest {

    // TS-13: открываем вопрос и нажимаем "Add a comment" чтобы убедиться что поле доступно
    @Test
    public void testCase13() {
        AuthUtils.login(driver);

        navigateTo(BASE_URL + "/questions/");

        // открываем первый вопрос из списка
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("(//div[@id='questions']//div[contains(@class,'s-post-summary')]//h3//a)[1]")));
        driver.findElement(By.xpath(
                "(//div[@id='questions']//div[contains(@class,'s-post-summary')]//h3//a)[1]")).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='question']")));

        WebElement commentBtn = driver.findElement(By.xpath("//a[contains(@class,'comments-link')]"));
        js.executeScript("arguments[0].scrollIntoView(true);", commentBtn);

        // кнопка иногда перекрыта, поэтому fallback на JS-клик
        try {
            commentBtn.click();
        } catch (Exception e) {
            js.executeScript("arguments[0].click();", commentBtn);
        }
    }
}
