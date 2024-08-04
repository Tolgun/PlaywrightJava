package pageObjects;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import lombok.Getter;

@Getter
public class MainPage {
    private final Locator btnHome;
    private final Locator btnMenu;
    private final Locator btnLogout;
    private final Locator btnWSQA;
    private final Locator btnWSPP;
    private final Locator btnChat;

    public MainPage(Page page) {
        this.btnHome = page.locator("//*[@id=\"menu-appbar\"]/div[1]");
        this.btnLogout = page.locator("//*[@id=\"menu-appbar\"]/div[3]/ul/li[3]");
        this.btnWSQA = page.locator("//*[@id=\"single-spa-application:@jod/topbar\"]/div[2]/div/div/div/div[4]");
        this.btnWSPP = page.locator("//*[@id=\"single-spa-application:@jod/topbar\"]/div[2]/div/div/div/div[5]/div/div");
        this.btnMenu = page.locator("//*[@id=\"sidebar-container-id\"]/div[2]/div[1]/div");
        this.btnChat = page.locator("#chat-section-id").getByRole(AriaRole.BUTTON).first();
    }
}