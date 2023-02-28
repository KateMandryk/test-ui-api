import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import utils.ConfigReader;

import static utils.Singleton.closeWebBrowser;
import static utils.Singleton.getWebDriverInstance;

public class BaseTest {
    public Logger log = LogManager.getLogger();
    public ConfigReader reader = new ConfigReader();
    private final String url = reader.getValue("url");

    @BeforeMethod
    public void setUp() {
        log.info("Browser ready...");
        getWebDriverInstance().get(url);
    }

    @AfterTest
    public void close() {
        closeWebBrowser();
    }
}