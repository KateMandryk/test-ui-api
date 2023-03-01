import api.ApplicationApi;
import forms.app.BookStorePage;
import forms.app.LoginForm;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginBookStoreTest extends BaseTest {
    private final String userName = reader.getValue("userName");
    private final String password = reader.getValue("password");
    private final String unregisteredUser = reader.getValue("UnregisteredUser");
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
    }

    @Test
    public void testLoginUnregisteredUser() {
        log.info("[UI] STEP 1 :: Navigate to Book Store");
        BookStorePage bookStore = new BookStorePage(driver);
        Assert.assertTrue(bookStore.isDisplayed(), "Book store page is not opened");
        bookStore.clickBtnLogin();
        log.info("[UI] STEP 2 :: Navigate to Login form");
        LoginForm loginForm = new LoginForm(driver);
        Assert.assertTrue(loginForm.isDisplayed(), "Login form is not opened");
        log.info("[UI] STEP 3 :: Login a non-existent user");
        loginForm.setUserName(unregisteredUser);
        loginForm.setPassword(password);
        loginForm.clickBtnLogin();
        Assert.assertEquals(loginForm.getMessage(), message, "Error message is not displayed on the page");
    }
}