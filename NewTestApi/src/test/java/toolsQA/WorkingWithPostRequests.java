package toolsQA;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class WorkingWithPostRequests {
    @Test
    public void createUser(){
        RestAssured.baseURI="https://demoqa.com/";
        RequestSpecification request = given();
     //   Response response;  // = request.get("BookStore/v1/Books");
//        ResponseBody body = response.body();
//        String res = body.asPrettyString();
//        System.out.println(res);

        //создадим новый JSON объект
        JSONObject requestParams = new JSONObject();
        //================================
//        requestParams.put("userId", "TQ123");
//        requestParams.put("isbn", "9781449325862");
        //==================================
        requestParams.put("isbn", "978144932586z");
        requestParams.put("title", "как мучить java");
        requestParams.put("subTitle","новая инструкия");
        requestParams.put("author","ichmich");
        requestParams.put("publish_date","2014-12-14T00:00:00.000Z");
        requestParams.put("publisher" ,"No Starch Press");
        requestParams.put("description", "JavaScript");
        requestParams.put("website", "http://eloquentjavascript.net/");
        request.header("Content-Type","application/json");
        request.body(requestParams.toJSONString());
       Response response =request.post("BookStore/v1/Books");
        System.out.println("response status line = "+response.statusLine());
    }
    @Test
    public void createUserReqresIn() {
        JSONObject requestBody = new JSONObject();
        requestBody.put("email", "eve.holt@reqres.in");
        requestBody.put("password", "cityslicka");
        RequestSpecification request = given()
                .baseUri("https://reqres.in/")
                .basePath("api/loging");
        request.header("Content-Type","application/json");
        request.body(requestBody.toJSONString());
        Response response = request.post();
        String stausLine =response.getStatusLine();
        System.out.println("status line = "+stausLine);
        ResponseBody body = response.body();
        String str = body.asPrettyString();
        System.out.println(str);
    }
}
