import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;


public class RegistrationTest extends BaseTest {
    private WebDriver driver;
    public String username;
    public String email;
    public String password;
    public User user;


    @Before
    public void createNewDriverAndUserAndOpenManePage() {
        user = RegisterApi.createUser();
        username = user.getName();
        email = user.getEmail();
        password = user.getPassword();
        driver = super.driver;
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
        registrationPage.fillInAllFields(username, email, password);
        registrationPage.clickRegistrationButton();
        loginPage.waitPageToLoad();
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(Constants.LOGIN_URL, actualUrl);
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
        Assert.assertEquals(Constants.REGISTRATION_URL, actualUrl);
        Assert.assertTrue(registrationPage.ifThereIsAnIncorrectPasswordError());
    }

    @After
    public void cleanUp(){
        try {
        LoginApi.loginUser(user);
        String token = LoginApi.getAccessToken();
        RegisterApi.deleteUser(token);
        } catch (NullPointerException nullPointerException) {
            System.out.println("Юзер не удален");
        }
    }
}




