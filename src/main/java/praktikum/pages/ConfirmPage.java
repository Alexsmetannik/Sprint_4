package praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ConfirmPage {
    final WebDriver driver;

    public ConfirmPage(WebDriver driver){
        this.driver = driver;
    }

    //локатор заголовка формы
    private static final By confirmHeader = By.xpath("//div[text()='Хотите оформить заказ?']");

    //локатор кнопки Да
    private static final By yesButton = By.xpath("//button[text()='Да']");

    //локатор заголовка финального сообщения
    private static final By finishHeader = By.xpath("//div[text()='Заказ оформлен']");


    // метод ожидания загрузки заголовка
    public void waitForLoadHeaderConfirmPage(WebDriver driver){
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(confirmHeader));
    }

    // метод нажатия на кнопку Да
    public void clickYesButton() throws InterruptedException {
        driver.findElement(yesButton).click();
    }

    // метод ожидания загрузки заголовка финального сообщения
    public void waitForLoadFinishHeader(WebDriver driver){
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(finishHeader));
    }

    // метод для получения текста элемента в заголовке
    public String getTextInHeader() {
        return driver.findElement(finishHeader).getText();
    }
}
