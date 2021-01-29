import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class JsoplaceholderReadAllUSersTest {

    @Test

    public void ReadAllusers(){

        Response response = given()
                .when()
                .get("https://jsonplaceholder.typicode.com/users")
                .then()
                .statusCode(200)
                .extract()
                .response();

        JsonPath json = response.jsonPath();
        List<String> emails = json.getList("email");
        //emails.stream()
         //    .forEach(System.out::println);

        emails.stream()
              .filter(email ->email.contains(".pl"))
              .forEach(System.out::println);


        boolean anyMatchEndsWithpl = emails.stream()
                          .anyMatch(email -> email.contains(".pl"));

        System.out.println(anyMatchEndsWithpl);


    }
}
