package forms.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TextBox extends BaseElement {
    private By locator;
    private String name;

    public TextBox(WebDriver driver,By locator, String name) {
        super(driver,locator, name);
    }
}