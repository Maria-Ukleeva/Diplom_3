import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class ConstructorSorterTest extends BaseTest {

    private WebDriver driver;

    @Before
    public void openMainPage(){
        driver = super.driver;
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage();
    }
    @Test
    @DisplayName("Переход к разделу «Булки»")
    public void checkSortingByBuns(){
        MainPage mainPage = new MainPage(driver);
        mainPage.sortBuns();
        Assert.assertTrue(mainPage.bunsIsActive());

    }
    @Test
    @DisplayName("Переход к разделу «Соусы»")
    public void checkSortingBySauces(){
        MainPage mainPage = new MainPage(driver);
        mainPage.sortSauces();
        Assert.assertTrue(mainPage.saucesIsActive());

    }
    @Test
    @DisplayName("Переход к разделу «Начинки»")
    public void checkSortingByFillings(){
        MainPage mainPage = new MainPage(driver);
        mainPage.sortFillings();
        Assert.assertTrue(mainPage.fillingsIsActive());
    }

}
