import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;


public class ConstructorTest extends BaseTest {
    private WebDriver driver;
    public String email;
    public String password;
    public User user;


    @Before
    public void loginAndOpenProfile() {
        driver = super.driver;
        user = RegisterApi.createUser();
        RegisterApi.registerUser(user);
        email = user.getEmail();
        password = user.getPassword();
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
    public void checkClickOnGoToConstructor(){
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.goToConstructor();
        MainPage mainPage = new MainPage(driver);
        mainPage.waitPageToLoad();
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(Constants.BASEURL, actualUrl);
        Assert.assertTrue(mainPage.checkIsConstructorDisplayed());
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор по клику на логотип Stellar Burgers")
    public void checkClickOnLogo(){
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.clickLogo();
        MainPage mainPage = new MainPage(driver);
        mainPage.waitPageToLoad();
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(Constants.BASEURL, actualUrl);
        Assert.assertTrue(mainPage.checkIsConstructorDisplayed());
    }
    @After
    public void cleanUp(){
        RegisterApi.deleteUser();
    }

}
