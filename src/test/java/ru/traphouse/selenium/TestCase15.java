package ru.traphouse.selenium;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestCase15 extends BaseTest {

    @Test
    public void testCase15() {
        AuthUtils.login(driver);
        navigateTo(BASE_URL + "/");

        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(.,'Ask Question')]"))).click();

        // TITLE
        WebElement titleInput = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//input[@id='post-title-input']")));
        titleInput.click();
        titleInput.sendKeys(TestData.uniqueQuestionTitle());
        js.executeScript(
                "arguments[0].dispatchEvent(new Event('input', {bubbles:true}));" +
                        "arguments[0].dispatchEvent(new Event('change', {bubbles:true}));",
                titleInput);

        // клик в редактор
        WebElement editor = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[@id='editor-container-question']/div/div[3]")));
        js.executeScript("arguments[0].click();", editor);

        // BODY
        WebElement bodyEditor = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[@id='editor-container-question']//*[@contenteditable='true']")));
        bodyEditor.click();
        bodyEditor.sendKeys(TestData.uniqueQuestionBodyText());
        js.executeScript(
                "arguments[0].dispatchEvent(new Event('input', {bubbles:true}));" +
                        "arguments[0].dispatchEvent(new Event('change', {bubbles:true}));",
                bodyEditor);

        // TAG
        WebElement tagInput = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//input[@id='filterInput']")));
        tagInput.click();
        tagInput.sendKeys("test");

        WebElement suggestion = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@id='tag-searchResults']/div/div/div/span/span")));
        suggestion.click();

        // SUBMIT
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(.,'Submit to Staging Ground')]"))).click();

        wait.until(d -> {
            String url = d.getCurrentUrl();
            boolean redirected = url != null && url.contains("/staging-ground/");
            boolean throttled = !d.findElements(
                    By.xpath("//div[contains(.,'You can only post once every 90 minutes')]")).isEmpty();
            return redirected || throttled;
        });

        String currentUrl = driver.getCurrentUrl();
        boolean stagingGroundReached = currentUrl != null && currentUrl.startsWith(BASE_URL + "/staging-ground/");
        boolean throttled = !driver.findElements(
                By.xpath("//div[contains(.,'You can only post once every 90 minutes')]")).isEmpty();

        assertTrue(stagingGroundReached || throttled);
    }
}
