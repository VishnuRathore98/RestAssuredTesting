package Tute2;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import static io.restassured.RestAssured.given;

public class ExternalJsonRequestBodyTest {
    int id;
    @Test(priority = 1)
    void createUser() throws FileNotFoundException {
        File file = new File("/home/vpsr/IdeaProjects/RestAssuredTesting/src/test/resources/data.json");
        FileReader fileReader = new FileReader(file);
        JSONTokener jsonTokener = new JSONTokener(fileReader);
        JSONObject data = new JSONObject(jsonTokener);

        id = given()
                .contentType("application/json")
                .body(data.toString())
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
