package ru.levelp.at.homework3;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

//Второе задание
public class SendRuleEmailTest extends YandexEmailBaseClassTest {
    @Test
    void sendRuleEmail() {
        final String subject = "Тест тема";
        //Вход в почту и проверка
        logInYandexEmail();
        assertThat(driver.getTitle()).contains(expectedTitle);
        //Создание и отправка нового письма
        createNewEmail(testLoginEmail, subject, testText);
        wait.until(ExpectedConditions
            .presenceOfElementLocated(By.cssSelector(".ComposeSendButton > button"))).click();
        wait.until(ExpectedConditions
            .presenceOfElementLocated(By.cssSelector(".ComposeDoneScreen-Actions > a"))).click();
        //Проверка появления письмо в отправленных
        wait.until(ExpectedConditions
            .presenceOfElementLocated(By.cssSelector("[data-react-focusable-id='4']"))).click();
        wait.until(ExpectedConditions
            .presenceOfElementLocated(By.className("Layout-m__sync--3L1uJ"))).click();
        boolean isLetterInSent = tryIsDisplayed(".mail-MessageSnippet-FromText");
        assertTrue(isLetterInSent);
        //Переход в папку Тест
        wait.until(ExpectedConditions
            .presenceOfElementLocated(By.className("Layout-m__sync--3L1uJ"))).click();
        wait.until(ExpectedConditions
            .presenceOfElementLocated(By.cssSelector("[data-react-focusable-id='1']"))).click();
        wait.until(ExpectedConditions
            .presenceOfElementLocated(By.cssSelector("[data-react-focusable-id='8']"))).click();
        wait.until(ExpectedConditions
            .presenceOfElementLocated(By.cssSelector("[data-react-focusable-id='1']"))).click();
        //Проверка атрибутов полученного письма
        wait.until(ExpectedConditions
            .presenceOfElementLocated(By.className("Layout-m__sync--3L1uJ"))).click();
        String actualEmail = tryGetTitleAttribute(".mail-MessageSnippet-FromText");
        String actualSubject = tryGetTitleAttribute(".mail-MessageSnippet-Item_subject > span");
        String actualText = tryGetTitleAttribute(".mail-MessageSnippet-Item_firstline > span");
        assertEquals(testLoginEmail, actualEmail);
        assertEquals(subject, actualSubject);
        assertEquals(testText, actualText);
        //Удаление пиcьма из отправленных и папки Тест
        deleteOneMail();
        wait.until(ExpectedConditions
            .presenceOfElementLocated(By.cssSelector("[data-react-focusable-id='4']"))).click();
        deleteOneMail();
        //Очистка корзины
        cleanTrash();
        //Выход из учетной записи
        logOutYandexEMail();
    }
}
