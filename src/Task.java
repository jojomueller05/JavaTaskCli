import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    enum Status {
        TODO,
        INPROGRESS,
        DONE
    }
    public int id;
    public String description;
    public Status status;
    public LocalDateTime createdAt;
    public LocalDateTime updatedAt;

    public Task(int id, String description){
        this.id = id;
        this.description = description;
        this.status = Status.TODO;

        LocalDateTime now = LocalDateTime.now();

        this.createdAt = now;
        this.updatedAt = now;
    }

    public Task(int id, String description, Status status, LocalDateTime createdAt, LocalDateTime updatedAt){
        this.id = id;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void setDescription(String description){
        this.description = description;

        LocalDateTime now = LocalDateTime.now();

        this.updatedAt = now;
    }

    public void setStatus(Status status) {
        this.status = status;

        LocalDateTime now = LocalDateTime.now();

        this.updatedAt = now;
    }

    public String toString() {
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return id + ". " + description + " |" + status +"|";
    }
}
