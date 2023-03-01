import api.ApplicationApi;
import forms.app.BookStorePage;
import forms.app.LoginForm;
import org.apache.hc.core5.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestLoginBookStore extends BaseTest {
    private final String userName = reader.getValue("userName");
    private final String password = reader.getValue("password");

    @Test
    public void testLogin() {
        log.info("[UI] STEP 1 :: Navigate to Book Store");
        BookStorePage bookStore = new BookStorePage();
        Assert.assertTrue(bookStore.isDisplayed(),"Book store page is not opened");
        bookStore.clickBtnLogin();
        log.info("[API] STEP 2 :: Registering a new user");
        ApplicationApi applicationApi = new ApplicationApi();
        applicationApi.authUser(userName, password);
        applicationApi.checkAuthUser(userName, password);
        log.info("[UI] STEP 3 :: Log in as a user " + userName);
        LoginForm loginForm = new LoginForm();
        Assert.assertTrue(loginForm.isDisplayed(),"Login form is not opened");
        loginForm.setUserName(userName);
        loginForm.setPassword(password);
        loginForm.clickBtnLogin();
        Assert.assertEquals(bookStore.getUserName(), userName, "Usernames are different");
    }
}