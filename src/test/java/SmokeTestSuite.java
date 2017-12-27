import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.List;

import static io.restassured.RestAssured.given;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SmokeTestSuite {

    private static int newUserId = 0;

    @BeforeClass
    public static void requestSpec() {
        RestAssured.baseURI = "http://localhost:8080/users/";
    }

    @Test
    public void test1_testGetUsersStatusCode() {
        given()
                .when()
                .get()
                .then()
                .assertThat().statusCode(200);
    }

    @Test
    public void test2_createNewUserNegative() {
        given().body("{\"name\":\"Test User\",\"age\":\"aaa\"}")
                .when()
                .contentType(ContentType.JSON)
                .post()
                .then()
                .assertThat().statusCode(400);
    }

    @Test
    public void test3_createNewUserPositive() {
        Response res = given().body("{\"name\":\"Test User\",\"age\":10}")
                .when()
                .contentType(ContentType.JSON)
                .post()
                .then()
                .assertThat()
                .statusCode(201)
                .extract().response();

        JsonPath jsonPath = new JsonPath(res.asString());
        Assert.assertEquals("Test User", jsonPath.getString("name"));
        Assert.assertEquals(10, jsonPath.getInt("age"));
        Assert.assertTrue((newUserId = jsonPath.getInt("id")) > 0);
    }

    @Test
    public void test4_getAllUsersTest() {
        Response res = given()
                .when()
                .get()
                .then()
                .contentType(ContentType.JSON)
                .extract().response();

        JsonPath jsonPath = new JsonPath(res.asString());
        List<Integer> ids = jsonPath.getList("id");
        Assert.assertTrue(ids.contains(newUserId));
    }

    @Test
    public void test5_deleteUserTest() {
        given()
                .when()
                .contentType(ContentType.JSON)
                .delete(String.format("/%d", newUserId))
                .then()
                .assertThat()
                .statusCode(204);

        Response res = given()
                .when()
                .get()
                .then()
                .contentType(ContentType.JSON)
                .extract().response();

        JsonPath jsonPath = new JsonPath(res.asString());
        List<Integer> ids = jsonPath.getList("id");
        Assert.assertTrue(!ids.contains(newUserId));
    }

    @Test
    public void test6_deleteUserTest() {
        given()
                .when()
                .contentType(ContentType.JSON)
                .delete(String.format("/%d", newUserId))
                .then()
                .assertThat()
                .statusCode(404);
    }
}
