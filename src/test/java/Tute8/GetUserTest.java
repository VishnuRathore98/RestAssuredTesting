package Tute8;

import org.junit.jupiter.api.TestInstance;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GetUserTest {
    @Test
    void testGetUser(ITestContext context){
        int id = (Integer) context.getAttribute("user_id");
        String bearerToken = "98e81ac7b5015bd54b483cd742fc2cfb02a5418b90db0e12eb746d413bca6121";
        given()
            .headers("Authorization","Bearer "+bearerToken)
            .pathParam("id",id)
            .when()
            .get("https://gorest.co.in/public/v2/users/{id}")
            .then()
            .statusCode(200)
            .log().all();
    }
}
