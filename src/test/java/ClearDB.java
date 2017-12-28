import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class ClearDB {

    List<Integer> userId = new ArrayList<>();

    @BeforeClass
    public static void requestSpec() {
        RestAssured.baseURI = "http://localhost:8080/users/";
    }

    @Test
    public void createTestUsers() {
        Response res = given()
                .when()
                .contentType(ContentType.JSON)
                .get()
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .response();

        JsonPath jsonPath = new JsonPath(res.asString());
        userId = jsonPath.getList("id", Integer.TYPE);

        for (Integer id : userId) {
            given()
                    .when()
                    .contentType(ContentType.JSON)
                    .delete(String.valueOf(id))
                    .then()
                    .assertThat()
                    .statusCode(204);
        }
    }
}
