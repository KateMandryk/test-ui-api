package forms.app;

import forms.base.BaseForm;
import forms.base.Button;
import forms.base.Label;
import models.Book;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static utils.Singleton.getWebDriverInstance;

public class BookStorePage extends BaseForm {

    private static final By locator = By.xpath("//div[contains(@class,'main-header') and contains(text(),'Book Store')]");
    private static final String name = "Book Store";
    private final Button btnLogin = new Button(By.id("login"), "Button login");
    private final Label lblUserName = new Label(By.id("userName-value"), "Label UserName");
    private final By row = By.xpath("//div[contains(@class,'rt-tr-group')]");
    private final By sell = By.xpath(".//div[contains(@class,'rt-td')]");
    private final int indexTitleSell=1;
    private final int indexAuthorSell=2;
    private final int indexPublisherSell=3;

    public BookStorePage() {
        super(locator, name);
    }

    public LoginForm clickBtnLogin() {
        btnLogin.click();
        return new LoginForm();
    }

    public String getUserName() {
        return lblUserName.getText();
    }

    public List<Book> getTable() {
        List<Book> testsTable = new ArrayList<>();
        List<WebElement> rowTable = getWebDriverInstance().findElements(row);
        for (int i = 0; i < rowTable.size(); i++) {
            testsTable.add(i, createTable(rowTable.get(i)));
        }
        return testsTable;
    }

    public Book createTable(WebElement row) {
        Book book = new Book();
        List<WebElement> cellTable = row.findElements(sell);
        book.setTitle(cellTable.get(indexTitleSell).getText());
        book.setAuthor(cellTable.get(indexAuthorSell).getText());
        book.setPublisher(cellTable.get(indexPublisherSell).getText());
        return book;
    }
}

