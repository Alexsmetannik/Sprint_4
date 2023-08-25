package praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

// класс формы заполнения данных заказа
public class OrderPage {
    final WebDriver driver;

    public OrderPage(WebDriver driver){
        this.driver = driver;
    }

    //локатор заголовка формы
    private static final By orderHeader = By.xpath("//div[text()='Про аренду']");

    //локатор поля даты
    private static final By dateField = By.xpath("//input[@placeholder='* Когда привезти самокат']");

    //локатор поля срока
    private static final By periodField = By.xpath("//div[text()='* Срок аренды']");

    //локатор чек-бокса черного цвета самоката
    private static final By blackColourCheckBox = By.xpath("//*[@id='black']");

    //локатор чек-бокса серого цвета самоката
    private static final By greyColourCheckBox = By.xpath("//*[@id='grey']");

    //локатор поля комментария
    private static final By commentField = By.xpath("//input[@placeholder='Комментарий для курьера']");

    //локатор кнопки Заказать
    private static final By orderButton = By.xpath("//*[@class='Order_Buttons__1xGrp']/button[text()='Заказать']");




    // метод ожидания загрузки заголовка
    public void waitForLoadHeaderOrderPage(WebDriver driver){
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(orderHeader));
    }

    // метод заполнения даты
    public void setDate(String day) throws InterruptedException {
        driver.findElement(dateField).sendKeys(day);
    }

    // метод заполнения срока
    public void setPeriod(String period) throws InterruptedException {
        driver.findElement(periodField).click();
        driver.findElement(By.xpath("//*[@class='Dropdown-option'][text()='" + period + "']")).click();
    }

    // метод активации чек-бокса черного цвета самоката
    public void setCheckBox(String Colour) throws InterruptedException {
        if (Colour.equals("Black")) {
            driver.findElement(blackColourCheckBox).click();
        } else  if (Colour.equals("Gray")) {
            driver.findElement(greyColourCheckBox).click();
        }
    }

    // метод заполнения комментария
    public void setCommentField(String comment) throws InterruptedException {
        driver.findElement(commentField).sendKeys(comment);
    }

    // метод нажатия на кнопку Заказать
    public void clickOrderButton() throws InterruptedException {
        driver.findElement(orderButton).click();
    }

    public void transitionInConfirmPage(String period, String colour, String comment, String day) throws InterruptedException {
        setPeriod(period);
        setCheckBox(colour);
        setCommentField(comment);
        setDate(day);
        clickOrderButton();
    }
}
