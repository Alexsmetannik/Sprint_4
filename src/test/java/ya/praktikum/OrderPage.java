package ya.praktikum;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

// класс формы заполнения данных заказа
public class OrderPage {
    private WebDriver driver;

    public OrderPage(WebDriver driver){
        this.driver = driver;
    }

    //локатор заголовка формы
    private By orderHeader = By.xpath("//*[@id='root']/div/div[2]/div[1]");

    //локатор поля даты
    private By dateField = By.xpath("//*[@id='root']/div/div[2]/div[1]");

    //локатор значения '23.09.2023' поля даты
    private By dateTodayField = By.xpath(".//input[@value='22.08.2023']");


    // метод ожидания загрузки заголовка
    public void waitForLoadHeaderOrderPage(WebDriver driver){
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(orderHeader));
    }

    // метод заполнения даты
    public void setDate() {
        driver.findElement(dateField).click();
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", dateTodayField);
        driver.findElement(dateTodayField).click();
    }
}
