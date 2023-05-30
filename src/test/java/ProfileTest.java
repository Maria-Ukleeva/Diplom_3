import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;



public class ProfileTest extends  BaseTest{
    private WebDriver driver;
    public String email;
    public String password;
    public User user;


    @Before
    public void openMainPageAndLogin() {
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
    }

    @Test
    @DisplayName("Переход в личный кабинет")
    public void checkGoToProfile() {
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.openProfile();
        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.waitPageToLoad();
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(Constants.PROFILE_URL, actualUrl);
    }
    @After
    public void cleanUp(){
        RegisterApi.deleteUser();
    }

    }

