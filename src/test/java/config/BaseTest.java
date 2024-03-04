package config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import pages.BasePage;

import java.time.Duration;

public class BaseTest {
    public static WebDriver driver;

    public static WebDriver getDriver() {
        return driver;
    }

    @BeforeMethod
    @Parameters("browser")
    public void setUp(@Optional("firefox") String browser) {

        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--lang=en");
            // options.addArguments("--headless");
            driver = new ChromeDriver(options);
        } else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();
            options.addPreference("intl.accept_languages", "en");
            // options.addArguments("-headless");
            driver = new FirefoxDriver(options);
        } else if (browser.equalsIgnoreCase("safari")) {
            SafariOptions options = new SafariOptions();
            options.setCapability("language", "en");
            driver = new SafariDriver(options);
        }
        else {throw new IllegalArgumentException("Invalid browser "+browser); }
        WebDriver driver = getDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofMillis(20000) );
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(20000) );
        BasePage.setDriver(driver);
    }
    @AfterMethod
    public void tearDown(){
        WebDriver driver = getDriver();
        if (driver != null){
            driver.quit();

        }
    }
}

