package kuCoinTest;


import io.restassured.http.ContentType;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class StreamApiExampleTest {
    public List<TickerData> getTickers(){
        return given()
                .contentType(ContentType.JSON)
                .when()
                .get("https://api.kucoin.com/api/v1/market/allTickers")
                .then()
                .extract().jsonPath().getList("data.ticker",TickerData.class);
    }

@Test
    public void checkCrypto(){
        List<TickerData> usdTickers = getTickers().stream().filter(name->name.getSymbol().endsWith("USDT")).collect(Collectors.toList());
    Assert.assertTrue(usdTickers.stream().allMatch(name->name.getSymbol().endsWith("USDT")));
        int i= 0;
}
@Test
    public void sortHighToLow(){
        List<TickerData> sortTickers = getTickers().stream().filter(x->x.getSymbol().endsWith("USDT"))
                .sorted((o1,o2)->o2.getChangeRate().compareTo(o1.getChangeRate())).collect(Collectors.toList());
        List<TickerData> top10 = sortTickers.stream().limit(10).collect(Collectors.toList());
        Assert.assertEquals("GOAL-USDT",top10.get(0).getSymbol());
        int i=0;
}
@Test
    public void sortLowToHigh(){
        List<TickerData> lowToHigh = getTickers().stream().filter(x->x.getSymbol().endsWith("USDT"))
                .sorted(new TickerComparatorLow()).limit(10).collect(Collectors.toList());
        int i = 0;
}
@Test
    public void map(){
        Map<String,Float> usd = new HashMap<>() {};
        List<String> lowerCases = getTickers().stream().map(x->x.getSymbol().toLowerCase()).limit(10).collect(Collectors.toList());
        getTickers().forEach(x->usd.put(x.getSymbol(),Float.parseFloat(x.getChangeRate())));
        int i= 0 ;
}
@Test
    public void mapFromClass(){
        List<TickerShort> shortList = new ArrayList<>();
        getTickers().forEach(x->shortList.add(new TickerShort(x.getSymbol(),Float.parseFloat(x.getChangeRate()))));
        int i=0;
}




}
