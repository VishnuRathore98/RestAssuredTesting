package Tute6;

import io.restassured.module.jsv.JsonSchemaValidator;
import org.junit.jupiter.api.TestInstance;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class JsonSchemaValidationTest {

    final String URL = "https://reqres.in/api/users";

    @Test
    void testJsonSchemaValidation(){
        given()
            .when()
            .get(URL)
            .then()
            .assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema.json"));

    }
}
