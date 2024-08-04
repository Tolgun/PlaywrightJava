package constantData;

import io.cucumber.java.*;

public class Hooks {

    @Before(order = 1)
    public void before() {
        Initialize.init();
    }

    @After
    public void after(Scenario scenario) {
        if (scenario.isFailed()) {
            Initialize.takeScreenshot(scenario.getName());
        }
        Initialize.close();
    }

}
