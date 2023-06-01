import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class LoginTest extends BaseTest {
    private WebDriver driver;
    public String email;
    public String password;
    public User user;



    @Before
    public void createUser() {
        driver = super.driver;
        user = RegisterApi.createUser();
        RegisterApi.registerUser(user);
        email = user.getEmail();
        password = user.getPassword();
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
        mainPage.waitPageToLoad();
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(Constants.BASEURL, actualUrl);
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
        mainPage.waitPageToLoad();
        String actualUrl = driver.getCurrentUrl();
        Assert.assertTrue(mainPage.checkIsConstructorDisplayed());
        Assert.assertEquals(Constants.BASEURL, actualUrl);
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
        MainPage mainPage = new MainPage(driver);
        mainPage.waitPageToLoad();
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(Constants.BASEURL, actualUrl);
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
        MainPage mainPage = new MainPage(driver);
        mainPage.waitPageToLoad();
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(Constants.BASEURL, actualUrl);
    }

    @After
    public void cleanUp(){
        RegisterApi.deleteUser();
    }

}
