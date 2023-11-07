package toolsQA;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Assert;
import org.junit.Test;


import static io.restassured.RestAssured.given;

public class WorkingWithGetQueryParametrs {



        static final String url = "https://reqres.in";
        static final String path = "/api/users/";
        RequestSpecification reqSpec = new RequestSpecBuilder()
                .setBaseUri("https://reqres.in")
                .setBasePath("/api/users/")
                .setContentType(ContentType.JSON)
                .build();

        ResponseSpecification respSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();





        @Test
        public void readingHeadersFromResponse() {
            RequestSpecification request = given();
            Response response = request.get(url + path + "page=2");
            Headers headers = response.headers();
            for (Header head : headers) {
                System.out.println(head);
            }
            String content_type = response.getHeader("Content-Type");
            System.out.println("content-type value = " + content_type);
            Assert.assertEquals("asserting ", "application/json; charset=utf-8", content_type);
            String connection = response.header("Connection");
            System.out.println("connection value = " + connection);
        }

        @Test
        public void readBodyFromResponse() {
            RequestSpecification request = given()
                    .baseUri(url)
                    .basePath(path)
                    .param("page", "2");
            Response response = request.get();
            ResponseBody body = response.body();
            String bodyString = body.asString();
            Assert.assertEquals(true, bodyString.toLowerCase().contains("byron"));
            System.out.println("response body = " + body.asPrettyString());
        }

        @Test
        public void getJsonPathFromResponse() {
            RequestSpecification httpRequest = given()
                    .baseUri(url)
                    .basePath(path)
                    .param("page", 2);
            Response response = httpRequest.get();
            JsonPath jsonPath = response.jsonPath();
            String page = jsonPath.getString("data[3].email");
            System.out.println("page = " + page);
            Assert.assertTrue("assert with reqres.in", page.endsWith("reqres.in"));
            System.out.println("page = " + jsonPath.getString("page"));
            System.out.println("per_page = " + jsonPath.getString("per-page"));
            System.out.println("total = " + jsonPath.getString("total"));
            System.out.println("total_pages = " + jsonPath.getString("total_pages"));
            System.out.println("data[1] = " + jsonPath.getString("data[1]"));
            System.out.println("data[2] = " + jsonPath.getString("data[2]"));
            System.out.println("data[2].firstName = " + jsonPath.getString("data[2].first_name"));

        }
        @Test
    public void getValuesFromBooksStore(){
            RestAssured.baseURI = "https://demoqa.com/BookStore/v1/Book"
                    ;
            RequestSpecification req =given();
//                    .baseUri("https://demoqa.com/")
//                    .basePath("BookStore/v1/Book")
//                    .param("ISBN","9781449325862");//можно так
            Response response = req.queryParam("ISBN","9781449325862").get();//а можно так
            ResponseBody body = response.body();
            String res = body.asPrettyString();
            //System.out.println(res);
           // JsonPath jsonPath =response.jsonPath();//можно так
            JsonPath jsonPath = new JsonPath(res);// а можно и так
            String out = jsonPath.getString("description");
            System.out.println(out);

        }
}
