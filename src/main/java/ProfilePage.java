import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProfilePage {
    private final WebDriver driver;
    private final By profileHeader = By.xpath(".//a[text() = 'Профиль']");

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitPageToLoad(){
        new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(profileHeader));
    }
}
