import api.ApplicationApi;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.DataReader;
import utils.RandomCredentials;

public abstract class BaseTest {
    public Logger log = LogManager.getLogger();
    public DataReader reader = new DataReader();
    private final String url = reader.getValue("url");
    public RandomCredentials credentials = new RandomCredentials();
    public String userName = credentials.getLogin();
    public String password = credentials. getPassword();
    protected static WebDriver driver;

    @BeforeMethod(onlyForGroups = {"login test"})
    public void createUser() {
        log.info("[API] Precondition :: Create a new user");
        ApplicationApi applicationApi = new ApplicationApi();
        applicationApi.createUser(userName, password);
    }

    @BeforeMethod
    public void setUp() {
        WebDriverManager.edgedriver().setup();
        driver=new EdgeDriver();
        log.info("Browser ready...");
        driver.get(url);
    }

    @AfterMethod
    public void tearDown() {
       driver.quit();
    }
}