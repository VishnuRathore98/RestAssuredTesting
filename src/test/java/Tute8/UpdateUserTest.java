package Tute8;

import com.github.javafaker.Faker;
import org.json.JSONObject;
import org.junit.jupiter.api.TestInstance;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UpdateUserTest {
    @Test
    void testUpdateUser(ITestContext context){
        System.out.println("===> Updating user details ...");
        String bearerToken = "98e81ac7b5015bd54b483cd742fc2cfb02a5418b90db0e12eb746d413bca6121";
        Faker faker = new Faker();
        JSONObject data = new JSONObject();
        data.put("name",faker.name().fullName());
        data.put("gender","Male");
        data.put("email",faker.internet().emailAddress());
        data.put("status","Active");

        int id = (Integer) context.getAttribute("user_id");
//        int id = (Integer) context.getSuite().getAttribute("user_id");
        given()
            .headers("Authorization","Bearer "+bearerToken)
            .contentType("application/json")
            .body(data.toString())
            .pathParam("id",id)
            .when()
            .put("https://gorest.co.in/public/v2/users/{id}")
            .then()
            .statusCode(200)
            .log().all();
        System.out.println("===> User details for "+data.getString("name")+" updated successfully.");
    }
}
