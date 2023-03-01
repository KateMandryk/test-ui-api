package forms.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ConfigReader;


public abstract class BaseElement {
    protected WebDriver driver;
    private final By locator;
    private final String name;
    private final Logger log = LogManager.getLogger();
    private final ConfigReader reader=new ConfigReader();
    private final Integer timeout=Integer.parseInt(reader.getValue("timeout"));

    public BaseElement(WebDriver driver,By locator, String name) {
        this.driver=driver;
        this.locator = locator;
        this.name = name;
    }

    public WebElement getElement() {
    return driver.findElement(locator);
    }

    public void click() {
        log.info(name+ ":: Clicking");
        getElement().click();
    }

    public void typeText(String text) {
        log.info(name+ ":: Typing "+ text);
        getElement().sendKeys(text);
    }

    public String getText() {
        log.info(name + ":: Getting text from element");
        WebElement myElement = new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOfElementLocated(locator));
        return myElement.getText();
    }

    public void scrollToElement(){
        WebElement myElement = new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOf(getElement()));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", myElement);
    }
}