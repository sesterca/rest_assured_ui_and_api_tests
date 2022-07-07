import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.*;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class UiAndApiTests extends BaseTest{

    @Test
    public void loginTest(){
        String authCookieValue = given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("Email", login)
                .formParam("Password", password)
                .log().uri()
                .log().params()
                .when()
                .post(baseURI + "/login")
                .then()
                .statusCode(302)
                .extract().cookie(cookieAuthName);

        Selenide.open(baseUrl + "/Themes/DefaultClean/Content/images/logo.png");

        Cookie authCookie = new Cookie(cookieAuthName, authCookieValue);
        WebDriverRunner.getWebDriver().manage().addCookie(authCookie);

       open(baseUrl);

        $(".account").shouldHave(text("m.osip@icloud.com"));
    }

    @Test
    public void addProductsToCompareList(){

        String productId = given()
                .when()
                .log().all()
                .get("/build-your-cheap-own-computer")
                .then()
                .statusCode(200)
                .extract().cookie(viewedCookie).substring(25);

        open(baseUrl + "/Themes/DefaultClean/Content/images/logo.png");
        Cookie compareCookie = new Cookie(compareListCookieName, compareListCookie + productId);
        WebDriverRunner.getWebDriver().manage().addCookie(compareCookie);
        refresh();

        open(baseUrl + "/compareproducts");
        Selenide.sleep(5000);
        $(".product-name").shouldHave(text("Build your own cheap computer"));
    }
}
