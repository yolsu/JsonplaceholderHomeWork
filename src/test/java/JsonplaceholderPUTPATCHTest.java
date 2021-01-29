import com.github.javafaker.Faker;
import io.restassured.internal.path.json.mapping.JsonObjectDeserializer;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class JsonplaceholderPUTPATCHTest {

    private static Faker faker;
    private String fakeEmail;
    private String fakeName;
    private String fakeBody;

    @BeforeAll
    public static void beforeAll(){

            faker = new Faker();
    }


    @BeforeEach
    public void beforeEach(){

        fakeBody = faker.lorem().paragraph();
        fakeEmail = faker.internet().emailAddress();
        fakeName = faker.lorem().sentence();



    }




    @Test
public void JsonplaceholderPUTTest() {



        JSONObject comment = new JSONObject();

        comment.put("name", fakeName);
        comment.put("email", fakeEmail);
        comment.put("body", fakeBody);


        Response response = given()
                .contentType("application/json")
                .body(comment.toString())
                .when()
                .put("https://jsonplaceholder.typicode.com/comments/1")
                .then()
                .statusCode(200)
                .extract()
                .response();


        JsonPath json = response.jsonPath();

        assertEquals(fakeName, json.get("name"));
        assertEquals(fakeEmail, json.get("email"));
        assertEquals(fakeBody, json.get("body"));

    }

        @Test
                public void jsonplaceholderPATCHTest(){

        JSONObject comment = new JSONObject();

        comment.put("email", fakeEmail);

            Response response = given()
                    .contentType("application/json")
                    .body(comment.toString())
                    .when()
                    .patch("https://jsonplaceholder.typicode.com/comments/1")
                    .then()
                    .statusCode(200)
                    .extract()
                    .response();

            JsonPath json = response.jsonPath();

            Assertions.assertEquals(fakeEmail, json.get("email"));


        }



    }

