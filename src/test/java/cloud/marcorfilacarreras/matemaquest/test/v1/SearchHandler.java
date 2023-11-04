package cloud.marcorfilacarreras.matemaquest.test.v1;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import java.util.List;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.isA;

/**
 * Test class for the Search functionality.
 */
public class SearchHandler {
    
    /**
     * Setting up the base URI and starting the Spark server before running the tests.
     */
    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = "http://localhost:8080/v1"; // Replace with your API base URL
    }

    /**
     * Test case to verify the default search functionality.
     */
    @Test
    public void getDefault() {
        given()
            .when()
            .get("/search")
            .then()
            .assertThat()
            .contentType(ContentType.JSON)
            .and()
            .body("status", equalTo("success"))
            .and()
            .body("data", isA(List.class))
            .and()
            .body("$", hasKey("meta"))
            .and()
            .statusCode(200);
    }
    
    /**
     * Test case to verify the search functionality with language specified.
     */
    @Test
    public void getLang() {
        given()
            .when()
            .get("/search?lang=es")
            .then()
            .assertThat()
            .contentType(ContentType.JSON)
            .and()
            .body("status", equalTo("success"))
            .and()
            .body("data", isA(List.class))
            .and()
            .body("$", hasKey("meta"))
            .and()
            .statusCode(200);
    }
    
    /**
     * Test case to verify the response when an invalid language is provided.
     */
    @Test
    public void getInvalidLang() {
        given()
            .when()
            .get("/search?lang=test")
            .then()
            .assertThat()
            .contentType(ContentType.JSON)
            .and()
            .body("status", equalTo("fail"))
            .and()
            .body("data.message", equalTo("Invalid lang. Please provide a valid lang (es / ca)."))
            .and()
            .statusCode(400);
    }
    
    /**
     * Test case to verify the search functionality with pagination.
     */
    @Test
    public void getPage() {
        given()
            .when()
            .get("/search?page=2")
            .then()
            .assertThat()
            .contentType(ContentType.JSON)
            .and()
            .body("status", equalTo("success"))
            .and()
            .body("data", isA(List.class))
            .and()
            .body("$", hasKey("meta"))
            .and()
            .statusCode(200);
    }
    
    /**
     * Test case to verify the response when an invalid page number is provided.
     */
    @Test
    public void getInvalidPage() {
        given()
            .when()
            .get("/search?page=test")
            .then()
            .assertThat()
            .contentType(ContentType.JSON)
            .and()
            .body("status", equalTo("fail"))
            .and()
            .body("data.message", equalTo("Invalid page. Please provide a valid page number."))
            .and()
            .statusCode(400);
    }
}
