package ya.praktikum.other;

import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;

public class ScooterTest {

    WebDriver driver;
    @Before
    public void initDriver() {
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        ChromeDriverService service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File("C:/Users/Alex/projects/WebDriver/bin/chromedriver/chromedriver"))
                .build();

        driver = new ChromeDriver(service);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }
    @Test
    public void invalidOrderNumber() {

        driver.get("https://qa-scooter.praktikum-services.ru/");

        ClickOnShowStatus(driver);
        typeOrderNumber(driver, "123");
        clickOnGo(driver);
        waitForNoStatus(driver);
        killDriver();
    }
    private static void ClickOnShowStatus(WebDriver driver){
        driver.findElement(By.className("Header_Link__1TAG7")).click();
    }

    private static void typeOrderNumber (WebDriver driver, String orderNumber){
        driver.findElement(By.xpath("//input[contains(@class, 'Input_Input__1iN_Z')]")).sendKeys(orderNumber);
    }

    private static void clickOnGo (WebDriver driver){
        driver.findElement(By.cssSelector("[class*= 'Header_Button_']")).click();
    }

    private static void waitForNoStatus (WebDriver driver){
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[alt = 'Not found']")));
    }

    @After
    public void killDriver() {
        driver.quit();
    }
}
