package reqresApiTests;

public class UnSuccessRegister {
    private String error;
    UnSuccessRegister(){}
    UnSuccessRegister(String error){
        this.error = error;
    }
    public String getError(){
        return error;
    }
}
