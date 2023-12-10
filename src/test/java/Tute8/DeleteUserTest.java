package Tute8;

import org.junit.jupiter.api.TestInstance;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DeleteUserTest {
    @Test
    void testDeleteUser(ITestContext context){
        String bearerToken = "98e81ac7b5015bd54b483cd742fc2cfb02a5418b90db0e12eb746d413bca6121";
        int id = (Integer) context.getAttribute("user_id");
        System.out.println("===> Deleting user with id : "+id+" ...");

//        int id = (Integer) context.getSuite().getAttribute("user_id");
        given()
            .headers("Authorization","Bearer "+bearerToken)
            .pathParam("id",id)
            .when()
            .delete("https://gorest.co.in/public/v2/users/{id}")
            .then()
            .statusCode(204)
            .log().all();
        System.out.println("===> User with id : "+id+" has been deleted Successfully.");
    }
}