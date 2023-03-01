package forms.app;

import forms.base.BaseForm;
import forms.base.Button;
import forms.base.Label;
import forms.base.TextBox;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginForm extends BaseForm {
    private static final By locator = By.xpath("//div[contains(@class,'main-header') and contains(text(),'Login')]");
    private static final String name = "Login Form";
    private final TextBox txbUserName = new TextBox(driver,By.id("userName"), "TextBox UserName");
    private final TextBox txbPassword = new TextBox(driver,By.id("password"), "TextBox Password");
    private final Button btnLogin = new Button(driver,By.id("login"), "Button Login");
    private final Label lblMessage=new Label(driver,By.className("mb-1"),"Label Message");

    public LoginForm(WebDriver driver) {
        super(driver,locator, name);
    }

    public LoginForm setUserName(String name) {
        txbUserName.typeText(name);
        return this;
    }

    public LoginForm setPassword(String password) {
        txbPassword.typeText(password);
        return this;
    }

    public void clickBtnLogin() {
        btnLogin.scrollToElement();
        btnLogin.click();
    }

    public String getMessage(){
        return lblMessage.getText();
    }
}