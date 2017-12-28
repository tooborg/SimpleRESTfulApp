import classes.User;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class CreateTestUsers {

    User[] users = {
            new User().setName("Donald Trump").setAge(61),
            new User().setName("Santa Claus").setAge(487)
    };

    @BeforeClass
    public static void requestSpec() {
        RestAssured.baseURI = "http://localhost:8080/users/";
    }

    @Test
    public void createTestUsers() {
        for (User user : users) {
            given()
                    .body(user)
                    .when()
                    .contentType(ContentType.JSON)
                    .post()
                    .then()
                    .assertThat()
                    .statusCode(201);
        }
    }
}
