package ru.traphouse.selenium;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class TestCase6 extends BaseTest {

    @Test
    public void testCase6() {
        navigateTo(BASE_URL + "/tags");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[contains(.,'Tags')]")));

        List<WebElement> postTags = driver.findElements(By.xpath("//a[contains(@class,'post-tag')]"));
        assertFalse(postTags.isEmpty());

        List<WebElement> secondTag = driver.findElements(By.xpath("(//a[contains(@class,'post-tag')])[2]"));
        assertFalse(secondTag.isEmpty());

        List<WebElement> popularSort = driver.findElements(By.xpath("//a[contains(.,'Popular')]"));
        assertFalse(popularSort.isEmpty());

        List<WebElement> nameSort = driver.findElements(By.xpath("//a[contains(.,'Name')]"));
        assertFalse(nameSort.isEmpty());

        List<WebElement> newSort = driver.findElements(By.xpath("//a[contains(.,'New')]"));
        assertFalse(newSort.isEmpty());
    }
}
