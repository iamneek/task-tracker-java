import java.io.*;
import java.util.ArrayList;

public class TaskManager {
  ArrayList<Task> tasks;
  File csvCheck;

  public TaskManager() throws IOException {
    this.tasks = new ArrayList<>();
    this.csvCheck = new File("data.csv");
    if (csvCheck.exists()) {
      System.out.println("Yes the file exists");
    } else {
      try {
        if (csvCheck.createNewFile()) {
          System.out.println("File did not exist, but now it does.");
        }
      } catch (IOException e) {
        System.out.println("An error occurred while trying to create data file.");
      }
    }

    this.importFromCSV();
  }

  public String addTask(String description) throws IOException {
    Task task = new Task(description);
    tasks.add(task);
    this.exportToCSV();
    return "Task ID: " + task.getId() + " Added Successfully!\n" + "Task: " + task.getDescription();
  }

  public void deleteTask(int id) throws IOException {
    for (Task t : this.tasks) {
      if (t.getId() == id) {
        this.tasks.remove(t);
        this.exportToCSV();
        return;
      }
    }
    System.out.println("Task with " + id + " not found, are you sure Task exists?");
  }

  public void updateTask(int id, String description) throws IOException {
    for (Task t : this.tasks) {
      if (t.getId() == id) {
        t.setDescription(description);
        this.exportToCSV();
        return;
      }
    }
    System.out.println("Task with " + id + " not found, are you sure Task exists?");
  }

  public ArrayList<Task> list(String filter) {
    switch (filter) {
      case "todo" -> {
        ArrayList<Task> todoTasks = new ArrayList<>();
        for (Task t : this.tasks) {
          if (t.getStatus().equals("todo")) {
            todoTasks.add(t);
          }
        }
        return todoTasks;
      }
      case "in-progress" -> {
        ArrayList<Task> todoTasks = new ArrayList<>();
        for (Task t : this.tasks) {
          if (t.getStatus().equals("in-progress")) {
            todoTasks.add(t);
          }
        }
        return todoTasks;
      }
      case "done" -> {
        ArrayList<Task> todoTasks = new ArrayList<>();
        for (Task t : this.tasks) {
          if (t.getStatus().equals("done")) {
            todoTasks.add(t);
          }
        }
        return todoTasks;
      }

      default -> {
        return this.tasks;
      }
    }
  }

  public void updateStatus(int id, String status) throws IOException {
    for (Task t : this.tasks) {
      if (t.getId() == id) {
        t.setStatus(status);
        this.exportToCSV();
        return;
      }
    }
    System.out.println("Task with " + id + " not found, are you sure Task exists?");
  }

  public void exportToCSV() throws IOException {
    BufferedWriter bw = new BufferedWriter(new FileWriter(this.csvCheck, false));
    for (Task t : this.tasks) {
      bw.write(
          t.getId()
              + ","
              + t.getDescription()
              + ","
              + t.getStatus()
              + ","
              + t.getCreatedAt()
              + ","
              + t.getUpdatedAt()
              + "\n");
    }
    bw.close();
  }

  public void importFromCSV() throws IOException {
    BufferedReader bw = new BufferedReader(new FileReader(this.csvCheck));
    String taskData;
    while((taskData = bw.readLine()) != null){
      String[] taskInfo = taskData.split(",");
      int id = Integer.parseInt(taskInfo[0]);
      String description = taskInfo[1];
      String status = taskInfo[2];
      String createdAt = taskInfo[3];
      String updatedAt = taskInfo[4];

      this.tasks.add(new Task(id, description, status, createdAt, updatedAt));
    }
    bw.close();
  }
}
