package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class Singleton {
    private static final Logger log = LogManager.getLogger();
    private static WebDriver driver;

    private Singleton() {
    }

    public static WebDriver getWebDriverInstance() {
        if (driver == null) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
        return driver;
    }

    public static void closeWebBrowser() {
        if (driver != null) {
            log.info("Closing browser");
            driver.quit();
        }
    }
}