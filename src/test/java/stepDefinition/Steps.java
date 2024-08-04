package stepDefinition;

import com.microsoft.playwright.Page;
import constantData.Initialize;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import tasks.Login;
import tasks.Main;
import tasks.Navigate;

import java.io.ByteArrayInputStream;

public class Steps {

    private final Page page = Initialize.getPage();
    private static String lang = "";

    @Given("^Авторизация в системе (.*)$")
    public void userLogin(String language){
        lang = language;
        Navigate.toMAINPage(page);
        Login.setLang(page, lang);
        Login.useUser(page);
    }

    @And("^Выход из системы$")
    public void userLogout() {
        Login.logout(page);
    }

    @Then("^Выбор Workspace$")
    public void setWS() throws InterruptedException{
        Login.setWS(page);
    }

    @Then("^Открыть чат$")
    public void openChat() {
        Main.openChat(page);
    }

    @And("^Развернуть чат на весь экран$")
    public void expandChat(){
        Main.expandCompaseChat(page);
        Allure.step("Формат чата - развёрнут на весь экран!");
    }

    @And("^Свернуть чат в оконный режим$")
    public void compaseChat(){
        Main.expandCompaseChat(page);
        Allure.step("Формат чата - восстановлен в оконный режим!");
    }

    @Then("^Закрыть чат$")
    public void closeChat() {
        Main.closeChat(page);
    }

    @When("^Написать сообщение (.*) с текстом (.*)$")
    public void startMess(String user, String text) throws InterruptedException{
        Main.startMess(page, lang, user, text);
    }

    @When("^Создать канал (.*) добавить пользователя (.*) и отправить сообщение (.*)$")
    public void addChannel(String name, String user, String text) throws InterruptedException{
        Main.addChannel(page, lang, name, user, text);
    }

    public enum checkChatFunc {

        Copy, Forward, Edit, Delete, Select, Reaction

    }

    @When("^ЧАТ Проверка функционала по сообщениям (.*) для (.*)$")
    public void checkChatFunc(checkChatFunc operation, String value) throws InterruptedException{
        switch (operation) {
            case Copy:
                Main.chatFuncCopy(page);
                break;

            case Forward:
                Main.chatFuncForward(page, value);
                break;

            case Edit:
                Main.chatFuncEdit(page);
                break;

            case Delete:
                Main.chatFuncDelete(page);
                break;

            case Select:
                Main.chatFuncSelect(page);
                break;

            case Reaction:
                Main.chatFuncReaction(page);
                break;

            default:
                throw new IllegalStateException("Operation is not valid");
        }
        Allure.addAttachment("Screenshoot", new ByteArrayInputStream(Initialize.getPage().screenshot()));
    }

    public enum checkChanFunc {

        Leave, MakeAdmin, Edit, RemakeAdmin, RemoveMember, Info, Back, AddMember, MenuChannel, PreviousChannel, UserMenuChannel

    }

    @When("^КАНАЛЫ Проверка функционала (.*)$")
    public void checkChanFunc(checkChanFunc operation) throws InterruptedException{
        switch (operation) {
            case Leave:
                Main.leaveChan(page);
                break;

            case MakeAdmin:
                Main.makeAdmin(page);
                break;

            case Edit:
                Main.editNameChan(page);
                break;

            case RemakeAdmin:
                Main.remakeAdmin(page);
                break;

            case RemoveMember:
                Main.removeMember(page);
                break;

            case Info:
                Main.openInfoChan(page);
                break;

            case Back:
                Main.backToChan(page);
                break;

            case AddMember:
                Main.addMember(page);
                break;

            case MenuChannel:
                Main.menuChan(page);
                break;

            case UserMenuChannel:
                Main.userMenuChan(page);
                break;

            case PreviousChannel:
                Main.previousChan(page);
                break;

            default:
                throw new IllegalStateException("Operation is not valid");
        }
        Allure.addAttachment("Screenshoot", new ByteArrayInputStream(Initialize.getPage().screenshot()));
    }
}
