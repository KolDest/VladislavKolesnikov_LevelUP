package ru.levelp.at.homework3;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class YandexEmailBaseClassTest {
    protected static final String YA_RU_URL = "https://ya.ru/";
    protected static String testLoginEmail = "youdontknowanythingjohnsnow@yandex.ru";
    protected static String testLoginPassword = "testPass00";
    protected static String testEmail = "kolxy@ya.ru";
    protected static String testSubject = "Авто тема письма";
    protected static String testText = "Авто тело письма";
    protected static String expectedTitle = "Яндекс Почта";

    protected WebDriver driver;
    protected WebDriverWait wait;

    protected void logInYandexEmail() {
        driver.navigate().to(YA_RU_URL);
        wait.until(ExpectedConditions
            .presenceOfElementLocated(By.className("headline__personal-enter"))).click();
        if (driver.findElement(By.cssSelector("[data-type='phone']")).getAttribute("aria-pressed").equals("true")) {
            wait.until(ExpectedConditions
                .presenceOfElementLocated(By.className("Button2_view_clear"))).click();
        }
        wait.until(ExpectedConditions
            .presenceOfElementLocated(By.id("passp-field-login"))).sendKeys(testLoginEmail);
        wait.until(ExpectedConditions
            .presenceOfElementLocated(By.id("passp:sign-in"))).click();
        wait.until(ExpectedConditions
            .presenceOfElementLocated(By.id("passp-field-passwd"))).sendKeys(testLoginPassword);
        wait.until(ExpectedConditions
            .presenceOfElementLocated(By.id("passp:sign-in"))).click();
        wait.until(ExpectedConditions
            .presenceOfElementLocated(By.className("headline__personal-mail-icon"))).click();
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.close();
        driver.switchTo().window(tabs.get(1));
    }

    protected void createNewEmail(String recipient, String subject, String text) {
        //Создание нового письма
        wait.until(ExpectedConditions.titleContains("Входящие — Яндекс Почта"));
        wait.until(ExpectedConditions
            .presenceOfElementLocated(By.className("ComposeButton-m__root--fP-o9"))).click();
        wait.until(ExpectedConditions
            .presenceOfElementLocated(By.id("compose-field-1"))).sendKeys(recipient);
        wait.until(ExpectedConditions
            .presenceOfElementLocated(By.id("compose-field-subject-4"))).sendKeys(subject);
        wait.until(ExpectedConditions
            .presenceOfElementLocated(By.className("cke_contents_ltr"))).sendKeys(text);
    }

    protected void logOutYandexEMail() {
        wait.until(ExpectedConditions
            .presenceOfElementLocated(By.className("user-pic__image"))).click();
        wait.until(ExpectedConditions
            .presenceOfElementLocated(By.cssSelector(".legouser__menu-item_action_exit > span"))).click();
    }

    protected void deleteOneMail() {
        driver.navigate().refresh();
        tryClick(".mail-Toolbar-Item_main-select-all > .mail-Toolbar-Item-Checkbox  > span");
        wait.until(ExpectedConditions
                .attributeToBe(By.className("js-toolbar-item-delete"), "aria-disabled", "false"));
        tryClick(".js-toolbar-item-delete");
    }

    protected void cleanTrash() {
        wait.until(ExpectedConditions
            .elementToBeClickable(By.className("Layout-m__sync--3L1uJ"))).click();
        wait.until(ExpectedConditions
            .presenceOfElementLocated(By.className("Link_view_default"))).click();
        wait.until(ExpectedConditions
            .presenceOfElementLocated(By.className("qa-LeftColumn-ConfirmPopup-ActionButton"))).click();
    }

    protected String tryGetTitleAttribute(String cssLocator) {
        for (int i = 0; i < 5; i++) {
            try {
                return wait.until(ExpectedConditions
                    .presenceOfElementLocated(By.cssSelector(cssLocator))).getAttribute("title");
            } catch (org.openqa.selenium.StaleElementReferenceException ex) {
                System.out.println("Attempt № " + i);
            }
        }
        return "Element not found";
    }

    protected boolean tryIsDisplayed(String cssLocator) {
        for (int i = 1; i < 5; i++) {
            try {
                return wait.until(ExpectedConditions
                    .presenceOfElementLocated(By.cssSelector(cssLocator))).isDisplayed();
            } catch (org.openqa.selenium.StaleElementReferenceException ex) {
                System.out.println("Attempt № " + i);
            }
        }
        return false;
    }

    protected void tryClick(String cssLocator) {
        for (int i = 1; i < 5; i++) {
            try {
                wait.until(ExpectedConditions
                    .elementToBeClickable(By.cssSelector(cssLocator))).click();
                return;
            } catch (org.openqa.selenium.StaleElementReferenceException ex) {
                System.out.println("Attempt № " + i);
            }
        }
    }

    @BeforeAll
    static void beforeALL() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterEach
    void afterEach() {
        driver.quit();
    }
}
