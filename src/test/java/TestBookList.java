import api.ApplicationApi;
import forms.app.BookStorePage;
import models.Book;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

public class TestBookList extends BaseTest{


    @Test
    public void testBookList(){
        log.info("[UI] STEP 1 :: Navigate to Book Store");
        BookStorePage bookStore=new BookStorePage();
        Assert.assertTrue(bookStore.isDisplayed(),"Book store page is not opened");
        log.info("[API] STEP 2 :: Getting book list");
        ApplicationApi applicationApi=new ApplicationApi();
        List<Book> bookListApi=applicationApi.getBookList();
        List<String> titleApi=bookListApi.stream().map(Book::getTitle).collect(Collectors.toList());
        int numberOfBooks=titleApi.size();
        log.info("[UI] STEP 3 :: Getting book list on the page");
        List<Book> bookListUI=bookStore.getTable();
        List<String> titleUI=bookListUI.stream().map(Book::getTitle).limit(numberOfBooks).collect(Collectors.toList());
        Assert.assertTrue(titleUI.equals(titleApi),"Titles of books do not match");
    }
}