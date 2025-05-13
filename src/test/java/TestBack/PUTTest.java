package TestBack;

import com.google.gson.JsonObject;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

import java.time.LocalDate;
import java.time.OffsetDateTime;

import static io.restassured.RestAssured.given;

public class PUTTest {

    @Test
    public void PutTest() {
        JsonObject requestBody = new JsonObject();
        requestBody.addProperty("name", "PKO");
        requestBody.addProperty("job", "Developer");

        given()
                .header("x-api-key", "reqres-free-v1")
                .contentType("application/json")
                .body(requestBody)
                .put("https://reqres.in/api/users/2")
                .then()
                .log().status()
                .statusCode(200);
    }

    @Test
    public void PutTest2() {
        JsonObject requestBody = new JsonObject();
        requestBody.addProperty("name", "Space KO");
        requestBody.addProperty("job", "Poker player");

        Response responseBody =
                given()
                        .header("x-api-key", "reqres-free-v1")
                        .contentType("application/json")
                        .body(requestBody)
                        .put("https://reqres.in/api/users/10");


        String nombreModificado = responseBody.jsonPath().getString("name");
        String trabajoModificado = responseBody.jsonPath().getString("job");
        String fechaDeModificacion = responseBody.jsonPath().getString("updatedAt");

        Assert.assertEquals(responseBody.getStatusCode(), 200);
        Assert.assertEquals(nombreModificado, "Space KO");
        Assert.assertEquals(trabajoModificado, "Poker player");

        LocalDate fechaModificada = OffsetDateTime.parse(fechaDeModificacion).toLocalDate();
        LocalDate fechaActual = LocalDate.now();

        Assert.assertEquals(fechaActual, fechaModificada);

        System.out.println("NOMBRE MODIFICADO: " + nombreModificado);
        System.out.println("TRABAJO MODIFICADO: " + trabajoModificado);
        System.out.println("FECHA DE MODIFICACION: " + fechaModificada);
        System.out.println("FECHA ACTUAL: " + fechaActual);
        System.out.println("CODIGO DE RESPUESTA: " + responseBody.getStatusCode());

    }
}
