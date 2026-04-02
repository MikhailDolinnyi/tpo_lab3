package ru.traphouse.selenium;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestCase8 extends BaseTest {

    @Test
    public void testCase8() {
        navigateTo(BASE_URL + "/questions");

        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("(//div[@id='questions']//div[contains(@class,'s-post-summary')]//h3//a)[1]")));
        driver.findElement(By.xpath(
                "(//div[@id='questions']//div[contains(@class,'s-post-summary')]//h3//a)[1]")).click();

        List<WebElement> title = driver.findElements(
                By.xpath("//div[@id='question-header']//h1/a[contains(@class,'question-hyperlink')]"));
        assertFalse(title.isEmpty());

        List<WebElement> body = driver.findElements(
                By.xpath("//*[@id='question']//div[contains(@class,'s-prose') and contains(@class,'js-post-body')]"));
        assertFalse(body.isEmpty());

        List<WebElement> answers = driver.findElements(By.xpath("//div[@id='answers']"));
        assertFalse(answers.isEmpty());

        List<WebElement> comments = driver.findElements(
                By.xpath("//div[starts-with(@id,'comments-') and contains(@class,'comments')]"));
        List<WebElement> addComment = driver.findElements(
                By.xpath("//a[contains(@class,'comments-link')]"));
        assertTrue(!comments.isEmpty() || !addComment.isEmpty());
    }
}
