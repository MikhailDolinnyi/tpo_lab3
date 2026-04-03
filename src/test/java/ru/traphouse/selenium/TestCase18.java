package ru.traphouse.selenium;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class TestCase18 extends BaseTest{

    // TS-18: наводим мышь на комментарий и нажимаем кнопку Flag
    @Test
    public void testCase18() {
        AuthUtils.login(driver);

        // вопрос с гарантированным комментарием
        driver.get(BASE_URL + "/staging-ground/79919244");

        WebElement comment = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[contains(@class,'comment-body')]")));

        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", comment);

        // кнопки у комментария появляются только при hover
        Actions actions = new Actions(driver);
        actions.moveToElement(comment).perform();

        // вторая кнопка рядом с comment-body — это Flag
        WebElement flagBtn = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[contains(@class,'comment-body')]/..//button[2]")));
        js.executeScript("arguments[0].click();", flagBtn);
    }
}
