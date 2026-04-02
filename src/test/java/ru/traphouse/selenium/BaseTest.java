package ru.traphouse.selenium;

import java.time.Duration;

import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

public abstract class BaseTest {

    protected static final String BASE_URL = "https://stackoverflow.com";
    protected static final int TIMEOUT = 30;

    protected WebDriver driver;
    protected JavascriptExecutor js;
    protected WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        driver = new FirefoxDriver(buildFirefoxOptions());
        js = (JavascriptExecutor) driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    protected void navigateTo(String url) {
        driver.get(url);
    }

    private FirefoxOptions buildFirefoxOptions() {
        FirefoxOptions options = new FirefoxOptions();

        String profilePath = Dotenv.load().get("FIREFOX_PROFILE");
        FirefoxProfile profile = (profilePath != null && !profilePath.isBlank())
                ? new FirefoxProfile(new File(profilePath))
                : new FirefoxProfile();

        profile.setPreference("dom.webdriver.enabled", false);
        profile.setPreference("useAutomationExtension", false);
        options.setProfile(profile);

        return options;
    }
}
