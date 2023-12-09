package Tute3;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static io.restassured.RestAssured.given;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class HeadersTest {
    @Test
    void testHeaders(){
        given()
            .when()
            .get("https://www.google.com")
            .then()
            .header("Content-Type","text/html; charset=ISO-8859-1")
            .and()
            .header("Content-Encoding","gzip")
            .and()
            .header("Server","gws");
//            .log().headers();
    }
    @Test
    void getHeaders(){
        Response res = given()
            .when()
            .get("https://www.google.com");

        Headers myheaders = res.getHeaders();
        for (Header header:myheaders){
            System.out.println(header.getName()+" : "+header.getValue());
        }

    }
}













