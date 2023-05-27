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

import static io.restassured.RestAssured.given;

public class ConstructorTest {
    private WebDriver driver;
    public String email;
    public String password;
    public User user;

    @Before
    public void createNewDriverAndUserAndLogin() {
        user = TestDataGenerator.getData();
        email = user.getEmail();
        password = user.getPassword();
        given()
                .header("Content-type", "application/json")
                .and()
                .body(user)
                .when()
                .post("https://stellarburgers.nomoreparties.site/api/auth/register");
        driver = new ChromeDriver();
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage();
        mainPage.clickLoginButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillInAllFields(email, password);
        loginPage.clickLoginButton();
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.openProfile();
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор  по клику на «Конструктор»")
    public void clickOnGoToConstructor(){
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.goToConstructor();
        new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//button[text() = 'Оформить заказ']")));
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals("https://stellarburgers.nomoreparties.site/", actualUrl);
        Assert.assertTrue(driver.findElement(By.xpath(".//h1[text() = 'Соберите бургер']")).isDisplayed());
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор по клику на логотип Stellar Burgers")
    public void clickOnLogo(){
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.clickLogo();
        new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//button[text() = 'Оформить заказ']")));
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals("https://stellarburgers.nomoreparties.site/", actualUrl);
        Assert.assertTrue(driver.findElement(By.xpath(".//h1[text() = 'Соберите бургер']")).isDisplayed());
    }

    @After
    public void cleanUp(){
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(user)
                .when()
                .post("https://stellarburgers.nomoreparties.site/api/auth/login");

        String token = response.body().as(Session.class).getAccessToken().substring(7);
        given()
                .header("Content-type", "application/json")
                .auth()
                .oauth2(token)
                .delete("https://stellarburgers.nomoreparties.site/api/auth/user");

        driver.quit();
    }
}
