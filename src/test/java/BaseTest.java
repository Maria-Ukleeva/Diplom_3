import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class BaseTest {
    protected WebDriver driver;


    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver",
                Constants.YANDEX);
        driver = new ChromeDriver();
    }

    @After
    public void shutDown() {
        driver.quit();
    }
}
