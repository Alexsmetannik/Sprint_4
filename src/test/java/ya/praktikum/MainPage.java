package ya.praktikum;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    private WebDriver driver;

    public MainPage(WebDriver driver){
        this.driver = driver;
    }

    //локатор кнопки куки
    private By cookiesButton = By.className("App_CookieButton__3cvqF");

    //локатор верхней кнопки Заказать
    private By aboveOrderButton = By.xpath("//*[@id='root']/div/div/div[1]/div[2]/button[1]");

    //локатор нижней кнопки Заказать
    private By belowOrderButton = By.xpath("//*[@id='root']/div/div/div[4]/div[2]/div[5]/button");

    // создаётся массив с вариантом ожидаемого текста под стрелочкой
    String[] expectedTextInArrows = { "Сутки — 400 рублей. Оплата курьеру — наличными или картой.",
            "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.",
            "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.",
            "Только начиная с завтрашнего дня. Но скоро станем расторопнее.",
            "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.",
            "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.",
            "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.",
            "Да, обязательно. Всем самокатов! И Москве, и Московской области."};

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
    public void clickOnCookiesButton(WebDriver driver) {
        driver.findElement(cookiesButton).click();
    }

    // Метод клика по стрелочке и проверки текста под стрелочкой
    public void checkingTextUnderArrow(WebDriver driver) {
        for (int i = 0; i < arrows.length; i++) {
            driver.findElement(By.xpath(arrows[i])).click();
            for (int j = 0; j < expectedTextInArrows.length; j++) {
                if (i == j) {
                    String expectedTextInArrow = expectedTextInArrows[j];
                    for (int k = 0; k < textArrows.length; k++) {
                        if (j == k) {
                            By textArrow = By.xpath(textArrows[k]);
                            new WebDriverWait(driver, Duration.ofSeconds(10)).
                                    until(ExpectedConditions.textToBePresentInElementLocated(textArrow, expectedTextInArrow));
                        }
                    }
                }
            }
        }
    }

    // Метод клика по верхней кнопке Заказать
    public void clickOnAboveOrderButton(WebDriver driver) {
        driver.findElement(aboveOrderButton).click();
    }

    // Метод клика по нижней кнопке Заказать
    public void clickOnBelowOrderButton(WebDriver driver) {
        driver.findElement(belowOrderButton).click();
    }
}
