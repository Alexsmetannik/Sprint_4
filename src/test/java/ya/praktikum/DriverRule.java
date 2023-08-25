package ya.praktikum;

import org.junit.rules.ExternalResource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;

import java.io.File;
import java.time.Duration;

// драйвер для браузера Chrome
public class DriverRule extends ExternalResource {
    WebDriver driver;

    @Override
    protected void before() throws Throwable {
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        ChromeDriverService service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File("C:/Users/Alex/projects/WebDriver/bin/chromedriver/chromedriver"))
                .build();
        driver = new ChromeDriver(service);

        //тайм-аут 10
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Override
    protected void after(){
        driver.quit();
    }

    public WebDriver getDriver() {
        return driver;
    }
}
