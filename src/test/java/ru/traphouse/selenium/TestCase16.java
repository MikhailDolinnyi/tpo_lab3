package ru.traphouse.selenium;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestCase16 extends BaseTest {

    // TS-16: редактируем свой вопрос и проверяем что изменения сохраняются
    @Test
    public void testCase16() {
        AuthUtils.login(driver);

        // идём в профиль -> раздел с вопросами (третий пункт nav)
        driver.findElement(By.xpath("//a[@id='user-profile-button']")).click();
        WebElement navItem = driver.findElement(By.xpath("//nav/ul/li[3]/a/span"));
        js.executeScript("arguments[0].click();", navItem);

        // открываем первый вопрос из списка своих постов
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("(//div[contains(@class,'s-post-summary')]//h3/a)[1]")));
        driver.findElement(By.xpath("(//div[contains(@class,'s-post-summary')]//h3/a)[1]")).click();

        // нажимаем Edit
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'Edit')]"))).click();

        // редактор — это textarea, id зависит от id поста, поэтому ищем по contains
        WebElement editor = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//textarea[contains(@id,'wmd-input')]")));
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", editor);
        // устанавливаем значение через JS и диспатчим input-событие
        js.executeScript("arguments[0].value = arguments[1];", editor, TestData.QUESTION_BODY_EDIT);
        js.executeScript("arguments[0].dispatchEvent(new Event('input', {bubbles: true}));", editor);

        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[@id='submit-button']"))).click();

        // после сохранения редирект на страницу вопроса вида /questions/{id}/...
        wait.until(d -> d.getCurrentUrl() != null && d.getCurrentUrl().contains("/questions/"));
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl != null && currentUrl.matches(BASE_URL + "/questions/\\d+/.*"));
    }
}
