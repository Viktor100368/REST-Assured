package toolsQA;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

public class RestAssuredTestResponse {
    public static final String url = "https://demoqa.com/BookStore/v1/Books";
    @Test
    public void getBooksDetails(){
        RestAssured.baseURI = url;
        RequestSpecification httpRequest = given();
        Response response = httpRequest.get();
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
    }
    @Test
    public void getListUsersReqresIn(){
        RestAssured.baseURI = "https://reqres.in/api/users?page=2";
        RequestSpecification httpRequet = given();
        Response response = httpRequet.get();
        System.out.println("response status line = "+response.getStatusLine());
        System.out.println(response.prettyPrint());
    }
    @Test
    public void getBookHeaders() {
        RestAssured.baseURI = url;
        RequestSpecification httpRequest = given();
        Response response = httpRequest.get();
        Headers headers = response.headers();
        for (Header he : headers) {
            System.out.println(he);
        }
    }
        @Test
    public void getHeader(){
            RestAssured.baseURI = url;
            RequestSpecification httpRequest = given();
            Response response = httpRequest.get();
            String contentType = response.getHeader("Content-Type");
            System.out.println("content-type value is "+contentType);
            String serverType = response.getHeader("Server");
            System.out.println("server-type is "+serverType);
            String connection = response.getHeader("Connection");
            System.out.println("connection is "+connection);

        }
        @Test
    public void validateBookHeaders(){
        RestAssured.baseURI = url;
        RequestSpecification httpRec = given();
        Response response = httpRec.get();
        String contentType = response.getHeader("Content-Type");
        Assert.assertEquals("application/json; charset=utf-8",contentType);
        String connection = response.getHeader("Connection");
        Assert.assertEquals("keep-alive",connection);
        }

        @Test
    public void getBodyAReqresInJson(){
        RestAssured.baseURI="https://reqres.in/api/users";
        RequestSpecification httpReq = given();
        Response response = httpReq.get("?page=2");
            ResponseBody body = response.body();
            String responseBody = body.asString();
            Assert.assertEquals(responseBody.toLowerCase().contains("rachel"),true);
            System.out.println("body = "+ body.asPrettyString());
        }

    }

