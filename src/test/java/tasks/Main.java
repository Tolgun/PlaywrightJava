package tasks;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.MouseButton;
import io.qameta.allure.Allure;
import pageObjects.ChatBotPage;
import pageObjects.MainPage;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import constantData.Initialize;

import java.io.ByteArrayInputStream;

public class Main {
    private static final Logger log = LoggerFactory.getLogger(Login.class);

    public static void openChat(Page page) {
        MainPage mainPage = new MainPage(page);
        mainPage.getBtnChat().click();
    }

    public static void closeChat(Page page) {
        ChatBotPage chatBotPage = new ChatBotPage(page);
        chatBotPage.getBtnCloseChat().click();
        if (chatBotPage.getBtnCloseChat().isHidden()){
            Allure.step("Чат успешно закрыт");
        }else{
            Assert.fail("Чат не закрылся");
        }
    }

    public static void startMess(Page page, String lang, String value, String text) throws InterruptedException {
        ChatBotPage chatBotPage = new ChatBotPage(page);
        if (lang.equals("RU")){
            chatBotPage.getBtnStartMessageRU().click();
        }else if(lang.equals("ENG")){
            chatBotPage.getBtnStartMessageENG().click();
        }else{
            Assert.fail("Язык не определён");
        }
        chatBotPage.getBtnSearchUser().fill(value);
        chatBotPage.getBtnSearchUser().waitFor();
        chatBotPage.getBtnUseUser().click();
        chatBotPage.getBtnTextChat().fill(text);
        chatBotPage.getBtnSendMess().click();
    }

    public static void addChannel(Page page, String lang, String name, String value, String text) throws InterruptedException {
        ChatBotPage chatBotPage = new ChatBotPage(page);
        if (lang.equals("RU")){
            chatBotPage.getBtnCreateChannelRU().click();
        }else if(lang.equals("ENG")){
            chatBotPage.getBtnCreateChannelENG().click();
        }else{
            Assert.fail("Язык не определён");
        }
        chatBotPage.getBtnEnterNameChan().fill(name + Math.random());
        chatBotPage.getBtnSearchUserChan().fill(value);
        chatBotPage.getBtnSearchUserChan().waitFor();
        if(Initialize.environment.equals("QA")){
            chatBotPage.getBtnTakeUserChanQA().click();
        }else if(Initialize.environment.equals("PP")){
            chatBotPage.getBtnTakeUserChanPP().click();
        }else{
            Assert.fail("Не выбрана среда для выполнения тестов");
        }

        chatBotPage.getBtnNextCreateChan().click();
        String text1 = chatBotPage.getWnwCopied().innerText();
        if(chatBotPage.getWnwCreateChan().isVisible()){
            Allure.step("Отображение в плашке: " + text1);
            Allure.addAttachment("Screenshoot", new ByteArrayInputStream(Initialize.getPage().screenshot()));
        }else{
            Assert.fail("Плашка по созданию канала не отобразилась");
        }
        chatBotPage.getBtnTextChat().fill(text);
        chatBotPage.getBtnSendMess().click();
    }

    public static void expandCompaseChat(Page page) {
        ChatBotPage chatBotPage = new ChatBotPage(page);
        chatBotPage.getBtnExpandCompaseChat().click();
    }

    public static void chatFuncCopy(Page page){
        ChatBotPage chatBotPage = new ChatBotPage(page);
        chatBotPage.getBtnMenuM().click(new Locator.ClickOptions()
                .setButton(MouseButton.RIGHT));
        chatBotPage.getBtnCopyM().click();
        String text = chatBotPage.getWnwCopied().innerText();
        if (chatBotPage.getWnwCopied().isVisible()){
            Allure.step("Отображение в плашке: " + text);
        }else{
            Assert.fail("Плашка по копированию сообщения не отобразилась");
        }

    }

    public static void chatFuncForward(Page page, String value) throws InterruptedException {
        ChatBotPage chatBotPage = new ChatBotPage(page);
        chatBotPage.getBtnMenuM().click(new Locator.ClickOptions()
                .setButton(MouseButton.RIGHT));
        chatBotPage.getBtnForwardM().click();
        chatBotPage.getWnwForwardU().fill(value);
        chatBotPage.getWnwForwardU().waitFor();
        chatBotPage.getWnwForwardT().click();
        if(chatBotPage.getWnwForwardI().isVisible()){
            chatBotPage.getBtnTextChat().fill("Forward message");
            chatBotPage.getBtnSendMess().click();
            Allure.step("Функция пересылки сообщения выполнена успешно");
        }else{
            Assert.fail("Не отобразилось окно пересылки сообщения");
        }
    }

    public static void chatFuncEdit(Page page){
        ChatBotPage chatBotPage = new ChatBotPage(page);
        chatBotPage.getBtnMenuM().click(new Locator.ClickOptions()
                .setButton(MouseButton.RIGHT));
        chatBotPage.getBtnEditM().click();
        if (chatBotPage.getWnwEditI().isVisible()){
            chatBotPage.getBtnTextChat().fill("AT send a message (EDITED)");
            chatBotPage.getBtnSendMess().click();
            Allure.step("Функция редактирования сообщения выполнена успешно");
        }else{
            Assert.fail("Не отобразилось окно редактирования сообщения");
        }
    }

    public static void chatFuncDelete(Page page){
        ChatBotPage chatBotPage = new ChatBotPage(page);
        chatBotPage.getBtnMenuM().click(new Locator.ClickOptions()
                .setButton(MouseButton.RIGHT));
        chatBotPage.getBtnDeleteM().click();
        chatBotPage.getWnwDeleteY().click();
        String text = chatBotPage.getWnwCopied().innerText();
        if (chatBotPage.getWnwDeleteC().isVisible()){
            Allure.step("Отображение в плашке: " + text);
        }else{
            Assert.fail("Плашка по удалению сообщения не отобразилась");
        }
    }

    public static void chatFuncSelect(Page page){
        ChatBotPage chatBotPage = new ChatBotPage(page);
        chatBotPage.getBtnMenuM().click(new Locator.ClickOptions()
                .setButton(MouseButton.RIGHT));
        chatBotPage.getBtnSelectM().click();
        if (chatBotPage.getWnwSelectT().isVisible()){
            Allure.step("Функционал выборки сообщений работает");
        }else{
            Assert.fail("Функционал выборки сообщений не работает");
        }
    }

    public static void chatFuncReaction(Page page){
        ChatBotPage chatBotPage = new ChatBotPage(page);
        chatBotPage.getWnwSelectT().click();
        chatBotPage.getBtnMenuM().click(new Locator.ClickOptions()
                .setButton(MouseButton.RIGHT));
        chatBotPage.getBtnReactionM().click();
        if (chatBotPage.getBtnReactionC().isVisible()){
            Allure.step("Функционал реакций сообщений работает");
        }else{
            Assert.fail("Функционал реакций на сообщение не работает");
        }
    }

    public static void openInfoChan(Page page){
        ChatBotPage chatBotPage = new ChatBotPage(page);
        chatBotPage.getBtnInfoChan().click();
        if(chatBotPage.getWnwInfoChan().isVisible()) {
            Allure.step("Кликабельность информации о канале работает корректно");
        }else{
            Assert.fail("Не удалось открыть информацию о канале");
        }
    }

    public static void menuChan(Page page){
        ChatBotPage chatBotPage = new ChatBotPage(page);
        chatBotPage.getBtnMenuChan().click();
    }

    public static void userMenuChan(Page page){
        ChatBotPage chatBotPage = new ChatBotPage(page);
        chatBotPage.getBtnUserMenuChan().click();
    }

    public static void leaveChan(Page page){
        ChatBotPage chatBotPage = new ChatBotPage(page);
        chatBotPage.getBtnLeaveChan().click();
        if (chatBotPage.getWnwLeavedChanC().isVisible()){
            Allure.step("Плашка успешно отображена");
        }else{
            Assert.fail("Плашка о выходе с канала не отобразилась");
        }
    }

    public static void makeAdmin(Page page){
        ChatBotPage chatBotPage = new ChatBotPage(page);
        chatBotPage.getBtnMakeAdmin().click();
    }

    public static void remakeAdmin(Page page){
        ChatBotPage chatBotPage = new ChatBotPage(page);
        chatBotPage.getBtnReMakeAdmin().click();
    }

    public static void editNameChan(Page page){
        ChatBotPage chatBotPage = new ChatBotPage(page);
        chatBotPage.getBtnEditChan().click();
        chatBotPage.getWnwNewNameChan().fill("AT" + Math.random());
        chatBotPage.getBtnSaveNameChan().click();
        if(chatBotPage.getWnwRenamedChan().isVisible()){
            Allure.step("Плашка о переименовании успешно отображена");
        }else{
            Assert.fail("Плашка о переименовании не отобразилась");
        }
    }

    public static void backToChan(Page page){
        ChatBotPage chatBotPage = new ChatBotPage(page);
        chatBotPage.getBtnBackToChan().click();
    }

    public static void previousChan(Page page){
        ChatBotPage chatBotPage = new ChatBotPage(page);
        chatBotPage.getBtnPreviousChan().click();
    }

    public static void addMember(Page page) throws InterruptedException {
        ChatBotPage chatBotPage = new ChatBotPage(page);
        chatBotPage.getBtnAddMemberInChan().click();
        chatBotPage.getBtnUserNameInChan().fill("Dmitriy Tolgun");
        chatBotPage.getBtnUserNameInChan().waitFor();
        chatBotPage.getWnwTakeUserInChan().click();
        chatBotPage.getBtnButtonAddMembChan().click();
    }

    public static void removeMember(Page page){
        ChatBotPage chatBotPage = new ChatBotPage(page);
        chatBotPage.getBtnRemoveMember().click();
    }
}
