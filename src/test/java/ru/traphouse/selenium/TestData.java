package ru.traphouse.selenium;

import java.util.Random;
import java.util.UUID;

public class TestData {

    private static final Random RANDOM = new Random();

    private static final String[] TITLE_TEMPLATES = {
        "How to handle %s blocking element clicks in Selenium WebDriver (Java)?",
        "Selenium Java: %s causes ElementClickInterceptedException — best practices?",
        "ElementClickInterceptedException due to %s overlay — how to fix reliably?",
        "WebDriver click fails because of %s — what is the proper solution?",
        "Dealing with %s popups intercepting clicks in Selenium WebDriver tests",
    };

    private static final String[] OVERLAY_SUBJECTS = {
        "cookie consent banner",
        "Google One Tap popup",
        "GDPR consent dialog",
        "floating notification widget",
        "sticky footer overlay",
    };

    private static final String[] PROBLEM_INTROS = {
        "I am writing automated UI tests using Selenium WebDriver (Java) and keep running into",
        "While building a Selenium WebDriver test suite in Java, I frequently encounter",
        "I have been working on a Selenium WebDriver project in Java and cannot reliably avoid",
        "My Selenium WebDriver (Java) tests are consistently failing because of",
        "I am struggling to reliably handle a recurring issue in my Selenium WebDriver (Java) tests:",
    };

    private static final String[] ATTEMPTED_FIXES = {
        "I tried WebDriverWait with elementToBeClickable and scrollIntoView, but the issue persists intermittently.",
        "I attempted explicit waits and JavaScript scrollIntoView, yet the problem still occurs occasionally.",
        "I used both WebDriverWait and Actions.moveToElement, but clicks still get intercepted at times.",
        "I experimented with Thread.sleep as a quick fix and JS click as a fallback, but neither feels clean.",
        "I applied explicit waits and scrolled the element into the viewport, but it only helps sometimes.",
    };

    private static final String[] QUESTION_ENDINGS = {
        "What is the recommended approach to handle such overlays while keeping tests close to real user behavior?",
        "What is the best practice for dealing with these overlays in a maintainable and reliable way?",
        "Is there a proper pattern to dismiss these overlays without resorting to JavaScript hacks?",
        "How should I architect my test setup to handle overlays consistently across all test cases?",
        "What strategy would you recommend for making tests resilient to these kinds of dynamic overlays?",
    };

    public static final String QUESTION_BODY_EDIT =
            "<p>I am writing UI tests using Selenium WebDriver (Java) and frequently encounter an " +
            "ElementClickInterceptedException. When trying to click a button or link, the element is covered " +
            "by overlay components such as a cookie consent banner or Google One Tap popup. I have tried " +
            "using WebDriverWait with elementToBeClickable, as well as scrolling the element into view with " +
            "scrollIntoView, but the issue still occurs intermittently. Sometimes using JavaScript click " +
            "works, but it feels like a workaround rather than a proper solution. What is the recommended " +
            "approach to handle such overlays in a reliable and maintainable way while keeping the tests " +
            "close to real user behavior? +1</p>";

    public static String uniqueQuestionTitle() {
        String template = TITLE_TEMPLATES[RANDOM.nextInt(TITLE_TEMPLATES.length)];
        String subject = OVERLAY_SUBJECTS[RANDOM.nextInt(OVERLAY_SUBJECTS.length)];
        String uid = UUID.randomUUID().toString().substring(0, 6);
        String title = String.format(template, subject) + " [" + uid + "]";
        return title.length() > 50 ? title.substring(0, 50) : title;
    }

    public static String uniqueQuestionBodyText() {
        String intro = PROBLEM_INTROS[RANDOM.nextInt(PROBLEM_INTROS.length)];
        String subject = OVERLAY_SUBJECTS[RANDOM.nextInt(OVERLAY_SUBJECTS.length)];
        String fix = ATTEMPTED_FIXES[RANDOM.nextInt(ATTEMPTED_FIXES.length)];
        String ending = QUESTION_ENDINGS[RANDOM.nextInt(QUESTION_ENDINGS.length)];
        String uid = UUID.randomUUID().toString().substring(0, 8);

        return intro + " an ElementClickInterceptedException caused by a " + subject + ". " +
               fix + " " + ending + " [ref:" + uid + "]";
    }
}
