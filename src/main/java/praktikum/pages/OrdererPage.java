package praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

// класс формы заполнения данных заказчика
public class OrdererPage {
    private WebDriver driver;

    public OrdererPage(WebDriver driver){
        this.driver = driver;
    }

    //локатор заголовка формы
    private static final By ordererHeader = By.xpath("//*[@id='root']/div/div[2]/div[1]");

    //локатор поля Имя
    private static final By firstNameField = By.xpath("//*[@id='root']/div/div[2]/div[2]/div[1]/input");

    //локатор поля Фамилия
    private static final By lastNameField = By.xpath("//*[@id='root']/div/div[2]/div[2]/div[2]/input");

    //локатор поля Адрес
    private static final By addressField = By.xpath("//*[@id='root']/div/div[2]/div[2]/div[3]/input");

    //локатор поля Метро
    private static final By metroField = By.xpath("//*[@id='root']/div/div[2]/div[2]/div[4]/div/div/input");

    //локатор поля Телефон
    private static final By phoneField = By.xpath("//*[@id='root']/div/div[2]/div[2]/div[5]/input");

    //локатор кнопки Далее
    private static final By goButton = By.xpath("//*[@id='root']/div/div[2]/div[3]/button");

    // метод ожидания загрузки заголовка
    public void waitForLoadHeaderOrdererPage(WebDriver driver){
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(ordererHeader));
    }

    // метод заполнения имени
    public void setFirstName(String firstName) throws InterruptedException {
        driver.findElement(firstNameField).sendKeys(firstName);
        Thread.sleep(1000);
    }

    // метод заполнения фамилии
    public void setLastName(String lastName) throws InterruptedException {
        driver.findElement(lastNameField).sendKeys(lastName);
        Thread.sleep(1000);
    }

    // метод заполнения адреса
    public void setAddress(String address) throws InterruptedException {
        driver.findElement(addressField).sendKeys(address);
        Thread.sleep(1000);
    }

    // метод заполнения метро
    public void setMetro(String metro) throws InterruptedException {
        driver.findElement(metroField).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@class='Order_Text__2broi'][text()='" + metro + "']")).click();
        Thread.sleep(1000);
    }

    // метод заполнения телефона
    public void setPhone(String phone) throws InterruptedException {
        driver.findElement(phoneField).sendKeys(phone);
        Thread.sleep(1000);
    }

    // метод нажатия на кнопку Далее
    public void clickOnGoButton() throws InterruptedException {
        driver.findElement(goButton).click();
        Thread.sleep(1000);
    }
}
