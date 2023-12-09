package Tute2;

import org.json.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;


public class JsonRequestBodyTest {

    int id;

    @Test(priority = 1)
    void createUser(){
        JSONObject json = new JSONObject();
        json.put("name","Bonni");
        json.put("job","Frontend Developer");

        id = given()
                .contentType("application/json")
                .body(json.toString())
            .when()
                .post("https://reqres.in/api/users")
                .jsonPath().getInt("id");
    }

    @Test(priority = 2)
    void deleteUser(){
        given()
            .when()
            .delete("https://reqres.in/api/users/"+id)
            .then()
            .statusCode(204)
            .log().all();
    }
}
