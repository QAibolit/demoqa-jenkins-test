package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class BaseTest {

    @BeforeAll
    public static void setUp() {
        Configuration.browserSize = System.getProperty("browserSize");
        Configuration.browser = System.getProperty("browser");
        Configuration.browserVersion = System.getProperty("browserVersion");
        Configuration.remote = System.getProperty("remoteUrl");

        // -Dbrowser=${BROWSER}
        // -DbrowserVersion=${BROWSER_VERSION}
        // -DbrowserSize=${BROWSER_SIZE}
//        Configuration.browserSize = "1920x1080";
        // -DremoteUrl=https://user1:1234@${REMOTE_URL}/wd/hub"
//        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";

        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        Configuration.timeout = 10000;

//        Configuration.browser = System.getProperty("browser", "chrome");
//        Configuration.browserVersion = System.getProperty("browserVersion", "132");
//        Configuration.browserSize = "1920x1080";

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;

        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterEach
    public void tearDown() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
        closeWebDriver();
    }
}
