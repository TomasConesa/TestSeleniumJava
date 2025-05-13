package TestBack;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

import static io.restassured.RestAssured.given;

public class DELETETest {

    @Test
    public void DeleteTest() {
        Response response =
                given()
                        .header("x-api-key", "reqres-free-v1")
                        .delete("https://reqres.in/api/users/55");

        Assert.assertEquals(response.getStatusCode(), 204);
        System.out.println("CODIGO DE RESPUESTA: " + response.getStatusCode());
    }

    @Test
    public void DeleteTest2() {
        String url = "https://reqres.in/api";
        String pathUser = "/users";
        String deleteUser = "/15";

        Response response =
                given()
                        .header("x-api-key", "reqres-free-v1")
                        .delete(url + pathUser + deleteUser);

        Assert.assertEquals(response.getStatusCode(), 204);
        System.out.println("CODIGO DE RESPUESTA: " + response.getStatusCode());
    }
}
