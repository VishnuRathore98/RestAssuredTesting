package Tute2;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class POJORequestBodyTest {
    int id;
    @Test(priority = 1)
    void createUser(){
        POJO_DataClass data = new POJO_DataClass();
        data.setName("Charlie");
        data.setJob("Android Developer");

        System.out.println(data);

        id = given()
                .contentType("application/json")
                .body(data)
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
