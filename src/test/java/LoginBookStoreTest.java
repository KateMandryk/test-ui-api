import api.ApplicationApi;
import forms.app.BookStorePage;
import forms.app.LoginForm;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginBookStoreTest extends BaseTest {
    private final String userName = reader.getValue("userName");
    private final String password = reader.getValue("password");

    @Test
    public void testLogin() {
        log.info("[UI] STEP 1 :: Navigate to Book Store");
        BookStorePage bookStore = new BookStorePage(driver);
        Assert.assertTrue(bookStore.isDisplayed(), "Book store page is not opened");
        bookStore.clickBtnLogin();
        log.info("[API] STEP 2 :: Create a new user");
        ApplicationApi applicationApi = new ApplicationApi();
        applicationApi.authUser(userName, password);
        Assert.assertEquals(applicationApi.checkAuthUser(userName, password),"true","User is not authorized");
        log.info("[UI] STEP 3 :: Log in as a user " + userName);
        LoginForm loginForm = new LoginForm(driver);
        Assert.assertTrue(loginForm.isDisplayed(), "Login form is not opened");
        loginForm.setUserName(userName);
        loginForm.setPassword(password);
        loginForm.clickBtnLogin();
        Assert.assertEquals(bookStore.getUserName(), userName, "Usernames are different");
    }
}