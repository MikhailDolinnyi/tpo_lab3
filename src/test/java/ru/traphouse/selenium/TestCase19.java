package ru.traphouse.selenium;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class TestCase19 extends BaseTest {

    // TS-19: проверяем что после логина отображается кнопка профиля
    @Test
    public void testCase19() {
        AuthUtils.login(driver);

        // user-profile-button есть только у авторизованного пользователя
        assertFalse(driver.findElements(By.xpath("//a[@id='user-profile-button']")).isEmpty());
    }
}
