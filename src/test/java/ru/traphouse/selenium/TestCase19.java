package ru.traphouse.selenium;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class TestCase19 extends BaseTest {

    @Test
    public void testCase19() {
        AuthUtils.login(driver);

        assertFalse(driver.findElements(By.xpath("//a[@id='user-profile-button']")).isEmpty());
    }
}
