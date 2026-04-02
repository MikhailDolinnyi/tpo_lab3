package ru.traphouse.selenium;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestCase16 extends BaseTest {

    @Test
    public void testCase16() {
        AuthUtils.login(driver);

        driver.findElement(By.xpath("//a[@id='user-profile-button']")).click();

        WebElement navItem = driver.findElement(By.xpath("//nav/ul/li[3]/a/span"));
        js.executeScript("arguments[0].click();", navItem);

        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("(//div[contains(@class,'s-post-summary')]//h3/a)[1]")));
        driver.findElement(By.xpath("(//div[contains(@class,'s-post-summary')]//h3/a)[1]")).click();

        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'Edit')]"))).click();

        WebElement editor = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[contains(@class,'outline-none')]")));
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", editor);
        js.executeScript("arguments[0].click();", editor);
        js.executeScript(
                "if(arguments[0].contentEditable === 'true') { arguments[0].innerHTML = arguments[1]; }",
                editor, TestData.QUESTION_BODY_EDIT);

        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(.,'Save changes')]"))).click();

        wait.until(d -> d.getCurrentUrl() != null && d.getCurrentUrl().contains("/staging-ground/"));
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl != null && currentUrl.startsWith(BASE_URL + "/staging-ground/"));
    }
}
