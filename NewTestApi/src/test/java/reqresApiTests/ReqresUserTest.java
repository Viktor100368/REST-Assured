package reqresApiTests;

import com.sun.source.tree.AssertTree;
import io.restassured.http.ContentType;
import org.checkerframework.checker.units.qual.C;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;


public class ReqresUserTest {
    private static final String URL = "https://reqres.in";

    @Test
    public void getUserList(){
        List<UserData> list = given().contentType(ContentType.JSON)
                .when()
                .get(URL+"/api/users?page=2")
                .then()
                .extract().body().jsonPath().getList("data",UserData.class)
                .stream().collect(Collectors.toList());
        int a=0;
    }
    @Test
    public void checkAvatarToContainsId(){
        given().contentType(ContentType.JSON)
                .when()
                .get(URL+"/api/users?page=2")
                .then()
                .extract().body().jsonPath().getList("data",UserData.class)
                .forEach(el->Assert.assertTrue(el.getAvatar().contains(el.getId().toString())));
    }
    @Test
    public void checkEmail(){
        List<UserData> ud =given().contentType(ContentType.JSON)
                .when()
                .get(URL+"/api/users?page=2")
                .then()
                .extract().body().jsonPath().getList("data",UserData.class)
                .stream().collect(Collectors.toList());
        Assert.assertTrue(ud.stream().allMatch(el->el.getEmail().endsWith("reqres.in")));

    }
    @Test
    public void succesRegister(){
        Integer id = 4;
        String token = "QpwL5tke4Pnpja7X4";
       SuccesRegisterData registerData = given().contentType(ContentType.JSON)
                .baseUri(URL)
                .basePath("/api/register")
                .when()
                .body("{\"email\":\"eve.holt@reqres.in\",\"password\": \"pistol\"}")
                .post()
                .then()
                .statusCode(200)
                .extract().as(SuccesRegisterData.class);
       Assert.assertNotNull(registerData.getId());
       Assert.assertNotNull(registerData.getToken());
       Assert.assertEquals(id,registerData.getId());
       Assert.assertEquals(token,registerData.getToken());

    }
    @Test
    public void unSuccesRegister(){
        String error = "Missing password";
        UnSuccessRegister unRegister = given().contentType(ContentType.JSON)
                .baseUri(URL)
                .basePath("/api/register")
                .when()
                .body("{\"email\": \"sydney@fife\"}")
                .post()
                .then()
                .statusCode(400)
                .extract().as(UnSuccessRegister.class);
        Assert.assertNotNull(unRegister.getError());
        Assert.assertEquals(error,unRegister.getError());

    }
    @Test
    public void checkListResourse(){
        List<ListResourse> list = given().contentType(ContentType.JSON)
                .baseUri(URL)
                .basePath("/api/unknown")
                .when()
                .get()
                .then()
                .extract().body().jsonPath().getList("data",ListResourse.class);
        List<Integer> dataList = list.stream().map(el->(el.getYear())).collect(Collectors.toList());
        List<Integer> sortList = dataList.stream().sorted().collect(Collectors.toList());
        Assert.assertEquals(dataList,sortList);
        int a=0;
    }
    @Test
    public void deleteTwoUser(){
        given().contentType(ContentType.JSON)
                .baseUri(URL)
                .basePath("/api/users/2")
                .when()
                .delete()
                .then()
                .statusCode(204);
    }

}
