package Tute2;

import org.testng.annotations.Test;
import java.util.HashMap;
import static io.restassured.RestAssured.*;

public class HashMapRequestBody {

    int id;

    @Test(priority = 1)
    void createUser(){
        HashMap map = new HashMap();
        map.put("name","Anni");
        map.put("job","Backend Developer");

        id = given()
                .contentType("application/json")
                .body(map)
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

