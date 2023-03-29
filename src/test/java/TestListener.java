import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener ;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    public void onTestFailure(ITestResult result) {
        Allure.getLifecycle().addAttachment("Page screenshot", "image/png","png", ((TakesScreenshot) BaseTest.driver).getScreenshotAs(OutputType.BYTES));
        BaseTest.driver.quit();
    }
}