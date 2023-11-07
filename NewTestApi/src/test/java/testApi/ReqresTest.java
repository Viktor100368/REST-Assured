package testApi;
import io.restassured.http.ContentType;
import org.junit.Assert;
import org.junit.Test;

import java.time.Clock;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class ReqresTest {
    private final static String URL="https://reqres.in/";
    @Test
    public void checkAvatarAndIdTest(){
        //если мы подключаем спецификацию:
        Specifications.installSpecification(Specifications.requestSpec(URL),Specifications.responseSpecOk200());
        List<UserData> users = given()
                .when()
       //ContentType уже прописан в спецификации, по этому следующую строку можно закоментировать
                //.contentType(ContentType.JSON)
        //URL тоже уже прописан  в спецфикации, следующую строку изменяем
                //.get(URL+"api/users?page=2")
                .get("api/users?page=2")
                .then().log().all()
                .extract().body().jsonPath().getList("data",UserData.class);

        users.forEach(x-> Assert.assertTrue(x.getAvatar().contains(x.getId().toString())));
        Assert.assertTrue(users.stream().allMatch(x->x.getEmail().endsWith("@reqres.in")));

        List<String> avatars = users.stream().map(UserData::getAvatar).collect(Collectors.toList());
        List<String> ids = users.stream().map(x->x.getId().toString()).collect(Collectors.toList());
        for(int i=0;i<users.size();i++){
            Assert.assertTrue(avatars.get(i).contains(ids.get(i)));
        }
    }
    @Test
    public void sussesRegTest(){
        Specifications.installSpecification(Specifications.requestSpec(URL),Specifications.responseSpecOk200());
        Integer id = 4;
        String token = "QpwL5tke4Pnpja7X4";
        Register register = new Register("eve.holt@reqres.in","pistol");
        SuccesReg succesReg = given()
                .body(register)
                .when()
                .post("api/register")
                .then().log().all()
                .extract().as(SuccesReg.class);
        Assert.assertNotNull(succesReg.getId());
        Assert.assertNotNull(succesReg.getToken());
        Assert.assertEquals(id,succesReg.getId());
        Assert.assertEquals(token,succesReg.getToken());
    }
    @Test
    public void noSuccessRegTest(){
        Specifications.installSpecification(Specifications.requestSpec(URL),Specifications.responseSpecError400());
        Register register = new Register("sydney@fife","");
        UnSuccessReg unReg = given()
                .body(register)
                .when()
                .post("api/register")
                .then().log().all()
                .extract().as(UnSuccessReg.class);
        Assert.assertEquals("Missing password",unReg.getError());
    }
    @Test
    public void sortedYearsTest(){
        Specifications.installSpecification(Specifications.requestSpec(URL),Specifications.responseSpecOk200());
        List<ColorsData> data = given()
                .when()
                .get("api/unknown")
                .then().log().all()
                .extract().body().jsonPath().getList("data",ColorsData.class);
        List<Integer> getData = data.stream().map(ColorsData::getYear).collect(Collectors.toList());
        List<Integer> sortData = getData.stream().sorted().collect(Collectors.toList());
        Assert.assertEquals(sortData,getData);

    }
    @Test
    public void deleteCustomerTest(){
        Specifications.installSpecification(Specifications.requestSpec(URL),Specifications.responseSpecUnique(204));
        given()
                .delete("api/users/2\n")
                .then().log().all();
    }
    @Test
    public void getTimeTest(){
        Specifications.installSpecification(Specifications.requestSpec(URL),Specifications.responseSpecOk200());
        UserTime userTime = new UserTime("morpheus","zion resident");
        UserTimeResponce responce = given()
                .body(userTime)
                .when()
                .put("api/users/2")
                .then().log().all()
                .extract().as(UserTimeResponce.class);
        String regex = "(.{5})$";
        String currentTime = Clock.systemUTC().instant().toString().replaceAll(regex,"");
        System.out.println(currentTime);
        Assert.assertEquals(currentTime,responce.getUpdatedAt().replaceAll(regex,""));
        System.out.println(responce.getUpdatedAt().replaceAll(regex,""));
    }



}
