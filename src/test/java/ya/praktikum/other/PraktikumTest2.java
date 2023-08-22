package ya.praktikum.other;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;


// Класс страницы авторизации
class LoginPageMesto {

    private WebDriver driver;
    private By emailField = By.id("email");
    private By passwordField = By.id("password");
    private By signInButton = By.className("auth-form__button");

    public LoginPageMesto(WebDriver driver){
        this.driver = driver;
    }

    public void setUsername(String username) throws InterruptedException {
        driver.findElement(emailField).sendKeys(username);
        Thread.sleep(10_000);
    }
    public void setPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }
    public void clickSignInButton() {
        driver.findElement(signInButton).click();
    }
    public void login(String username, String password) throws InterruptedException {
        setUsername(username);
        setPassword(password);
        clickSignInButton();
    }
}

// Класс главной страницы
class HomePageMesto {

    private WebDriver driver;

    private By profileTitle = By.className("profile__title");
    // создай локатор для кнопки редактирования профиля
    private By editProfileButton = By.className("profile__edit-button");

    // создай локатор для поля «Занятие» в профиле пользователя
    private By activity = By.className("profile__description");

    public HomePageMesto(WebDriver driver) {
        this.driver = driver;
    }

    // метод ожидания прогрузки данных профиля
    public void waitForLoadProfileData() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver -> (driver.findElement(profileTitle).getText() != null
                && !driver.findElement(profileTitle).getText().isEmpty()
        ));
    }
    // метод для нажатия на кнопку редактирования профиля
    public void clickEditProfileButton() {
        driver.findElement(editProfileButton).click();
    }

    public void waitForChangedActivity(String changed) {
        // здесь нужно дождаться, чтобы текст в элементе «Занятие» стал равен значению из параметра
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.textToBePresentInElementLocated(activity, changed));
    }
}

// Класс cтраницы редактирования профиля
class ProfilePageMesto {

    private WebDriver driver;
    // создай локатор для поля «Занятие» в профиле пользователя
    private By activity =  By.id("owner-description");
    // создай локатор для кнопки «Сохранить» в профиле пользователя
    private By save =  By.xpath("//*[@id = 'root']/div/div[1]/div/form/button[2]");

    public ProfilePageMesto (WebDriver driver){
        this.driver = driver;
    }

    // метод для проверки открытости поля «Занятие», удаления текста из неё и ввода нового значения из параметра
    public void editOwnerDescription(String newActivity) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(activity));
        driver.findElement(activity).clear();
        driver.findElement(activity).sendKeys(newActivity);
    }

    // метод для нажатия на кнопку сохранения профиля
    public void clickSaveButton() {
        driver.findElement(save).click();
    }
}



public class PraktikumTest2 {

    private WebDriver driver;

    @Before
    public void setUp() {
        ChromeDriverService service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File("C:/Users/Alex/projects/WebDriver/bin/chromedriver/chromedriver"))
                .build();

        driver = new ChromeDriver(service);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void genericCheck() throws Exception {
        driver.get("https://qa-mesto.praktikum-services.ru/");

        // создай объект класса страницы авторизации
        LoginPageMesto objLoginPage = new LoginPageMesto(driver);
        // выполни авторизацию
        objLoginPage.login("Galkin_24@yandex.com",
                "test1234");

        // создай объект класса главной страницы приложения
        HomePageMesto objHomePage = new HomePageMesto(driver);
        // дождись загрузки данных профиля на главной странице
        objHomePage.waitForLoadProfileData();

        // кликни на кнопку редактирования профиля
        objHomePage.clickEditProfileButton();

        // создай объект класса для поп-апа редактирования профиля
        ProfilePageMesto objProfilePage = new ProfilePageMesto(driver);

        // это переменная со значением, которое надо ввести в поле «Занятие»
        String newActivity = "Тестировщик";
        // в одном шаге проверь, что поле «Занятие» доступно для редактирования, и введи в него новое значение
        objProfilePage.editOwnerDescription(newActivity);
        // сохрани изменения в профиле
        objProfilePage.clickSaveButton();

        // проверь, что поле «Занятие» на основной странице поменяло значение на новое
        objHomePage.waitForChangedActivity(newActivity);
    }
}
