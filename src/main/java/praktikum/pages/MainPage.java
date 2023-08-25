package praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

// класс главной страницы
public class MainPage {

    final WebDriver driver;

    public MainPage(WebDriver driver){
        this.driver = driver;
    }

    //локатор кнопки куки
    private static final By cookiesButton = By.className("App_CookieButton__3cvqF");

    //локатор верхней кнопки Заказать
    private static final By aboveOrderButton = By.xpath("//*[@class='Button_Button__ra12g']");

    //локатор нижней кнопки Заказать
    private static final By belowOrderButton = By.xpath("//*[@class='Home_FinishButton__1_cWm']/button");

    //создаётся массив с локатарами стрелочек
    String[] arrows = {"//*[@id='accordion__heading-0']",
            "//*[@id='accordion__heading-1']",
            "//*[@id='accordion__heading-2']",
            "//*[@id='accordion__heading-3']",
            "//*[@id='accordion__heading-4']",
            "//*[@id='accordion__heading-5']",
            "//*[@id='accordion__heading-6']",
            "//*[@id='accordion__heading-7']"};

    //создаётся массив с локатарами текста под стрелочками
    String[] textArrows = {"//*[@id='accordion__panel-0']/p",
            "//*[@id='accordion__panel-1']/p",
            "//*[@id='accordion__panel-2']/p",
            "//*[@id='accordion__panel-3']/p",
            "//*[@id='accordion__panel-4']/p",
            "//*[@id='accordion__panel-5']/p",
            "//*[@id='accordion__panel-6']/p",
            "//*[@id='accordion__panel-7']/p"};



    // Метод клика по кнопке куки
    public void clickOnCookiesButton(WebDriver driver) throws InterruptedException {
        driver.findElement(cookiesButton).click();
    }

    // Метод клика по стрелочке
    public void clickOnArrow(int i) {
            driver.findElement(By.xpath(arrows[i])).click();
    }

    // Метод изъятия текста под стрелочкой
    public String getTextUnderArrow(int i) {
            return driver.findElement(By.xpath(textArrows[i])).getText();
    }

    // Метод клика по верхней кнопке Заказать
    public void clickOnAboveOrderButton(WebDriver driver) throws InterruptedException {
        driver.findElement(aboveOrderButton).click();
    }

    // Метод клика по нижней кнопке Заказать
    public void clickOnBelowOrderButton(WebDriver driver) throws InterruptedException {
        WebElement element = driver.findElement(belowOrderButton);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        driver.findElement(belowOrderButton).click();
    }
}
