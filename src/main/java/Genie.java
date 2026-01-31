import java.util.Scanner;
import java.util.ArrayList;

public class Genie {
    private static final String LINE = "____________________________________________________________";
    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        showWelcome();
        runCommandLoop(scanner);

        scanner.close();
    }

    /**
     * Displays the welcome message.
     */
    private static void showWelcome() {
        System.out.println(LINE);
        System.out.println(" Hello! I'm Genie");
        System.out.println(" What can I do for you?");
        System.out.println(LINE);
    }

    /**
     * Runs the main command loop.
     *
     * @param scanner The scanner for reading user input
     */
    private static void runCommandLoop(Scanner scanner) {
        while (true) {
            String input = scanner.nextLine();
            System.out.println(LINE);

            if (input.equals("bye")) {
                System.out.println(" Bye. Hope to see you again soon!");
                System.out.println(LINE);
                break;
            }

            handleCommand(input);
        }
    }

    /**
     * Handles a user command.
     *
     * @param input The user input string
     */
    private static void handleCommand(String input) {
        if (input.equals("list")) {
            handleList();
        } else if (input.startsWith("mark ")) {
            handleMark(input);
        } else if (input.startsWith("unmark ")) {
            handleUnmark(input);
        } else if (input.startsWith("todo ")) {
            handleTodo(input);
        } else if (input.startsWith("deadline ")) {
            handleDeadline(input);
        } else if (input.startsWith("event ")) {
            handleEvent(input);
        } else {
            System.out.println(" OOPS!!! I'm sorry, but I don't know what that means :-(");
            System.out.println(LINE);
        }
    }

    /**
     * Handles the list command.
     */
    private static void handleList() {
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.println(" " + (i + 1) + ".["
                + task.getTypeIcon() + "]["
                + task.getStatusIcon() + "] "
                + task.getDescription());
        }
        System.out.println(LINE);
    }

    /**
     * Handles the mark command.
     *
     * @param input The user input string
     */
    private static void handleMark(String input) {
        String[] parts = input.split(" ", 2);
        int taskIndex = Integer.parseInt(parts[1]) - 1;
        tasks.get(taskIndex).markAsDone();
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println("   [" + tasks.get(taskIndex).getStatusIcon()
            + "] " + tasks.get(taskIndex).getDescription());
        System.out.println(LINE);
    }

    /**
     * Handles the unmark command.
     *
     * @param input The user input string
     */
    private static void handleUnmark(String input) {
        String[] parts = input.split(" ", 2);
        int taskIndex = Integer.parseInt(parts[1]) - 1;
        tasks.get(taskIndex).markAsUndone();
        System.out.println(" OK, I've marked this task as not done yet:");
        System.out.println("   [" + tasks.get(taskIndex).getStatusIcon()
            + "] " + tasks.get(taskIndex).getDescription());
        System.out.println(LINE);
    }

    /**
     * Handles the todo command.
     *
     * @param input The user input string
     */
    private static void handleTodo(String input) {
        String description = input.substring(5).trim();
        if (description.isEmpty()) {
            System.out.println(" OOPS!!! The description of a todo cannot be empty.");
            System.out.println(LINE);
            return;
        }
        Todo todo = new Todo(description);
        tasks.add(todo);
        System.out.println(" Got it. I've added this task:");
        System.out.println("   [" + todo.getTypeIcon() + "]["
            + todo.getStatusIcon() + "] " + todo.getDescription());
        System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(LINE);
    }

    /**
     * Handles the deadline command.
     *
     * @param input The user input string
     */
    private static void handleDeadline(String input) {
        String content = input.substring(9).trim();
        int byIndex = content.indexOf(" /by ");
        if (byIndex == -1) {
            System.out.println(" OOPS!!! Please specify a deadline using /by.");
            System.out.println(LINE);
            return;
        }
        String description = content.substring(0, byIndex).trim();
        String by = content.substring(byIndex + 5).trim();

        if (description.isEmpty() || by.isEmpty()) {
            System.out.println(" OOPS!!! Description and deadline cannot be empty.");
            System.out.println(LINE);
            return;
        }

        Deadline deadline = new Deadline(description, by);
        tasks.add(deadline);
        System.out.println(" Got it. I've added this task:");
        System.out.println("   [" + deadline.getTypeIcon() + "]["
            + deadline.getStatusIcon() + "] " + deadline.getDescription());
        System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(LINE);
    }

    /**
     * Handles the event command.
     *
     * @param input The user input string
     */
    private static void handleEvent(String input) {
        String content = input.substring(6).trim();
        int fromIndex = content.indexOf(" /from ");
        int toIndex = content.indexOf(" /to ");

        if (fromIndex == -1 || toIndex == -1) {
            System.out.println(" OOPS!!! Please specify event timing using /from and /to.");
            System.out.println(LINE);
            return;
        }

        String description = content.substring(0, fromIndex).trim();
        String from = content.substring(fromIndex + 7, toIndex).trim();
        String to = content.substring(toIndex + 5).trim();

        if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
            System.out.println(" OOPS!!! Description and timing cannot be empty.");
            System.out.println(LINE);
            return;
        }

        Event event = new Event(description, from, to);
        tasks.add(event);
        System.out.println(" Got it. I've added this task:");
        System.out.println("   [" + event.getTypeIcon() + "]["
            + event.getStatusIcon() + "] " + event.getDescription());
        System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(LINE);
    }
}
