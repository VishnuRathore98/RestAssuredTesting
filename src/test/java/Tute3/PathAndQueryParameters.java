package Tute3;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PathAndQueryParameters {
    @Test
    void testPathAndQueryParameters()
    {
        given()
            .pathParam("myPath1","api")
            .pathParam("myPath2","users")
            .queryParam("page",2)
            .queryParam("id",5)
            .when()
                .get("https://reqres.in/{myPath1}/{myPath2}")
                .then()
                    .statusCode(200)
                    .log().all();
    }
}
