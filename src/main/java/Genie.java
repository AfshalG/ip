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
            System.out.println();
            String input = scanner.nextLine();
            System.out.println(line);

            if (input.equals("bye")) {
                System.out.println(" Bye. Hope to see you again soon!");
                System.out.println(line);
                break;
            } else if (input.equals("list")) {
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(" " + (i + 1) + ". " + tasks.get(i).getDescription());
                }
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

