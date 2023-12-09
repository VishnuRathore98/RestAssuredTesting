package Tute3;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Map;

import static io.restassured.RestAssured.given;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CookiesTest {
    @Test
    void getCookiesInfo(){

        Response res = given()
                        .when()
                         .get("https://www.google.com");
//        String cookie_Value = res.getCookie("AEC");
//        System.out.println("Value of cookie is ===> "+cookie_Value);
        Map<String, String> cookie_values = res.getCookies();
        System.out.println(cookie_values.keySet());
        for (String k:cookie_values.keySet()){
            String cookie_value = res.getCookie(k);
            System.out.println(k+" : "+cookie_value);
        }
    }
}