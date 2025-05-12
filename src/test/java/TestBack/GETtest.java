package TestBack;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class GETtest {

    @Test
    public void GetTest() {
        Response resGet = RestAssured.get("https://reqres.in/api/users?page=2");
        System.out.println(resGet.getBody().asString());
        System.out.println(resGet.getStatusCode());
        System.out.println(resGet.getHeader("Date"));
        System.out.println(resGet.getTime());
    }

}
