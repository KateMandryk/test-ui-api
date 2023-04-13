package api;

import models.Book;
import models.Credentials;
import models.User;
import org.apache.http.HttpStatus;
import utils.DataReader;

import java.util.List;
import java.util.Map;

import static api.Endpoints.BOOKS;
import static api.Endpoints.USER;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class ApplicationApi {
    private final DataReader reader = new DataReader();
    private final String url = reader.getValue("baseUrl");

    public List<Book> getBookList() {
        return given()
                .when().contentType(JSON).get(url + BOOKS)
                .then().assertThat()
                .statusCode(HttpStatus.SC_OK).log().all().extract()
                .response().jsonPath().getList("books", Book.class);
    }

    public Map<User, String> createUser(String userName, String password) {
        Credentials credentials = new Credentials(userName, password);
        return given()
                .contentType(JSON).body(credentials)
                .when().post(url + USER)
                .then().log().all().extract().body().jsonPath().getMap(".");
    }
}