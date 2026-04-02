package ru.traphouse.selenium;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class OverlayUtils {

    public static void handleOverlays(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript("""
                ['onetrust-consent-sdk', 'onetrust-banner-sdk', 'credential_picker_container']
                    .forEach(function(id) {
                        var el = document.getElementById(id);
                        if (el) el.remove();
                    });
                """);
    }
}
