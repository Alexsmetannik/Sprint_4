package praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

// класс формы заполнения данных заказчика
public class OrdererPage {
    final WebDriver driver;

    public OrdererPage(WebDriver driver){
        this.driver = driver;
    }

    //локатор заголовка формы
    private static final By ordererHeader = By.xpath("//div[text()='Для кого самокат']");

    //локатор поля Имя
    private static final By firstNameField = By.xpath("//input[@placeholder='* Имя']");

    //локатор поля Фамилия
    private static final By lastNameField = By.xpath("//input[@placeholder='* Фамилия']");

    //локатор поля Адрес
    private static final By addressField = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");

    //локатор поля Метро
    private static final By metroField = By.xpath("//input[@placeholder='* Станция метро']");

    //локатор поля Телефон
    private static final By phoneField = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");

    //локатор кнопки Далее
    private static final By goButton = By.xpath("//button[text()='Далее']");

    // метод ожидания загрузки заголовка
    public void waitForLoadHeaderOrdererPage(WebDriver driver){
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(ordererHeader));
    }

    // метод заполнения имени
    public void setFirstName(String firstName) throws InterruptedException {
        driver.findElement(firstNameField).sendKeys(firstName);
    }

    // метод заполнения фамилии
    public void setLastName(String lastName) throws InterruptedException {
        driver.findElement(lastNameField).sendKeys(lastName);
    }

    // метод заполнения адреса
    public void setAddress(String address) throws InterruptedException {
        driver.findElement(addressField).sendKeys(address);
    }

    // метод заполнения метро
    public void setMetro(String metro) throws InterruptedException {
        driver.findElement(metroField).click();
        driver.findElement(By.xpath("//*[@class='Order_Text__2broi'][text()='" + metro + "']")).click();
    }

    // метод заполнения телефона
    public void setPhone(String phone) throws InterruptedException {
        driver.findElement(phoneField).sendKeys(phone);
    }

    // метод нажатия на кнопку Далее
    public void clickOnGoButton() throws InterruptedException {
        driver.findElement(goButton).click();
    }

    public void transitionInOrderPage(String firstName, String lastName, String address, String metro, String phone) throws InterruptedException {
        setFirstName(firstName);
        setLastName(lastName);
        setAddress(address);
        setMetro(metro);
        setPhone(phone);
        clickOnGoButton();
    }
}
