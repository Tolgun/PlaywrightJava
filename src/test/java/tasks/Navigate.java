package tasks;

import com.microsoft.playwright.Page;
import org.junit.Assert;
import constantData.Initialize;

public class Navigate {
    public static void toMAINPage(Page page) {
        if(Initialize.environment == null || Initialize.environment.equals("QA")) {
            page.navigate("https://EXAMPLE.com/signin?authRedirect=/overview");
        }else if(Initialize.environment.equals("DEV")){
            page.navigate("https://EXAMPLE.com/signin?authRedirect=/overview");
        }else if(Initialize.environment.equals("PP")){
            page.navigate("https://EXAMPLE.com/signin?authRedirect=/overview");
        }else{
            Assert.fail("Заданная среда: " + Initialize.environment + " не найдена!");
        }
    }
}
