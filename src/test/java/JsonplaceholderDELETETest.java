import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class JsonplaceholderDELETETest {

@Test

    public void jsonplaceholderDeleteComment(){

    Response response = given()
            .when()
            .delete("https://jsonplaceholder.typicode.com/comments/1")
            .then()
            .statusCode(200)
            .extract()
            .response();

}
}
