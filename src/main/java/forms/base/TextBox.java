package forms.base;

import org.openqa.selenium.By;

public class TextBox extends BaseElement {
    private By locator;
    private String name;

    public TextBox(By locator, String name) {
        super(locator, name);
    }
}