package toolsQA.jsonDeSerialization;

public class SuccessRegistrationResponse {
    private Integer id;
    private String token;

    public SuccessRegistrationResponse() {
    }

    public SuccessRegistrationResponse(Integer id, String token) {
        this.id = id;
        this.token = token;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "JsonSuccessResponse{" +
                "id=" + id +
                ", token='" + token + '\'' +
                '}';
    }
}
