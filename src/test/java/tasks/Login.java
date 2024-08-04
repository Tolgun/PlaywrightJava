package tasks;

import com.microsoft.playwright.Page;
import io.qameta.allure.Allure;
import pageObjects.MainPage;
import pageObjects.LoginPage;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import constantData.Initialize;

public class Login {
    private static final Logger log = LoggerFactory.getLogger(Login.class);

    public static void useUser(Page page) {
        LoginPage loginPage = new LoginPage(page);
        loginPage.getBtnEmail().fill(Initialize.login);
        loginPage.getBtnPassword().fill(Initialize.password);
        loginPage.getBtnLogin().click();
    }

    public static void logout(Page page) {
        MainPage mainPage = new MainPage(page);
        mainPage.getBtnMenu().click();
        mainPage.getBtnLogout().click();
    }

    public static void setWS(Page page) throws InterruptedException {
        MainPage mainPage = new MainPage(page);
        if(Initialize.environment.equals("QA")){
            mainPage.getBtnWSQA().click();
            mainPage.getBtnWSQA().waitFor();
            if(mainPage.getBtnWSQA().isHidden()){
                Allure.step("Воркспейс успешно выбран!");
            }else{
                Assert.fail("Воркспейс не выбран!");
            }
        }else if(Initialize.environment.equals("PP")){
            mainPage.getBtnWSPP().click();

            if(mainPage.getBtnWSPP().isHidden()){
                Allure.step("Воркспейс успешно выбран!");
            }else{
                Assert.fail("Воркспейс не выбран!");
            }
        }else{
            Assert.fail("Не выбрана среда для выполнения тестов");
        }
    }

    public static void setLang(Page page, String lang){
        LoginPage loginPage = new LoginPage(page);
        if (lang.equals("RU")){
            loginPage.getBtnLang().click();
            loginPage.getBtnLangRU().click();
        }else if(lang.equals("ENG")){
            loginPage.getBtnLang().click();
            loginPage.getBtnLangENG().click();
        }else{
        Assert.fail("Язык не определён");
        }
    }
}
