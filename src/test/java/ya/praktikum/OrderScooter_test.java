package ya.praktikum;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;

import java.io.File;
import java.time.Duration;

public class OrderScooter_test {
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
    public void checkOrderScooter() throws InterruptedException {
        // переход на страницу тестового приложения
        driver.get("https://qa-scooter.praktikum-services.ru/");

        // создаётся объект класса главной страницы
        MainPage objMainPage = new MainPage(driver);
        // создаётся объект класса формы заполнения данных заказчика
        OrdererPage objOrdererPage = new OrdererPage(driver);
        // создаётся объект класса формы заполнения данных заказа
        OrderPage objOrderPage = new OrderPage(driver);

        String firstName = "Николай";
        String lastName = "Петров";
        String address = "г. Москва, проспект Вернадского, 43с1, кв.48";
        String metro = "Фили";
        String phone = "89200072839";

        objMainPage.clickOnCookiesButton(driver);
        objMainPage.clickOnAboveOrderButton(driver);
        objOrdererPage.waitForLoadHeaderOrdererPage(driver);
      //  objMainPage.clickOnBelowOrderButton(driver);

        objOrdererPage.setFirstName(firstName);
        objOrdererPage.setLastName(lastName);
        objOrdererPage.setAddress(address);
        objOrdererPage.setMetro(metro);
        objOrdererPage.setPhone(phone);
        objOrdererPage.clickOnGoButton();

        objOrderPage.waitForLoadHeaderOrderPage(driver);

        killDriver();
    }

    @After
    // Закрытие браузера
    public void killDriver() {
        driver.quit();
    }
}