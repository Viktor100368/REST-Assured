package toolsQA;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;


public class RestAssuredAPITest {
    private String url = "https://demoqa.com/BookStore/v1/Books";
    @Test
    public void getBooksDetails(){
        RestAssured.baseURI="https://demoqa.com/BookStore/v1/Books";
        RequestSpecification httpRequest = given();
        Response response = httpRequest.request(Method.GET,"");
        int statusCode = response.statusCode();
        Assert.assertEquals(200,statusCode);
        System.out.println("status received -> "+response.getStatusLine());
        System.out.println("response -> "+response.prettyPrint());
    }
    @Test
    public void getBooksDetails2(){
        List<Books> booksList =given()
                .contentType(ContentType.JSON)
                .baseUri(url)
                .when()
                .get()
                .then()//.log().all()
                .extract().body().jsonPath().getList("books", Books.class);
        for(Books el:booksList){
            System.out.println(el.toString());
        }
    }
}
