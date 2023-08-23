package praktikum.pages;

import org.openqa.selenium.By;
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
    private static final By orderHeader = By.xpath("//*[@id='root']/div/div[2]/div[1]");

    //локатор поля даты
    private static final By dateField = By.xpath("//*[@id='root']/div/div[2]/div[2]/div[1]/div[1]/div/input");

    //локатор поля срока
    private static final By periodField = By.xpath("//*[@id='root']/div/div[2]/div[2]/div[2]/div/div[1]");

    //локатор чек-бокса черного цвета самоката
    private static final By blackColourCheckBox = By.xpath("//*[@id='black']");

    //локатор чек-бокса серого цвета самоката
    private static final By greyColourCheckBox = By.xpath("//*[@id='grey']");

    //локатор поля комментария
    private static final By commentField = By.xpath("//*[@id='root']/div/div[2]/div[2]/div[4]/input");

    //локатор кнопки Заказать
    private static final By orderButton = By.xpath("//*[@id='root']/div/div[2]/div[3]/button[2]");




    // метод ожидания загрузки заголовка
    public void waitForLoadHeaderOrderPage(WebDriver driver){
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(orderHeader));
    }

    // метод заполнения даты
    public void setDate(String day) throws InterruptedException {
        driver.findElement(dateField).sendKeys(day);
        Thread.sleep(1000);
    }

    // метод заполнения срока
    public void setPeriod(String period) throws InterruptedException {
        driver.findElement(periodField).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@class='Dropdown-option'][text()='" + period + "']")).click();
        Thread.sleep(1000);
    }

    // метод активации чек-бокса черного цвета самоката
    public void setCheckBox(String Colour) throws InterruptedException {
        if (Colour.equals("Black")) {
            driver.findElement(blackColourCheckBox).click();
            Thread.sleep(1000);
        } else  if (Colour.equals("Gray")) {
            driver.findElement(greyColourCheckBox).click();
            Thread.sleep(1000);
        }
    }

    // метод заполнения комментария
    public void setCommentField(String comment) throws InterruptedException {
        driver.findElement(commentField).sendKeys(comment);
        Thread.sleep(1000);
    }

    // метод нажатия на кнопку Заказать
    public void clickOrderButton() throws InterruptedException {
        driver.findElement(orderButton).click();
        Thread.sleep(1000);
    }
}
