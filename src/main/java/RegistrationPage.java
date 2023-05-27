import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationPage {
    private final WebDriver driver;
    //поле "Имя"
    private final By name = By.xpath(".//label[text() = 'Имя']/parent::div/input");
    //поле "Email"
    private final By email = By.xpath(".//label[text() = 'Email']/parent::div/input");
    //поле "Пароль"
    private final By password = By.xpath(".//label[text() = 'Пароль']/parent::div/input");
    //кнопка "Зарегистрироваться"
    private final By registrationButton = By.xpath(".//button[text() = 'Зарегистрироваться']");
    private final By loginButton = By.xpath(".//a[text() = 'Войти']");
    private final By error = By.className("input__error");

    public RegistrationPage(WebDriver webDriver) {
        this.driver = webDriver;

    }
    public void openRegistrationPage(){
        driver.get("https://stellarburgers.nomoreparties.site/register");
    }
    public void fillInAllFields(String nameValue, String emailValue, String passwordValue){
        driver.findElement(name).sendKeys(nameValue);
        driver.findElement(email).sendKeys(emailValue);
        driver.findElement(password).sendKeys(passwordValue);
    }
    public void clickRegistrationButton(){
        driver.findElement(registrationButton).click();
    }

    public Boolean ifThereIsAnError() {
        return driver.findElement(error).isDisplayed();
    }

    public void goToLogin(){
        driver.findElement(loginButton).click();
    }

}