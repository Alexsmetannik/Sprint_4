package ya.praktikum;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

// класс формы заполнения данных заказчика
public class OrdererPage {
    private WebDriver driver;

    public OrdererPage(WebDriver driver){
        this.driver = driver;
    }

    //локатор заголовка формы
    private By ordererHeader = By.xpath("//*[@id='root']/div/div[2]/div[1]");

    //локатор поля Имя
    private By firstNameField = By.xpath("//*[@id='root']/div/div[2]/div[2]/div[1]/input");

    //локатор поля Фамилия
    private By lastNameField = By.xpath("//*[@id='root']/div/div[2]/div[2]/div[2]/input");

    //локатор поля Адрес
    private By addressField = By.xpath("//*[@id='root']/div/div[2]/div[2]/div[3]/input");

    //локатор поля Метро
    private By metrofield = By.xpath("//*[@id='root']/div/div[2]/div[2]/div[4]/div/div/input");

    //локатор поля Телефон
    private By phonefield = By.xpath("//*[@id='root']/div/div[2]/div[2]/div[5]/input");

    //локатор кнопки Далее
    private By goButton = By.xpath("//*[@id='root']/div/div[2]/div[3]/button");

    // метод ожидания загрузки заголовка
    public void waitForLoadHeaderOrdererPage(WebDriver driver){
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(ordererHeader));
    }

    // метод заполнения имени
    public void setFirstName(String firstName) {
        driver.findElement(firstNameField).sendKeys(firstName);
    }

    // метод заполнения фамилии
    public void setLastName(String lastName) {
        driver.findElement(lastNameField).sendKeys(lastName);
    }

    // метод заполнения адреса
    public void setAddress(String address) {
        driver.findElement(addressField).sendKeys(address);
    }

    // метод заполнения метро
    public void setMetro(String metro) throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(metrofield).click();
        Select dropdown = new Select(driver.findElement(metrofield));
        dropdown.deselectByValue(metro);
        Thread.sleep(2000);
        /*
        Thread.sleep(2000);
        driver.findElement(metrofield).sendKeys(metro);
        Thread.sleep(2000);
        driver.findElement(metrofield).click();
        Thread.sleep(2000);

        */
    }

    // метод заполнения телефона
    public void setPhone(String phone){
        driver.findElement(phonefield).sendKeys(phone);
    }

    // метод нажатия на кнопку Далее
    public void clickOnGoButton() throws InterruptedException {
        Thread.sleep(3000);
        driver.findElement(goButton).click();
    }
}
