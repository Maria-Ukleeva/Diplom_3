import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;

public class RegistrationTest {
    private WebDriver driver;
    public String username;
    public String email;
    public User user;

    @Before
    public void createNewDriverAndUserOpenMainPage() {
        user = TestDataGenerator.getData();
        username = user.getName();
        email = user.getEmail();
        driver = new ChromeDriver();
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage();
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.openProfile();
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
    }

    @Test
    @DisplayName("Регистрация c паролем > 6 символов")
    public void checkRegistrationWithValidPassword() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegistrationLink();
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.fillInAllFields(username, email, Password.PASSWORD);
        registrationPage.clickRegistrationButton();
        new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//h2[text() = 'Вход']")));
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals("https://stellarburgers.nomoreparties.site/login", actualUrl);
    }

    @Test
    @DisplayName("Регистрация c паролем < 6 символов")
    public void checkRegistrationWithInvalidPassword() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegistrationLink();
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.fillInAllFields(username, email, "pass");
        registrationPage.clickRegistrationButton();
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals("https://stellarburgers.nomoreparties.site/register", actualUrl);
        Assert.assertTrue(registrationPage.ifThereIsAnError());
    }

    @After
    public void cleanUp(){
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(user)
                .when()
                .post("https://stellarburgers.nomoreparties.site/api/auth/login");
try {
    String token = response.body().as(Session.class).getAccessToken().substring(7);
    given()
            .header("Content-type", "application/json")
            .auth()
            .oauth2(token)
            .delete("https://stellarburgers.nomoreparties.site/api/auth/user");
} catch (NullPointerException ignored){}
        driver.quit();
    }
}




