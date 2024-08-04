package pageObjects;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import lombok.Getter;

@Getter
public class LoginPage {
    private final Locator btnEmail;
    private final Locator btnPassword;
    private final Locator btnLogin;
    private final Locator btnForgotPass;
    private final Locator btnSignUp;
    private final Locator btnLang;
    private final Locator btnLangRU;
    private final Locator btnLangENG;

    public LoginPage(Page page) {
        this.btnEmail = page.locator("//*[@id=\"single-spa-application:@jod/topbar\"]/div[2]/div/div[2]/form/div[1]/div/input");
        this.btnPassword = page.locator("//*[@id=\"single-spa-application:@jod/topbar\"]/div[2]/div/div[2]/form/div[2]/div/input");
        this.btnLogin = page.locator("//*[@id=\"single-spa-application:@jod/topbar\"]/div[2]/div/div[2]/form/button");
        this.btnForgotPass = page.locator("//*[@id=\"single-spa-application:@jod/topbar\"]/div[2]/div/div[2]/form/div[3]/a");
        this.btnSignUp = page.locator("//*[@id=\"single-spa-application:@jod/topbar\"]/div[2]/div/div[2]/div[2]/a");
        this.btnLang = page.locator("//*[@id=\"single-spa-application:@jod/topbar\"]/div[2]/div/div[1]/header/div[2]/div/div");
        this.btnLangRU = page.locator("//*[@id=\"menu-\"]/div[3]/ul/li[2]");
        this.btnLangENG = page.locator("//*[@id=\"menu-\"]/div[3]/ul/li[3]");
    }
}
