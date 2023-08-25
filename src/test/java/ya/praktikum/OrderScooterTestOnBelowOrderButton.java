package ya.praktikum;

import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import praktikum.pages.ConfirmPage;
import praktikum.pages.MainPage;
import praktikum.pages.OrderPage;
import praktikum.pages.OrdererPage;

import static org.junit.Assert.assertEquals;

public class OrderScooterTestOnBelowOrderButton {

    @Rule
    public DriverRule driverRule = new DriverRule();

    @Test
    public void checkOrderScooter() throws InterruptedException {

        WebDriver driver = driverRule.getDriver();
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



        //test Below OrderButton
        objMainPage.clickOnCookiesButton(driver);
        objMainPage.clickOnBelowOrderButton(driver);
        objOrdererPage.waitForLoadHeaderOrdererPage(driver);
        objOrdererPage.transitionInOrderPage(firstName, lastName, address, metro, phone);
        objOrderPage.waitForLoadHeaderOrderPage(driver);
        objOrderPage.transitionInConfirmPage(period, colour, comment, day);
        objConfirmPage.waitForLoadHeaderConfirmPage(driver);
        objConfirmPage.clickYesButton();
        objConfirmPage.waitForLoadFinishHeader(driver);

        String getMessage = objConfirmPage.getTextInHeader();

        // проверка, что полученное сообщение совпадает с ожидаемым сообщением
        assertEquals("Полученное сообщение не совпадает с ожидаемым сообщением", expectedMessage, getMessage);
    }
}