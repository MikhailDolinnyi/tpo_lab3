package ru.traphouse.selenium;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class TestCase3 extends BaseTest {

    // TS-03: открываем панель фильтров и проверяем, что все параметры на месте
    @Test
    public void testCase3() {
        navigateTo(BASE_URL + "/questions");

        // кликаем Filter и ждём, пока панель раскроется
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(.,'Filter')]"))).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//button[contains(.,'Apply filter')]")));

        // все эти лейблы должны присутствовать в панели фильтрации
        String[] filterLabels = {
            "No answers",
            "No upvoted or accepted answers",
            "No Staging Ground",
            "Has bounty",
            "My watched tags",
            "The following tags:",
            "Newest",
            "Recent activity",
            "Highest score",
            "Most frequent",
            "Bounty ending soon",
            "Trending",
            "Most activity"
        };

        for (String label : filterLabels) {
            List<WebElement> elements = driver.findElements(
                    By.xpath("//label[contains(.,'" + label + "')]"));
            assertFalse(elements.isEmpty(), "Filter label not found: " + label);
        }

        List<WebElement> applyBtn = driver.findElements(By.xpath("//button[contains(.,'Apply filter')]"));
        assertFalse(applyBtn.isEmpty());
    }
}
