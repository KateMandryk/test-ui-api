import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import utils.ConfigReader;

public class BaseTest {
    public Logger log = LogManager.getLogger();
    public ConfigReader reader = new ConfigReader();
    private final String url = reader.getValue("url");
    protected WebDriver driver;


    @BeforeTest
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        log.info("Browser ready...");
        driver.get(url);
    }

    @AfterTest
    public void close() {
        driver.quit();
    }
}