package restAssuredReqresInTest;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;



public class AssuredReqresTest {
    @Test
    public void getListUsers(){
        Response response;
        response = given().contentType(ContentType.JSON)
            .baseUri("https://request.in")
             .basePath("/api/users?page=2")
                .when()
                .get()
                .then()
                .body("page",equalTo(2))
                .body("data.id",notNullValue())
                .extract().response();

    }
}
