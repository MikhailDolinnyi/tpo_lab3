package ru.traphouse.selenium;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class TestCase18 extends BaseTest{

    @Test
    public void testCase18() {
        AuthUtils.login(driver);

        driver.findElement(By.xpath("//a[@id='user-profile-button']/div/img")).click();

        WebElement navItem = driver.findElement(By.xpath("//nav/ul/li[3]/a/span"));
        js.executeScript("arguments[0].click();", navItem);

        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("(//div[contains(@class,'s-post-summary')]//h3/a)[1]")));
        driver.findElement(By.xpath("(//div[contains(@class,'s-post-summary')]//h3/a)[1]")).click();

        WebElement comment = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[contains(@class,'comment-body')]")));

        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", comment);

        Actions actions = new Actions(driver);
        actions.moveToElement(comment).perform();

        WebElement flagBtn = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[contains(@class,'comment-body')]/..//button[2]")));
        js.executeScript("arguments[0].click();", flagBtn);
    }
}
