package ru.levelp.at.homework3;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

//Третье задание
public class SendTrashEmailTest extends YandexEmailBaseClassTest {
    @Test
    void sendTrashEmailTest() {
        //Вход в почту и проверка
        logInYandexEmail();
        assertThat(driver.getTitle()).contains(expectedTitle);
        //Создание и отправка нового письма
        createNewEmail(testLoginEmail, testSubject, testText);
        wait.until(ExpectedConditions
            .presenceOfElementLocated(By.cssSelector(".ComposeSendButton > button"))).click();
        wait.until(ExpectedConditions
            .presenceOfElementLocated(By.cssSelector(".ComposeDoneScreen-Actions > a"))).click();
        //Проверка появления письма во входящих и его атрибутов
        wait.until(ExpectedConditions
            .presenceOfElementLocated(By.cssSelector("[data-react-focusable-id='relevant']"))).click();
        wait.until(ExpectedConditions
            .presenceOfElementLocated(By.className("Layout-m__sync--3L1uJ"))).click();
        String actualEmail = tryGetTitleAttribute(".mail-MessageSnippet-FromText");
        String actualSubject = tryGetTitleAttribute(".mail-MessageSnippet-Item_subject > span");
        String actualText = tryGetTitleAttribute(".mail-MessageSnippet-Item_firstline > span");
        assertEquals(testLoginEmail, actualEmail);
        assertEquals(testSubject, actualSubject);
        assertEquals(testText, actualText);
        //Удаление письма
        deleteOneMail();
        //Проверка появления письма в корзине
        wait.until(ExpectedConditions
            .presenceOfElementLocated(By.cssSelector("[data-react-focusable-id='3']"))).click();
        boolean isLetterInTrash = tryIsDisplayed(".mail-MessageSnippet-FromText");
        assertTrue(isLetterInTrash);
        //Очистка отправленных
        wait.until(ExpectedConditions
            .elementToBeClickable(By.cssSelector("[data-react-focusable-id='4']"))).click();
        deleteOneMail();
        //Очистка корзины
        cleanTrash();
        //Выход из учетной записи
        logOutYandexEMail();
    }
}
