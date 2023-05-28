import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class MainPage {
    private final WebDriver driver;

    // кнопка "Войти в аккаунт"
    private final By loginButton = By.xpath(".//button[text() = 'Войти в аккаунт']");
    //Переход к разделу "Булки"
    private final By buns = By.xpath(".//span[text() = 'Булки']/parent::div");
    //Переход к разделу "Соусы"
    private final By sauces = By.xpath(".//span[text() = 'Соусы']/parent::div");
    //Переход к разделу "Начинки"
    private final By fillings = By.xpath(".//span[text() = 'Начинки']/parent::div");

    public MainPage(WebDriver webDriver){
        this.driver = webDriver;
    }

    public void openMainPage(){
        driver.get("https://stellarburgers.nomoreparties.site/");
    }

    public void clickLoginButton(){
        driver.findElement(loginButton).click();
    }

    public void sortBuns(){
        if(!bunsIsActive()) {
            driver.findElement(buns).click();
        }
    }

    public Boolean bunsIsActive(){
        WebElement bunsTab = driver.findElement(buns);
        String className = bunsTab.getAttribute("class");
        return className.contains("current");
    }

    public void sortSauces(){
        if(!saucesIsActive()) {
            driver.findElement(sauces).click();
        }
    }

    public Boolean saucesIsActive(){
        WebElement saucesTab = driver.findElement(sauces);
        String className = saucesTab.getAttribute("class");
        return className.contains("current");
    }

    public void sortFillings(){
        if(!fillingsIsActive()) {
            driver.findElement(fillings).click();
        }
    }

    public Boolean fillingsIsActive(){
        WebElement fillingsTab = driver.findElement(fillings);
        String className = fillingsTab.getAttribute("class");
        return className.contains("current");
    }

}
