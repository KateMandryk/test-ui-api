package forms.app;

import forms.base.BaseForm;
import forms.base.Button;
import forms.base.TextBox;
import org.openqa.selenium.By;

public class LoginForm extends BaseForm {
    private static final By locator = By.xpath("//div[contains(@class,'main-header') and contains(text(),'Login')]");
    private static final String name = "Login Form";
    private final TextBox txbUserName = new TextBox(By.id("userName"), "TextBox UserName");
    private final TextBox txbPassword = new TextBox(By.id("password"), "TextBox Password");
    private final Button btnLogin = new Button(By.id("login"), "Button Login");

    public LoginForm() {
        super(locator, name);
    }

    public void typeUserName(String name) {
        txbUserName.typeText(name);
    }

    public void typePassword(String password) {
        txbPassword.typeText(password);
    }

    public void clickBtnLogin() {
        btnLogin.scrollToElement();
        btnLogin.click();
    }
}
