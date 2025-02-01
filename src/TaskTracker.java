import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class TaskTracker {
  public static void main(String[] args) throws IOException {

    Scanner scanner = new Scanner(System.in);

    TaskManager taskManager = new TaskManager();

    String helpMenu =
        """

Add item to List -> add Bring something
Delete item from List -> delete <task_id>
Update items in List -> update <task_id> Bring nothing.
Change task status -> mark <task_id> <options> [options: todo, in-progress, done]

List all items -> list
List items with todo status -> list todo
List items with in-progress status -> list in-progress
List completed tasks -> list done

Exit Program -> exit
To bring up this menu -> help
""";

    System.out.println("--- Welcome to Task Tracker ---");
    System.out.println(helpMenu);

    while (true) {
      System.out.print("Task Tracker > ");
      String input = scanner.nextLine();
      String[] inputPieces = input.split(" ");
      String command = inputPieces[0];
      switch (command) {
        case "exit" -> System.exit(0);

        case "help" -> System.out.println(helpMenu);

        case "add" -> {
          StringBuilder taskDescription = new StringBuilder();
          for (int i = 1; i < inputPieces.length; i++) {
            taskDescription.append(inputPieces[i]).append(" ");
          }
          System.out.println(taskManager.addTask(taskDescription.toString()));
        }

        case "update" -> {
          if (!(inputPieces.length > 3)) {
            System.out.println("Usage: update <task_id> New Description for Task");
          } else {
            int id = Integer.parseInt(inputPieces[1]);
            StringBuilder newDescription = new StringBuilder();
            for (int i = 2; i < inputPieces.length; i++) {
              newDescription.append(inputPieces[i]).append(" ");
            }
            taskManager.updateTask(id, newDescription.toString());
          }
        }

        case "delete" -> {
          if (inputPieces.length < 2) {
            System.out.println("Usage: delete <task_id>");
          } else {
            int id = Integer.parseInt(inputPieces[1]);
            taskManager.deleteTask(id);
          }
        }

        case "mark" -> {
          if (!(inputPieces.length > 2)) {
            System.out.println(
                "Usage: mark <task_id> <options> [options: todo, in-progress, done]");
          } else {
            int id = Integer.parseInt(inputPieces[1]);
            String status = inputPieces[2];
            if (status.equals("in-progress") || status.equals("done") || status.equals("todo")) {
              taskManager.updateStatus(id, status);
            } else {
              System.out.println(
                  "Usage: mark <task_id> <options> [options: todo, in-progress, done]");
            }
          }
        }

        case "list" -> {
          if (inputPieces.length == 1) {
            ArrayList<Task> taskList = taskManager.list("all");
            for (Task t : taskList) {
              System.out.println(t);
            }
          }

          else{
            String status = inputPieces[1];
            if (status.equals("in-progress") || status.equals("done") || status.equals("todo")) {
              ArrayList<Task> taskList = taskManager.list(inputPieces[1]);
              for (Task t : taskList) {
                System.out.println(t);
              }
            } else {
              System.out.println("Usage: list or list <options> [options: , todo, in-progress, done]");
            }
          }
        }
      }
    }
  }
}
