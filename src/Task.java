import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
  private static int global_id;
  private int id = ++global_id;
  private String description;
  private String Status = "todo"; // in-progress done
  DateTimeFormatter formattedDateTime = DateTimeFormatter.ofPattern("dd-MM-yyy @ hh:mm a");
  private final LocalDateTime TimeNow = LocalDateTime.now();
  private String createdAt = TimeNow.format(formattedDateTime);
  private String updatedAt = TimeNow.format(formattedDateTime);

  //  ---

  public Task(String description) {
    this.description = description;
  }

  public Task(int id, String description, String status, String createdAt, String updatedAt) {
    this.id = id;
    if (global_id < this.id) {
      global_id = this.id;
    }

    this.description = description;
    this.Status = status;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  //  ---

  public String getStatus() {
    return Status;
  }

  public void updateTime() {
    LocalDateTime currentTime = LocalDateTime.now();
    this.updatedAt = currentTime.format(formattedDateTime);
  }

  public void setStatus(String status) {
    Status = status;
    this.updateTime();
  }

  public int getId() {
    return this.id;
  }

  public String getCreatedAt() {
    return this.createdAt;
  }

  public String getUpdatedAt() {
    return this.updatedAt;
  }

  public void setDescription(String description) {
    this.description = description;
    this.updateTime();
  }

  public String getDescription() {
    return this.description;
  }

  @Override
  public String toString() {
    return "\n"
        + this.getId()
        + ". "
        + this.getDescription()
        + "\nStatus: "
        + this.getStatus()
        + "\n---";
  }
}
