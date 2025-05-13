package TestBack;

import com.google.gson.JsonObject;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class POSTTest {

    @Test
    public void PostTest() {
        JsonObject request = new JsonObject();
        request.addProperty("name", "space ko");
        request.addProperty("job", "poker player");

        given()
                .header("x-api-key", "reqres-free-v1")
                .contentType("application/json")
                .body(request)
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201)
                .log().status()
                .log().body();
    }

    @Test
    public void PostTest2() {
        JsonObject request = new JsonObject();
        request.addProperty("name", "space ko supernova");
        request.addProperty("job", "poker player");

        given()
                .header("x-api-key", "reqres-free-v1")
                .contentType("application/json")
                .body(request)
                .post("https://reqres.in/api/users")
                .then()
                .log().status()
                .statusCode(201)
                .log().body()
                .body("name", equalTo("space ko supernova"))
                .body("job", equalTo("poker player"))
                .body("createdAt", containsString("2025-05-13"));

    }

    @Test
    public void loginUnsuccessful() {
        JsonObject request = new JsonObject();
        request.addProperty("email", "peter@klaven");

        Response response =
                given()
                        .header("x-api-key", "reqres-free-v1")
                        .contentType("application/json")
                        .body(request)
                        .post("https://reqres.in/api/login");

        String error = response.jsonPath().getString("error");
        Assert.assertEquals(400, response.getStatusCode());
        Assert.assertEquals(error, "Missing password");

        System.out.println(response.getBody().asString());
        System.out.println("MENSAJE DE ERROR: " + error);
        System.out.println("CODIGO DE ERROR: " + response.statusCode());
        System.out.println("SE TARDO " + response.time() + " MILISEGUNDOS");
    }
}
