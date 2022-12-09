package ru.levelp.at.homework3;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

//Первое задание
class SendDraftEmailTest extends YandexEmailBaseClassTest {
    @Test
    void sendDraftEmail() {
        //Вход в почту и проверка
        logInYandexEmail();
        assertThat(driver.getTitle()).contains(expectedTitle);
        //Создание нового письма
        createNewEmail(testEmail, testSubject, testText);
        //Сохранение его в черновики
        wait.until(ExpectedConditions
            .presenceOfElementLocated(By.className("qa-ControlButton_button_close"))).click();
        //Проверка сохранения письма
        wait.until(ExpectedConditions
            .presenceOfElementLocated(By.cssSelector("[data-react-focusable-id='6']"))).click();
        wait.until(ExpectedConditions
            .presenceOfElementLocated(By.className("Layout-m__sync--3L1uJ"))).click();
        String actualEmail = tryGetTitleAttribute(".mail-MessageSnippet-FromText");
        String actualSubject = tryGetTitleAttribute(".mail-MessageSnippet-Item_subject > span");
        String actualText = tryGetTitleAttribute(".mail-MessageSnippet-Item_firstline > span");
        assertEquals(testEmail, actualEmail);
        assertEquals(testSubject, actualSubject);
        assertEquals(testText, actualText);
        //Отправка письма
        wait.until(ExpectedConditions
            .presenceOfElementLocated(By.className("_nb-checkbox-normal-flag"))).click();
        wait.until(ExpectedConditions
            .presenceOfElementLocated(By.className("mail-MessageSnippet-Item_sender"))).click();
        wait.until(ExpectedConditions
            .presenceOfElementLocated(By.cssSelector(".ComposeSendButton > button"))).click();
        //Закрытие всплывающего окна
        wait.until(ExpectedConditions
            .presenceOfElementLocated(By.cssSelector(".ComposeDoneScreen-Actions > a"))).click();
        //Проверка исчезновения письма из черновиков
        wait.until(ExpectedConditions
            .presenceOfElementLocated(By.cssSelector("[data-react-focusable-id='6']"))).click();
        boolean isLetterInDrafts = tryIsDisplayed(".messages-empty__header--2ws56");
        assertTrue(isLetterInDrafts);
        //Проверка появления письма в отправленных
        wait.until(ExpectedConditions
            .presenceOfElementLocated(By.cssSelector("[data-react-focusable-id='4']"))).click();
        wait.until(ExpectedConditions
            .presenceOfElementLocated(By.className("Layout-m__sync--3L1uJ"))).click();
        boolean isLetterInSent = tryIsDisplayed(".mail-MessageSnippet-FromText");
        assertTrue(isLetterInSent);
        //Удаление пиcьма из отправленных
        deleteOneMail();
        //Очистка корзиныс
        cleanTrash();
        //Выход из учетной записи
        logOutYandexEMail();
    }
}
