import api.ApplicationApi;
import forms.app.BookStorePage;
import forms.app.LoginForm;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginBookStoreTest extends BaseTest {
    private final String userName = credentials.getLogin();
    private final String password = credentials.getPassword();
    private final String userId = "userID";
    private final Integer timeout = Integer.parseInt(reader.getValue("timeout"));
    private final String message = reader.getValue("message");


    @Test
    public void testLoginBookStore() {
        log.info("[UI] STEP 1 :: Navigate to Book Store");
        BookStorePage bookStore = new BookStorePage(driver);
        Assert.assertTrue(bookStore.isDisplayed(), "Book store page is not opened");
        bookStore.clickBtnLogin();
        log.info("[API] STEP 2 :: Create a new user");
        ApplicationApi applicationApi = new ApplicationApi();
        applicationApi.createUser(userName, password);
        log.info("[UI] STEP 3 :: Navigate to Login form");
        LoginForm loginForm = new LoginForm(driver);
        Assert.assertTrue(loginForm.isDisplayed(), "Login form is not opened");
        log.info("[UI] STEP 4 :: Log in as a user " + userName);
        loginForm.setUserName(userName);
        loginForm.setPassword(password);
        loginForm.clickBtnLogin();
        Assert.assertEquals(bookStore.getUserName(), userName, "Usernames are different");
        log.info("[UI] STEP 5 :: Delete user " + userName);
        bookStore.clickBtnMenuLogin();
        loginForm.clickBtnProfile();
        bookStore.clickBtnDeleteAccount();
        bookStore.clickBtnOk();
        Alert alert = new WebDriverWait(driver, timeout).until(ExpectedConditions.alertIsPresent());
        alert.accept();
        log.info("[UI] STEP 6 ::Try to log in as a user " + userName + " after account deletion");
        loginForm.setUserName(userName);
        loginForm.setPassword(password);
        loginForm.clickBtnLogin();
        Assert.assertEquals(loginForm.getMsgLogin(), message, "Message is not displayed");
    }
}