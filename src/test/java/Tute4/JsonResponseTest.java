package Tute4;

import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.testng.Assert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class JsonResponseTest {

   final String URL = "https://reqres.in/api/users";

   // Using simple RestAssured assertion.

    @Test
    void testJsonResponse(){
        given()
            .contentType("ContentType.JSON")
            .when()
            .get(URL)
            .then()
            .statusCode(200)
            .header("content-type","application/json; charset=utf-8")
            .body("data[1].first_name",equalTo("Janet"));
//            .log().body();
    }

    //  Using TestNg Assertion.

    @Test
    void assertJsonResponse(){
        Response res = given()
            .contentType("ContentType.JSON")
            .when()
            .get(URL);
        Assert.assertEquals(res.getStatusCode(),200);
        Assert.assertEquals(res.header("content-type"),"application/json; charset=utf-8");
        String last_name = res.jsonPath().getString("data[4].last_name");
        Assert.assertEquals(last_name,"Morris");
        System.out.println("User with last name "+last_name+" exists.");
    }

// Using JSONObject class for converting response type to JSON.

    @Test
    void jsonResponseToJsonObject(){
        Response response = given()
            .contentType("ContentType.JSON")
            .when()
            .get(URL);
//        System.out.println("Response received: "+response.asString());
        JSONObject jsonObject = new JSONObject(response.asString());
//        System.out.println("Response converted to jsonObject.");

        boolean status = false;
        for (int i=0;i<jsonObject.getJSONArray("data").length();i++){
            String last_name = jsonObject.getJSONArray("data").getJSONObject(i)
                                .getString("last_name");
//            System.out.println("Last name received: "+last_name);
            if (last_name.equals("Ramos")){
                System.out.println("Last name "+last_name+" Matched.");
                status = true;
                break;
            }
        }
        Assert.assertTrue(status);

    }

//  Totalling the number of users received on page 1.

    @Test
    void testNumberOfUsers(){
        int total_users = 0;
        Response response = given()
            .contentType("ContentType.JSON")
            .when()
            .get(URL);
//        System.out.println("Response received: "+response.asString());
        JSONObject jsonObject = new JSONObject(response.asString());
        for(int i=0;i<jsonObject.getJSONArray("data").length();i++){
           int user_id = jsonObject.getJSONArray("data").getJSONObject(i).getInt("id");
           total_users++;
        }
        Assert.assertEquals(jsonObject.getInt("per_page"),total_users);
        System.out.println("Total No. of existing users: "+total_users);
    }

}















