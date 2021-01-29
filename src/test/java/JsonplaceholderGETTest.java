import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class JsonplaceholderGETTest {
    @Test
public void jsonplaceholderReadAllComments() {

        Response response = given()
                .when()
                .get("https://jsonplaceholder.typicode.com/comments")
                .then()
                .statusCode(200)
                .extract()
                .response();

        JsonPath json = response.jsonPath();
        List<String> names = json.getList("name");
        names.stream()
             .forEach(System.out::println);

        assertEquals(500, names.size());
    }

        @Test
                public void jsonplaceholderReadOnecomment(){

            Response response = given()
                    .when()
                    .get("https://jsonplaceholder.typicode.com/comments/1")
                    .then()
                    .statusCode(200)
                    .extract()
                    .response();

            JsonPath json = response.jsonPath();

            assertEquals("id labore ex et quam laborum", json.get("name"));
            assertEquals("Eliseo@gardner.biz", json.get("email"));

        }

        @Test
    public void jsonplaceholderReadOnePostWithPathVariable(){

            Response response = given()
                    .pathParam("postId", 1)
                    .when()
                    .get("https://jsonplaceholder.typicode.com/comments/{postId}")
                    .then()
                    .statusCode(200)
                    .extract()
                    .response();

            JsonPath json =response.jsonPath();
            assertEquals("id labore ex et quam laborum", json.get("name"));
            assertEquals("Eliseo@gardner.biz", json.get("email"));


        }

        @Test

        public void jsonplaceholderReadOnePostWithQueryParams(){
            Response response = given()
                    .queryParam("name", "id labore ex et quam laborum")
                    .when()
                    .get("https://jsonplaceholder.typicode.com/comments")
                    .then()
                    .statusCode(200)
                    .extract()
                    .response();

            JsonPath json = response.jsonPath();

            assertEquals("id labore ex et quam laborum", json.getList("name").get(0));
            assertEquals("Eliseo@gardner.biz", json.getList("email").get(0));


        }






    }






