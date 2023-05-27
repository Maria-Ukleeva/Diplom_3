import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private final WebDriver driver;
    //ссылка на регистрацию
    private final By registrationLink = By.xpath(".//a[text() = 'Зарегистрироваться']");
    //поле "Email"
    private final By email = By.xpath(".//label[text() = 'Email']/parent::div/input");
    //поле "Пароль"
    private final By password = By.xpath(".//label[text() = 'Пароль']/parent::div/input");
    //
    private final By loginButton = By.xpath(".//button[text() = 'Войти']");

    public LoginPage(WebDriver webDriver){
        this.driver = webDriver;
    }
    public void clickRegistrationLink(){
        driver.findElement(registrationLink).click();
    }

    public void fillInAllFields(String emailValue, String passwordValue){
        driver.findElement(email).sendKeys(emailValue);
        driver.findElement(password).sendKeys(passwordValue);
    }

    public void clickLoginButton(){
        driver.findElement(loginButton).click();
    }

}
