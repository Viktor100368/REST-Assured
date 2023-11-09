package toolsQA.jsonDeSerialization;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class RegisterSuccessfullTest {
    @Test
    public void getRegisterSuccessfull(){
//        RequestSpecification request = given()
//                .contentType(ContentType.JSON)
//                .baseUri("https://reqres.in")
//                .basePath("/api/register");
//        JSONObject requestBody = new JSONObject();
//        requestBody.put("email","eve.holt@reqres.in");
//        requestBody.put("password", "pistol");
//        request.body(requestBody.toJSONString());
//        Response response = request.post();
//        ResponseBody body = response.body();
//        int statusCode = response.statusCode();
//        System.out.println("status code = "+ statusCode);
//        JsonSuccessResponse res = body.as(JsonSuccessResponse.class);
//        System.out.println(res);
        //===========================================================

        SuccessfullRegistration reg = new SuccessfullRegistration("eve.olt@reqres.in","istol");
        SuccessRegistrationResponse res=given()
                .contentType(ContentType.JSON)
                .baseUri("https://reqres.in")
                .basePath("/api/register")
                .body(reg)
                .when()
                .post()
                .then().log().all()
               // .statusCode(200)
                .extract().as(SuccessRegistrationResponse.class);
        System.out.println(res);

    }
    @Test
    public void userRegistrSuccessOrUnSucces(){
        RequestSpecification request =given()
                .contentType(ContentType.JSON)
                .baseUri("https://reqres.in")
                .basePath("/api/register");
        JSONObject requestBody = new JSONObject();
        requestBody.put("email","eve.olt@reqres.in");
        requestBody.put("password", "pistol");
        request.body(requestBody.toJSONString());
        Response response = request.post();
        int statusCode = response.statusCode();
        System.out.println("status code = "+statusCode);
        ResponseBody body = response.body();
        if(statusCode == 200){
            SuccessRegistrationResponse res = body.as(SuccessRegistrationResponse.class);
            System.out.println(res);
        }else{
            UnSuccessRegistration res = body.as(UnSuccessRegistration.class);
            System.out.println(res);
        }

    }
}
