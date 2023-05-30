import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


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
    //Кнопка "Оформить заказ"
    private final By orderButton = By.xpath(".//button[text() = 'Оформить заказ']");
    //Заголовок
    private final By constructorHeader = By.xpath(".//h1[text() = 'Соберите бургер']");


    public MainPage(WebDriver webDriver){
        this.driver = webDriver;
    }

    public void openMainPage(){
        driver.get(Constants.BASEURL);
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

    public void waitPageToLoad(){
        new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(orderButton));
    }

    public Boolean checkIsConstructorDisplayed(){
        return driver.findElement(constructorHeader).isDisplayed();
    }

}
