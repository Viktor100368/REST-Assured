package toolsQA.jsonDeSerialization;

public class UnSuccessRegistration {
    private String error;

    public UnSuccessRegistration(String error) {
        this.error = error;
    }

    public UnSuccessRegistration() {
    }

    public String getError() {
        return error;
    }

    @Override
    public String toString() {
        return "UnSuccessRegistration{" +
                "error='" + error + '\'' +
                '}';
    }
}
