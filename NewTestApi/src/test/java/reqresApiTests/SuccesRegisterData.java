package reqresApiTests;

public class SuccesRegisterData {
    private Integer id;
    private String token;
    SuccesRegisterData(Integer id, String token){
        this.id = id;
        this.token = token;
    }
    SuccesRegisterData(){}
    public Integer getId(){
        return id;
    }
    public String getToken(){
        return token;
    }

}
