package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class Hooks {

    private static WebDriver driver;

    public static WebDriver getDriver() {
        return driver;
    }

    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless")
                .addArguments("--lang=en")
                .addArguments("--no-sandbox")
                .addArguments("--disable-download-notification")
                .addArguments("disable-gpu")
                .addArguments("--ignore-certificate-error")
                .addArguments("--always-authorize-plugins")
                .addArguments("--disable-extensions")
                .addArguments("--window-size=1920,1080")
                .addArguments("--disable-popup-blocking");
        driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

    }

    @After
    public void tearDown() {
        driver.quit();
    }

}
