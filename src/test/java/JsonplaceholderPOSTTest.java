import com.github.javafaker.Faker;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Locale;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class JsonplaceholderPOSTTest {

    @Test

    public void JsoplaceholderCreateNewComment(){

        Locale locale;
        Faker faker = new Faker();
        String fakeName = faker.name().name();
        String fakeEmail = faker.internet().emailAddress();
        String fakeBody = faker.lorem().sentence();


        JSONObject comment = new JSONObject();
        comment.put("name", fakeName);
        comment.put("email", fakeEmail);
        comment.put("body", fakeBody);


        Response response = given()
                .contentType("application/json")
                .body(comment.toString())
                .when()
                .post("https://jsonplaceholder.typicode.com/comments")
                .then()
                .statusCode(201)
                .extract()
                .response();


        JsonPath json = response.jsonPath();

        assertEquals(fakeName,json.get("name"));
        assertEquals(fakeEmail, json.get("email"));
        assertEquals(fakeBody, json.get("body"));


    }
}
