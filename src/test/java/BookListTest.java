import api.ApplicationApi;
import forms.app.BookStorePage;
import models.Book;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class BookListTest extends BaseTest {


    @Test
    public void testBookList() {
        log.info("[UI] STEP 1 :: Navigate to Book Store");
        BookStorePage bookStore = new BookStorePage(driver);
        Assert.assertTrue(bookStore.isDisplayed(), "Book store page is not opened");
        log.info("[API] STEP 2 :: Getting book list");
        ApplicationApi applicationApi = new ApplicationApi();
        List<Book> bookListApi = applicationApi.getBookList();
        List<String> titleApi = bookListApi.stream().map(Book::getTitle).collect(Collectors.toList());
        log.info("[UI] STEP 3 :: Getting book list on the page");
        List<Book> bookListUI = bookStore.getTable();
        List<String> titleUI = bookListUI.stream().map(Book::getTitle).filter(x -> !x.equals(" ")).collect(Collectors.toList());
        Assert.assertEquals(titleApi, titleUI, "Titles of books do not match");
    }
}