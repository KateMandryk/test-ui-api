package forms.base;

import org.openqa.selenium.By;

public class Label extends BaseElement{
    private By locator;
    private String name;

    public Label(By locator, String name) {
        super(locator, name);
    }
}