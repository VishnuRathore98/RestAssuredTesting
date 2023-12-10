package Tute8;

import com.github.javafaker.Faker;
import org.json.JSONObject;
import org.junit.jupiter.api.TestInstance;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CreateUserTest {
    @Test
    void testCreateUser(ITestContext context){
        Faker faker = new Faker();
        System.out.println("===> Creating new User ...");
        JSONObject data = new JSONObject();
        data.put("name",faker.name().fullName());
        data.put("gender","Male");
        data.put("email",faker.internet().emailAddress());
        data.put("status","inactive");

        String bearerToken = "98e81ac7b5015bd54b483cd742fc2cfb02a5418b90db0e12eb746d413bca6121";//Get this bearer token

        int id = given()
            .headers("Authorization","Bearer "+bearerToken)
            .contentType("application/json")
            .body(data.toString())
            .when()
            .post("https://gorest.co.in/public/v2/users")//Check this url
            .jsonPath().getInt("id");

        //For making variable available to single test class.
        context.setAttribute("user_id",id);
        //For making variable available to whole test suite.
//        context.getSuite().setAttribute("user_id",id);
        System.out.println("===> User "+data.getString("name")+" created successfully.");
    }
}
