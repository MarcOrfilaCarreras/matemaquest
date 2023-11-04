package cloud.marcorfilacarreras.matemaquest.test.v1;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;

/**
 * Test class for the Exam functionality.
 */
public class ExamHandler {
    
    /**
     * Setting up the base URI and starting the Spark server before running the tests.
     */
    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = "http://localhost:8080/v1"; // Replace with your API base URL
    }

    /**
     * Test case to verify the default exam functionality.
     */
    @Test
    public void getDefault() {
        given()
            .when()
            .get("/exam")
            .then()
            .assertThat()
            .contentType(ContentType.JSON)
            .and()
            .body("status", equalTo("fail"))
            .and()
            .body("data.message", equalTo("Not found."))
            .and()
            .statusCode(404);
    }
    
    /**
     * Test case to verify the response when an valid exam id is provided.
     */
    @Test
    public void getExam() {
        given()
            .when()
            .get("/exam/100")
            .then()
            .assertThat()
            .contentType(ContentType.JSON)
            .and()
            .body("status", equalTo("success"))
            .and()
            .body("data", hasKey("questions"))
            .and()
            .statusCode(200);
    }
    
    /**
     * Test case to verify the response when an invalid exam id is provided.
     */
    @Test
    public void getInvalidExam() {
        given()
            .when()
            .get("/exam/0")
            .then()
            .assertThat()
            .contentType(ContentType.JSON)
            .and()
            .body("status", equalTo("fail"))
            .and()
            .body("data.message", equalTo("Invalid ID. Please provide a valid ID."))
            .and()
            .statusCode(404);
    }
}
