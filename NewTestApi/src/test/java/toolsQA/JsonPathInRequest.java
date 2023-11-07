package toolsQA;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class JsonPathInRequest {
    @Test
    public void VerifyCityInJsonResponse(){
        RestAssured.baseURI="https://reqres.in/api/users";
        RequestSpecification httpRec = given();
        Response response = httpRec.get("?page=2");
        JsonPath jsonPath  = response.jsonPath();
        String email1 = jsonPath.get("data[1].email");
        System.out.println(email1);
        Assert.assertEquals("lindsay.ferguson@reqres.in",email1);
    }
}
