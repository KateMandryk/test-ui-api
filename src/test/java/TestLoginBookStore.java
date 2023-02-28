import api.ApplicationApi;
import forms.app.BookStore;
import forms.app.LoginForm;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestLoginBookStore extends BaseTest {
    private final String userName = reader.getValue("userName");
    private final String password = reader.getValue("password");

    @Test
    public void testLogin() {
        log.info("[UI] STEP 1 :: Navigate to Book Store");
        BookStore bookStore = new BookStore();
        bookStore.clickBtnLogin();
        log.info("[API] STEP 2 :: Registering a new user");
        ApplicationApi applicationApi = new ApplicationApi();
        applicationApi.authUser(userName, password);
        applicationApi.checkAuthUser(userName, password);
        log.info("[UI] STEP 3 :: Log in as a user " + userName);
        LoginForm loginForm = new LoginForm();
        loginForm.typeUserName(userName);
        loginForm.typePassword(password);
        loginForm.clickBtnLogin();
        Assert.assertEquals(bookStore.getUserName(), userName, "Usernames are different");
    }
}