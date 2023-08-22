package ya.praktikum;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class OrderPage {
    private WebDriver driver;

    public OrderPage(WebDriver driver){
        this.driver = driver;
    }

    //локатор заголовка формы
    private By orderHeader = By.xpath("//*[@id='root']/div/div[2]/div[1]");

    // метод ожидания загрузки заголовка
    public void waitForLoadHeader(WebDriver driver){
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(orderHeader));
    }
}
