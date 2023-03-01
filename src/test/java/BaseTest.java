import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.DataReader;

public class BaseTest {
    public Logger log = LogManager.getLogger();
    public DataReader reader = new DataReader();
    private final String url = reader.getValue("url");
    protected WebDriver driver;


    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        log.info("Browser ready...");
        driver.get(url);
    }

    @AfterMethod
    public void close() {
        driver.quit();
    }
}