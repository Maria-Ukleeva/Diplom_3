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

public class LoginTest {
    private WebDriver driver;
    public String email;
    public String password;
    public User user;

    @Before
    public void createNewDriverAndUserOpenMainPage() {
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
    }

    @Test
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной")
    public void checkLoginFromMainPage(){
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage();
        mainPage.clickLoginButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillInAllFields(email, password);
        loginPage.clickLoginButton();
        new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//button[text() = 'Оформить заказ']")));
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals("https://stellarburgers.nomoreparties.site/", actualUrl);
    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    public void checkLoginFromProfile(){
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage();
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.openProfile();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillInAllFields(email, password);
        loginPage.clickLoginButton();
        new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//button[text() = 'Оформить заказ']")));
        String actualUrl = driver.getCurrentUrl();
        Assert.assertTrue(driver.findElement(By.xpath(".//button[text() = 'Оформить заказ']")).isDisplayed());
        Assert.assertEquals("https://stellarburgers.nomoreparties.site/", actualUrl);
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    public void checkLoginFromRegistrationPage(){
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.openRegistrationPage();
        registrationPage.goToLogin();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillInAllFields(email, password);
        loginPage.clickLoginButton();
        new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//button[text() = 'Оформить заказ']")));
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals("https://stellarburgers.nomoreparties.site/", actualUrl);
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    public void checkLoginFromForgotPasswordPage(){
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
        forgotPasswordPage.openForgotPasswordPage();
        forgotPasswordPage.goToLogin();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillInAllFields(email, password);
        loginPage.clickLoginButton();
        new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//button[text() = 'Оформить заказ']")));
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals("https://stellarburgers.nomoreparties.site/", actualUrl);
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
