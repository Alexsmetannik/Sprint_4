package ya.praktikum;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import praktikum.pages.ConfirmPage;
import praktikum.pages.MainPage;
import praktikum.pages.OrderPage;
import praktikum.pages.OrdererPage;

import java.io.File;
import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class OrderScooterTestOnAboveOrderButton {
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
        // создаётся объект класса формы подтверждения заказа
        ConfirmPage objConfirmPage = new ConfirmPage(driver);

        String firstName = "Николай";
        String lastName = "Петров";
        String address = "г. Москва, проспект Вернадского, 43с1, кв.48";
        String metro = "Фили";
        String phone = "89200072839";
        String day = "23.08.2023";
        String period = "сутки";
        String colour = "Black";
        String comment = "Предоставьте услугу как можно скорее";
        String expectedMessage = "Заказ оформлен";

        //test Above OrderButton
        objMainPage.clickOnCookiesButton(driver);
        objMainPage.clickOnAboveOrderButton(driver);
        objOrdererPage.waitForLoadHeaderOrdererPage(driver);

        objOrdererPage.setFirstName(firstName);
        objOrdererPage.setLastName(lastName);
        objOrdererPage.setAddress(address);
        objOrdererPage.setMetro(metro);
        objOrdererPage.setPhone(phone);
        objOrdererPage.clickOnGoButton();

        objOrderPage.waitForLoadHeaderOrderPage(driver);
        objOrderPage.setPeriod(period);
        objOrderPage.setCheckBox(colour);
        objOrderPage.setCommentField(comment);
        objOrderPage.setDate(day);
        objOrderPage.clickOrderButton();

        objConfirmPage.waitForLoadHeaderConfirmPage(driver);
        objConfirmPage.clickYesButton();
        objConfirmPage.waitForLoadFinishHeader(driver);
        String getMessage = objConfirmPage.getTextInHeader();

        // проверка, что полученное сообщение совпадает с ожидаемым сообщением
        assertEquals("Полученное сообщение не совпадает с ожидаемым сообщением", getMessage, expectedMessage);

        killDriver();
    }

    @After
    // Закрытие браузера
    public void killDriver() {
        driver.quit();
    }
}
