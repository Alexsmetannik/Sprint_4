package ya.praktikum;

import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import praktikum.pages.ConfirmPage;
import praktikum.pages.MainPage;
import praktikum.pages.OrderPage;
import praktikum.pages.OrdererPage;

import static org.junit.Assert.assertEquals;

public class OrderScooterTestOnAboveOrderButton {

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

        String firstName = "Сергей";
        String lastName = "Иванов";
        String address = "г. Москва, ул. Ленина, д.13, кв.3";
        String metro = "Строгино";
        String phone = "89003234590";
        String day = "30.08.2023";
        String period = "сутки";
        String colour = "Gray";
        String comment = "Позвоните перед готовностью";
        String expectedMessage = "Заказ оформлен";

        //test Above OrderButton
        objMainPage.clickOnCookiesButton(driver);
        objMainPage.clickOnAboveOrderButton(driver);
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
