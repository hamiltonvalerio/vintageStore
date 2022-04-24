package br.valerio.quarkus.microservices.book;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.startsWith;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class BookResourceTest {

    @Test
    public void shouldCreateABook() {
        given()
        .formParam("title", "Understanding Quarkus")
        .formParam("author", "Hamilton Valerio")
        .formParam("year", 2022)
        .formParam("genre", "IT")
          .when().post("/api/books")
          .then()
             .statusCode(201)
             .body("isbn_13", startsWith("13-"))
             .body("title", is("Understanding Quarkus"))
             .body("author", is("Hamilton Valerio"))
             .body("year_of_publication", is(2022))
             .body("genre", is("IT"))
             .body("creation_date", startsWith("20"));
             
    }

}