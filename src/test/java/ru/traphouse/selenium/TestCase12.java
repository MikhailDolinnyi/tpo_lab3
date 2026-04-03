package ru.traphouse.selenium;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class TestCase12 extends BaseTest {

    // TS-12: открываем вопрос с баунти и проверяем доступность редактора ответа
    @Test
    public void testCase12() {
        AuthUtils.login(driver);

        navigateTo(BASE_URL + "/questions");
        // ждём загрузку страницы перед кликом на вкладку
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//span[contains(.,'Bountied')]")));
        driver.findElement(By.xpath("//span[contains(.,'Bountied')]")).click();

        // открываем первый вопрос из списка
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("(//div[@id='questions']//div[contains(@class,'s-post-summary')]//h3//a)[1]")));
        driver.findElement(By.xpath(
                "(//div[@id='questions']//div[contains(@class,'s-post-summary')]//h3//a)[1]")).click();

        // ждём форму ответа и прокручиваем к ней
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//form[@id='post-form']")));
        js.executeScript("document.getElementById('post-form').scrollIntoView(true);");

        // кликаем в редактор и пробуем вставить текст через JS
        WebElement editorArea = driver.findElement(By.xpath("//div[@id='js-stacks-editor-container']/div/div[3]"));
        editorArea.click();
        js.executeScript(
                "if(arguments[0].contentEditable === 'true') {arguments[0].innerText = '<p>Woaaapo</p>'}",
                editorArea);
    }
}
