import java.util.Scanner;
import java.util.ArrayList;

public class Genie {
    private static ArrayList<Task> tasks = new ArrayList<>();
    public static void main(String[] args) {
        String line = "____________________________________________________________";
        Scanner scanner = new Scanner(System.in);

        // Print greeting
        System.out.println(line);
        System.out.println(" Hello! I'm Genie");
        System.out.println(" What can I do for you?");
        System.out.println(line);
        // Input loop
        while (true) {
            String input = scanner.nextLine();
            System.out.println(line);

            if (input.equals("bye")) {
                System.out.println(" Bye. Hope to see you again soon!");
                System.out.println(line);
                break;
            } else if (input.equals("list")) {
                System.out.println(" Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    Task task = tasks.get(i);
                    System.out.println(" " + (i + 1) + ".["
                        + task.getStatusIcon() + "] "
                        + task.getDescription());
                }
                System.out.println(line);
            } else if (input.startsWith("mark ")) {
                String[] parts = input.split(" ", 2);
                int taskIndex = Integer.parseInt(parts[1]) - 1;
                tasks.get(taskIndex).markAsDone();
                System.out.println(" Nice! I've marked this task as done:");
                System.out.println("   [" + tasks.get(taskIndex).getStatusIcon()
                    + "] " + tasks.get(taskIndex).getDescription());
                System.out.println(line);
            } else if (input.startsWith("unmark ")) {
                String[] parts = input.split(" ", 2);
                int taskIndex = Integer.parseInt(parts[1]) - 1;
                tasks.get(taskIndex).markAsUndone();
                System.out.println(" OK, I've marked this task as not done yet:");
                System.out.println("   [" + tasks.get(taskIndex).getStatusIcon()
                    + "] " + tasks.get(taskIndex).getDescription());
                System.out.println(line);
            } else {
                // Add task
                Task task = new Task(input);
                tasks.add(task);
                System.out.println(" added: " + input);
                System.out.println(line);
            }
        }

        scanner.close();
    }
}



