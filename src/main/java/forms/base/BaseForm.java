package forms.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public abstract class BaseForm {
    protected WebDriver driver;
    private final By locator;
    private final String name;

    protected BaseForm(WebDriver driver,By locator, String name) {
        this.driver=driver;
        this.locator = locator;
        this.name = name;
    }

    public boolean isDisplayed() {
        WebElement element = driver.findElement(locator);
        return element.isDisplayed();
    }
}