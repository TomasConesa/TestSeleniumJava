package TestBack;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

import static io.restassured.RestAssured.given;

public class GETtest {

    @Test
    public void GetTest() {
        Response resGet = RestAssured.get("https://reqres.in/api/users?page=2");
        System.out.println(resGet.getBody().asString());
        System.out.println(resGet.getStatusCode());
        System.out.println(resGet.getHeader("Date"));
        System.out.println(resGet.getTime());
    }

    @Test
    public void GetTest2() {
        Response resGet = given()
                .header("x-api-key", "reqres-free-v1")
                .header("Accept", "application/json") // opcional pero recomendable
                .when()
                .get("https://reqres.in/api/users?page=2");

        int statusCode = resGet.statusCode();
        JsonPath body = resGet.jsonPath();
        String mail_0 = body.getString("data.email[0]");
        String name_0 = body.getString("data.first_name[0]");
        String lastName_0 = body.getString("data.last_name[0]");

        Assert.assertEquals(statusCode, 200);
        Assert.assertEquals(mail_0, "michael.lawson@reqres.in");
        Assert.assertEquals(name_0, "Michael");
        Assert.assertEquals(lastName_0, "Lawson");

        System.out.println("Status code: " + statusCode);
        System.out.println("Email: " + mail_0);
        System.out.println("Name: " + name_0);
        System.out.println("Last name: " + lastName_0);
    }

    @Test
    public void GetTest3() {
        given()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200)
                .log()
                .body(false); // Para obtenerlo como string
    }

    @Test
    public void PostTest() {
        given()
                .header("x-api-key", "reqres-free-v1")
                .header("Content-Type", "application/json")
                .body("{ \"name\": \"morpheus\", \"job\": \"leader\" }")
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201)
                .log()
                .body(false); // false para que no se imprima todo si es largo
    }

    @Test
    public void PutTest() {
        given()
                .header("x-api-key", "reqres-free-v1")
                .header("Content-Type", "application/json")
                .body("{ \"name\": \"morpheus\", \"job\": \"zion resident\" }")
                .when()
                .put("https://reqres.in/api/users/2")
                .then()
                .statusCode(200)
                .log()
                .body(false);
    }

    @Test
    public void DeleteTest() {
        given()
                .header("x-api-key", "reqres-free-v1")
                .when()
                .delete("https://reqres.in/api/users/2")
                .then()
                .statusCode(204)
                .log();
    }
}
