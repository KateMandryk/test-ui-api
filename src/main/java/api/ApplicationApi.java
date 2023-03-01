package api;

import io.restassured.http.ContentType;
import models.Book;
import models.Credentials;
import org.apache.hc.core5.http.HttpStatus;
import utils.ConfigReader;

import java.util.List;

import static api.Endpoints.*;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class ApplicationApi {
    private final ConfigReader reader = new ConfigReader();
    private final String url = reader.getValue("baseUrl");

    public List<Book> getBookList() {
        return given()
          .when().contentType(ContentType.JSON).get(url+BOOKS)
                .then().assertThat()
                .statusCode(HttpStatus.SC_OK).log().all().extract()
                .response().jsonPath().getList("books", Book.class);
    }

    public void checkAuthUser(String userName, String password) {
        Credentials credentials = new Credentials(userName, password);
        given()
                .log().body().contentType(JSON).body(credentials)
                .when().post(url + USER)
                .then().assertThat().statusCode(HttpStatus.SC_CREATED)
                .log().all();
    }

    public void authUser(String userName, String password) {
        Credentials credentials = new Credentials(userName, password);
        given()
                .log().body().contentType(JSON).body(credentials)
                .when().post(url + AUTHORIZATION)
                .then().assertThat().statusCode(HttpStatus.SC_OK)
                .log().all();
    }
}