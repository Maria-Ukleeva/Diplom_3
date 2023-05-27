import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HeaderPage {

    private final WebDriver driver;
    private final By logo = By.xpath(".//div[contains(@class, 'AppHeader_header__logo')]");
    private final By constructorButton = By.xpath(".//p[text() = 'Конструктор']");
    //кнопка "Личный кабинет"
    private final By cabinet = By.xpath(".//p[text() = 'Личный Кабинет']");

    public HeaderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickLogo(){
        WebElement logotype =  driver.findElement(logo);
        new WebDriverWait(driver, 10).until(driver -> logotype.isEnabled());
        driver.findElement(logo).click();
    }

    public void goToConstructor(){
        driver.findElement(constructorButton).click();
    }
    public void openProfile(){
        driver.findElement(cabinet).click();
    }

}
