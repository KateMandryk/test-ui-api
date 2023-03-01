package forms.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Button extends BaseElement {
    private By locator;
    private String name;

    public Button(WebDriver driver,By locator, String name) {
        super(driver,locator, name);
    }
}