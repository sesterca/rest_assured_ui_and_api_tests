import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attachments;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static io.restassured.RestAssured.baseURI;

public class BaseTest {

    String login = "m.osip@icloud.com";
    String password = "29071986";
    String cookieAuthName = "NOPCOMMERCE.AUTH";
    String compareListCookieName = "nop.CompareProducts";
    String viewedCookie = "NopCommerce.RecentlyViewedProducts";
    String compareListCookie = "CompareProductIds=";

    @BeforeAll
    static void config(){

        SelenideLogger.addListener("allure", new AllureSelenide());

        baseURI = "http://demowebshop.tricentis.com";
        baseUrl = "http://demowebshop.tricentis.com";

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;
    }

    @AfterEach
    void addAttachments(){
        Attachments.screenshotAs("Screenshot");
        Attachments.pageSource();
        Attachments.browserConsoleLogs();
        Attachments.addVideo();
        closeWebDriver();
    }
}
