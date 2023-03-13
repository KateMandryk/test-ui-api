import api.ApplicationApi;
import forms.app.BookStorePage;
import forms.app.LoginForm;
import models.User;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class LoginBookStoreTest extends BaseTest {
    private final String userName = credentials.getLogin();
    private final String password = credentials.getPassword();
    private final String userId="userID";

    @Test
    public void testLoginBookStore() {
        log.info("[UI] STEP 1 :: Navigate to Book Store");
        BookStorePage bookStore = new BookStorePage(driver);
        Assert.assertTrue(bookStore.isDisplayed(), "Book store page is not opened");
        bookStore.clickBtnLogin();
        log.info("[API] STEP 2 :: Create a new user");
        ApplicationApi applicationApi = new ApplicationApi();
        Map<User,String> user=applicationApi.createUser(userName, password);
        log.info("[UI] STEP 3 :: Navigate to Login form");
        LoginForm loginForm = new LoginForm(driver);
        Assert.assertTrue(loginForm.isDisplayed(), "Login form is not opened");
        log.info("[UI] STEP 4 :: Log in as a user " + userName);
        loginForm.setUserName(userName);
        loginForm.setPassword(password);
        loginForm.clickBtnLogin();
        Assert.assertEquals(bookStore.getUserName(), userName, "Usernames are different");
        log.info("[API] STEP 5 :: Delete user " + userName);
        applicationApi.deleteUser(user.get(userId));
    }
}