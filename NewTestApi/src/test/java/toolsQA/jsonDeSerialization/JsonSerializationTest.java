package toolsQA.jsonDeSerialization;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class JsonSerializationTest {

    @Test
    public void userRegistrationSuccessfull(){

        RequestSpecification request =given()
                .contentType(ContentType.JSON)
                .baseUri("https://reqres.in/")
                .basePath("api/users/2");
        JSONObject requestBody = new JSONObject();
        requestBody.put("name", "morpheus");
        requestBody.put("job", "zion resident");
        request.body(requestBody.toJSONString());
        Response response = request.put();
        int statuscode = response.statusCode();
        System.out.println("statusCode = "+statuscode);
        ResponseBody body = response.body();
        String str = body.asPrettyString();
        UpdateResponse upres =body.as(UpdateResponse.class);
        System.out.println(str);
        System.out.println("======================");
        System.out.println(upres);
    }
}
