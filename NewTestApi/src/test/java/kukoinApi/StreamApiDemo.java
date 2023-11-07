package kukoinApi;

import io.restassured.http.ContentType;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class StreamApiDemo {
    public List<NewTickerData> getTickers(){
       return given().contentType(ContentType.JSON)
                .when()
                .get("https://api.kucoin.com/api/v1/market/allTickers")
                .then()//.log().body()
                .extract().jsonPath().getList("data.ticker",NewTickerData.class);
    }
   @Test
    public void checkCrypto() {
//       List<NewTickerData> usdList = given().contentType(ContentType.JSON)
//                       .when()
//                       .get("https://api.kucoin.com/api/v1/market/allTickers")
//                       .then()
//                       .extract().jsonPath().getList("data.ticker",NewTickerData.class)
    List<NewTickerData> usdList = getTickers().stream()
               .filter(st -> st.getSymbol().endsWith("USDT")).toList();
       Assert.assertTrue(usdList.stream().allMatch(x->x.getSymbol().endsWith("USDT")));
      // int a=0;
       //System.out.println(usdList);
   }
   @Test
    public void sortLowToHigh(){
       List<NewTickerData> badlist = given().contentType(ContentType.JSON)
                .when()
                .get("https://api.kucoin.com/api/v1/market/allTickers")
                .then()
                .extract().jsonPath().getList("data.ticker",NewTickerData.class)
                .stream()
               .filter(x->x.getSymbol().endsWith("USDT"))
               .sorted((o1, o2) -> {
                    float result = Float.compare(Float.valueOf(o1.getChangeRate()),Float.valueOf(o2.getChangeRate()));
                    return (int) result;
                }).limit(10).collect(Collectors.toList());
       int i = 0;
   }
   @Test
    public void sortHighToLow(){
        List<NewTickerData> list = given().contentType(ContentType.JSON)
                .when()
                .get("https://api.kucoin.com/api/v1/market/allTickers")
                .then()
                .extract().jsonPath().getList("data.ticker",NewTickerData.class)
                .stream().filter(x->x.getSymbol().endsWith("USDT"))
                .sorted((o1,o2)->o2.getChangeRate().compareTo(o1.getChangeRate())).collect(Collectors.toList());
        List<NewTickerData> top10 = list.stream().limit(10).collect(Collectors.toList());
       //System.out.println(top10);
       Assert.assertEquals("MTS-USDT",top10.get(0).getSymbol());
        int i=0;
   }
   @Test
    public void map(){
        List<String> lowerTickers = given().contentType(ContentType.JSON)
                .when()
                .get("https://api.kucoin.com/api/v1/market/allTickers")
                .then()
                .extract().jsonPath().getList("data.ticker",NewTickerData.class)
                .stream().filter(x->x.getSymbol().endsWith("USDT"))
                .map(x->x.getSymbol().toLowerCase()).limit(10).collect(Collectors.toList());

        int i=0;
   }
   @Test
    public void getMap(){
       Map<String,Float> mapTickers = new HashMap<>();
        given().contentType(ContentType.JSON)
               .when()
               .get("https://api.kucoin.com/api/v1/market/allTickers")
               .then()
               .extract().jsonPath().getList("data.ticker",NewTickerData.class)
               .stream().filter(x->x.getSymbol().endsWith("USDT"))
               .sorted((o1, o2) -> o2.getChangeRate().compareTo(o1.getChangeRate()))
               .limit(10)
               .forEach(x->mapTickers.put(x.getSymbol(),Float.parseFloat(x.getChangePrice())));
        int i=0;
   }
   @Test
    public void getShortClass(){
        ArrayList<ShortTicker> shortTickers= new ArrayList<>();
        given().contentType(ContentType.JSON)
                .when()
                .get("https://api.kucoin.com/api/v1/market/allTickers")
                .then()
                .extract().jsonPath().getList("data.ticker",NewTickerData.class)
                .stream().filter(x->x.getSymbol().endsWith("USDT"))
                .sorted((o1,o2)->o2.getChangeRate().compareTo(o1.getChangeRate()))
                .limit(10)
                .forEach(x->shortTickers.add(new ShortTicker(x.getSymbol(),Float.valueOf(x.getChangeRate()))));
        int i=0;
   }
}
