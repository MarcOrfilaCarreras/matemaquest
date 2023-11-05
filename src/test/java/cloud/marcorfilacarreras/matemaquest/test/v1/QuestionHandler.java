package cloud.marcorfilacarreras.matemaquest.test.v1;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.equalTo;

/**
 * Test class for the Question functionality.
 */
public class QuestionHandler {
    
    /**
     * Setting up the base URI and starting the Spark server before running the tests.
     */
    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = "http://localhost:8080/v1"; // Replace with your API base URL
    }

    /**
     * Test case to verify the default question functionality.
     */
    @Test
    public void getDefault() {
        given()
            .when()
            .get("/question")
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
     * Test case to verify the response when an valid question id is provided.
     */
    @Test
    public void getExam() {
        given()
            .when()
            .get("/question/6000")
            .then()
            .assertThat()
            .contentType(ContentType.JSON)
            .and()
            .body("status", equalTo("success"))
            .and()
            .body("data.id", equalTo(6000))
            .and()
            .statusCode(200);
    }
    
    /**
     * Test case to verify the response when an invalid question id is provided.
     */
    @Test
    public void getInvalidQuestion() {
        given()
            .when()
            .get("/question/0")
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
    
    /**
     * Test case to verify the response when a question id does not exist.
     */
    @Test
    public void getNonExistentQuestion() {
        given()
            .when()
            .get("/question/100000")
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
}
