package toolsQA.jsonDeSerialization;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateResponse {

    private String name;
    private String job;
    private Date updatedAt;

    public UpdateResponse(String name, String job, Date updatedAt) {
        this.name = name;
        this.job = job;
        this.updatedAt = updatedAt;
    }

    public UpdateResponse() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "UpdateResponse{" +
                "name='" + name + '\'' +
                ", job='" + job + '\'' +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
