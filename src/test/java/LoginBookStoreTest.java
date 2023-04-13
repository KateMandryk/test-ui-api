import api.ApplicationApi;
import forms.app.BookStorePage;
import forms.app.LoginForm;
import io.qameta.allure.Attachment;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.time.Duration;

@Listeners(TestListener.class)

public class LoginBookStoreTest extends BaseTest {
    private final String message = reader.getValue("message");


    @Test(groups = {"login test"})
    @Severity(SeverityLevel.CRITICAL)
    @Story("Log in user")
    public void testLoginBookStore() {
        log.info("[UI] STEP 1 :: Navigate to Book Store");
        BookStorePage bookStore = new BookStorePage(driver);
        Assert.assertTrue(bookStore.isDisplayed(), "Book store page is not opened");
        bookStore.clickBtnLogin();
        log.info("[UI] STEP 2 :: Navigate to Login form");
        LoginForm loginForm = new LoginForm(driver);
        Assert.assertTrue(loginForm.isDisplayed(), "Login form is not opened");
        log.info("[UI] STEP 3 :: Log in as a user " + userName);
        loginForm.setUserName(userName);
        loginForm.setPassword(password);
        loginForm.clickBtnLogin();
        Assert.assertEquals(bookStore.getUserName(), userName, "Usernames are different");
        log.info("[UI] STEP 4 :: Delete user " + userName);
        bookStore.clickBtnMenuLogin();
        loginForm.clickBtnProfile();
        bookStore.clickBtnDeleteAccount();
        bookStore.clickBtnOk();
        Alert alert = new WebDriverWait(driver, Duration.ofMillis(500L)).until(ExpectedConditions.alertIsPresent());
        alert.accept();
        log.info("[UI] STEP 5 ::Try to log in as a user " + userName + " after account deletion");
        loginForm.setUserName(userName);
        loginForm.setPassword(password);
        loginForm.clickBtnLogin();
        Assert.assertEquals(loginForm.getMsgLogin(), message, "Message is not displayed");
    }
}