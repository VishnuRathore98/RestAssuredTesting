package Tute7;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class AuthenticationTest {
    @Test
    void testBasicAuthentication(){
        given()
            .auth().basic("postman","password")
            .when()
            .get("https://postman-echo.com/basic-auth")
            .then()
            .statusCode(200)
            .body("authenticated",equalTo(true))
            .log().all();
    }
    @Test
    void testDigestAuthentication(){
        given()
            .auth().digest("postman","password")
            .when()
            .get("https://postman-echo.com/basic-auth")
            .then()
            .statusCode(200)
            .body("authenticated",equalTo(true))
            .log().all();
    }
    @Test
    void testPreemptiveAuthentication(){
        given()
            .auth().preemptive().basic("postman","password")
            .when()
            .get("https://postman-echo.com/basic-auth")
            .then()
            .statusCode(200)
            .body("authenticated",equalTo(true))
            .log().all();
    }
    @Test
    void testBearerTokenAuthentication(){
        String bearerToken = "";
        given()
            .headers("Authorization","Bearer "+bearerToken)
            .when()
            .get("")
            .then()
            .statusCode(200)
            .log().all();
    }
    @Test
    void testOAuth1Authentication(){
        given()
            .auth().oauth("consumerKey","consumerSecret","accessToken","tokenSecret")
            .when()
            .get("")
            .then()
            .statusCode(200)
            .log().all();
    }
    @Test
    void testOAuth2Authentication(){
        given()
            .auth().oauth2("auth token")
            .when()
            .get("")
            .then()
            .statusCode(200)
            .log().all();
    }
    @Test
    void testAPIKeyAuthentication(){
        given()
            .queryParam("appid","your api key")
            .when()
            .get("")
            .then()
            .statusCode(200)
            .log().all();
    }
}