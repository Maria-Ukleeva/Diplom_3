import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotPasswordPage {
    private final WebDriver driver;
    //Кнопка "Войти"
    private final By loginButton = By.xpath(".//a[text() = 'Войти']");

    public ForgotPasswordPage(WebDriver webDriver){
        this.driver = webDriver;
    }

    public void openForgotPasswordPage(){
        driver.get(Constants.FORGOT_PASSWORD);
    }
    public void goToLogin(){
        driver.findElement(loginButton).click();
    }
}
