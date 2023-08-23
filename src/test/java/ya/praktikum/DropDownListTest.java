
package ya.praktikum;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import praktikum.pages.MainPage;

import java.io.File;
import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class DropDownListTest {

    private WebDriver driver;

    @Before
    // драйвер для браузера Chrome
    public void initDriver() {
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        ChromeDriverService service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File("C:/Users/Alex/projects/WebDriver/bin/chromedriver/chromedriver"))
                .build();
        driver = new ChromeDriver(service);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @Test
    public void checkTextAfterClickArrow() throws InterruptedException {
        // переход на страницу тестового приложения
        driver.get("https://qa-scooter.praktikum-services.ru/");

        // создаётся объект класса главной страницы
        MainPage objMainPage = new MainPage(driver);

        // создаётся массив с вариантом ожидаемого текста под стрелочкой
        String[] expectedTextInArrows = { "Сутки — 400 рублей. Оплата курьеру — наличными или картой.",
                "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.",
                "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.",
                "Только начиная с завтрашнего дня. Но скоро станем расторопнее.",
                "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.",
                "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.",
                "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.",
                "Да, обязательно. Всем самокатов! И Москве, и Московской области."};

        objMainPage.clickOnCookiesButton(driver);

        //test Text Under Arrow
            for (int i = 0; i < expectedTextInArrows.length; i++) {
                objMainPage.clickOnArrow(i);
                Thread.sleep(1000);
                String textInArrow = objMainPage.getTextUnderArrow(i);
                Thread.sleep(1000);
                assertEquals("Полученное описание не совпадает с ожидаемым описанием", textInArrow, expectedTextInArrows[i]);
            }

        killDriver();
    }

    @After
    // Закрытие браузера
    public void killDriver() {
        driver.quit();
    }
}