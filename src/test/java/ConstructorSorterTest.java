import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ConstructorSorterTest {
    private WebDriver driver;
    @Before
    public void createNewDriverAndOpenMainPage() {
        driver = new ChromeDriver();
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage();
    }

    @Test
    @DisplayName("Переход к разделу «Булки»")
    public void sortBuns(){
        MainPage mainPage = new MainPage(driver);
        mainPage.sortBuns();
        Assert.assertTrue(mainPage.bunsIsActive());

    }
    @Test
    @DisplayName("Переход к разделу «Соусы»")
    public void sortSauces(){
        MainPage mainPage = new MainPage(driver);
        mainPage.sortSauces();
        Assert.assertTrue(mainPage.saucesIsActive());

    }
    @Test
    @DisplayName("Переход к разделу «Начинки»")
    public void sortFillings(){
        MainPage mainPage = new MainPage(driver);
        mainPage.sortFillings();
        Assert.assertTrue(mainPage.fillingsIsActive());
    }

    @After
    public void cleanUp(){
        driver.quit();
    }
}
