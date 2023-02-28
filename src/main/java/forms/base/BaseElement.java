package forms.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ConfigReader;
import static utils.Singleton.getWebDriverInstance;


public abstract class BaseElement {
    private final By locator;
    private final String name;
    private final Logger log = LogManager.getLogger();
    private final ConfigReader reader=new ConfigReader();
    private final Integer timeout=Integer.parseInt(reader.getValue("timeout"));

    public BaseElement(By locator, String name) {
        this.locator = locator;
        this.name = name;
    }

    public WebElement getElement() {
    return getWebDriverInstance().findElement(locator);
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
        WebElement myElement = new WebDriverWait(getWebDriverInstance(), timeout).until(ExpectedConditions.visibilityOfElementLocated(locator));
        return myElement.getText();
    }

    public WebElement scrollToElement(){
        WebElement myElement = new WebDriverWait(getWebDriverInstance(), timeout).until(ExpectedConditions.visibilityOf(getElement()));
        ((JavascriptExecutor) getWebDriverInstance()).executeScript("arguments[0].scrollIntoView();", myElement);
        return myElement;
    }
}