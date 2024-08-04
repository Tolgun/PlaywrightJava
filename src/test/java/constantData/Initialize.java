package constantData;

import com.microsoft.playwright.*;
import lombok.Getter;

import java.io.*;
import java.nio.file.*;
import java.util.Arrays;

public class Initialize {

    public static String environment = "QA"; //System.getProperty("environment");       < -
    public static String login = "dtolgun"; //System.getProperty("loginsys");           < -
    public static String password = "PASS"; //System.getProperty("passwordsys");        < - For using in GITLAB CI
    public static String TestRunning = "TEST"; //System.getProperty("FULLCHECK");       < -
    public static String downloadPath = ""; //System.getProperty("PATH");               < -

    private static Playwright playwright;
    private static Browser browser;
    private static BrowserContext browserContext;
    @Getter
    private static Page page;

    public Initialize() {
        init();
    }

    public static void init() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                .setChannel("chrome")
                .setHeadless(false)
                .setArgs(Arrays.asList("--start-maximized",
                        "--enable-automation", "--no-sandbox",
                        "--disable-popup-blocking",
                        "--disable-default-apps",
                        "--disable-infobars",
                        "--disable-gpu",
                        "--disable-extensions"))
                .setSlowMo(500)
        );
        browserContext = browser.newContext(new Browser.NewContextOptions()
                .setViewportSize(null)
                .setRecordVideoDir(Paths.get("videos/"))
                .setRecordVideoSize(1920,1080));
        page = browserContext.newPage();
        //page.pause(); //For use Playwright Debugger
        //page.waitForLoadState(LoadState.DOMCONTENTLOADED); Loader DOMCONTENT
    }

    public static void close() {
        page.close();
        browserContext.close();
        browser.close();
        playwright.close();
    }

    public static void takeScreenshot(String name) {
        Allure.addAttachment(name, new ByteArrayInputStream(Initialize.getPage().screenshot()));
    }
}
