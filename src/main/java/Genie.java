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

            try {
                handleCommand(input);
            } catch (GenieException e) {
                System.out.println(" " + e.getMessage());
                System.out.println(LINE);
            }
        }
    }

    /**
     * Handles a user command.
     *
     * @param input The user input string
     * @throws GenieException If the command is invalid or encounters an error
     */
    private static void handleCommand(String input) throws GenieException {
        if (input.equals("list")) {
            handleList();
        } else if (input.startsWith("mark")) {
            handleMark(input);
        } else if (input.startsWith("unmark")) {
            handleUnmark(input);
        } else if (input.startsWith("todo")) {
            handleTodo(input);
        } else if (input.startsWith("deadline")) {
            handleDeadline(input);
        } else if (input.startsWith("event")) {
            handleEvent(input);
        } else {
            throw new GenieException("OOPS!!! I'm sorry, but I don't know what that means :-(");
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
     * @throws GenieException If the task index is invalid or out of range
     */
    private static void handleMark(String input) throws GenieException {
        try {
            String[] parts = input.split(" ", 2);
            if (parts.length < 2) {
                throw new GenieException("OOPS!!! Please specify which task to mark.");
            }
            int taskIndex = Integer.parseInt(parts[1]) - 1;
            if (taskIndex < 0 || taskIndex >= tasks.size()) {
                throw new GenieException("OOPS!!! Invalid task number. Please use a number from 1 to "
                    + tasks.size() + ".");
            }
            Task task = tasks.get(taskIndex);
            if (task.isDone()) {
                throw new GenieException("OOPS!!! This task is already marked as done.");
            }
            task.markAsDone();
            System.out.println(" Nice! I've marked this task as done:");
            System.out.println("   [" + task.getStatusIcon()
                + "] " + task.getDescription());
            System.out.println(LINE);
        } catch (NumberFormatException e) {
            throw new GenieException("OOPS!!! Please provide a valid task number.");
        }
    }

    /**
     * Handles the unmark command.
     *
     * @param input The user input string
     * @throws GenieException If the task index is invalid or out of range
     */
    private static void handleUnmark(String input) throws GenieException {
        try {
            String[] parts = input.split(" ", 2);
            if (parts.length < 2) {
                throw new GenieException("OOPS!!! Please specify which task to unmark.");
            }
            int taskIndex = Integer.parseInt(parts[1]) - 1;
            if (taskIndex < 0 || taskIndex >= tasks.size()) {
                throw new GenieException("OOPS!!! Invalid task number. Please use a number from 1 to "
                    + tasks.size() + ".");
            }
            Task task = tasks.get(taskIndex);
            if (!task.isDone()) {
                throw new GenieException("OOPS!!! This task is already unmarked.");
            }
            task.markAsUndone();
            System.out.println(" OK, I've marked this task as not done yet:");
            System.out.println("   [" + task.getStatusIcon()
                + "] " + task.getDescription());
            System.out.println(LINE);
        } catch (NumberFormatException e) {
            throw new GenieException("OOPS!!! Please provide a valid task number.");
        }
    }

    /**
     * Handles the todo command.
     *
     * @param input The user input string
     * @throws GenieException If the todo description is empty
     */
    private static void handleTodo(String input) throws GenieException {
        if (input.length() <= 4) {
            throw new GenieException("OOPS!!! The description of a todo cannot be empty.");
        }
        String description = input.substring(4).trim();
        if (description.isEmpty()) {
            throw new GenieException("OOPS!!! The description of a todo cannot be empty.");
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
     * @throws GenieException If the deadline format is invalid or fields are empty
     */
    private static void handleDeadline(String input) throws GenieException {
        if (input.length() <= 8) {
            throw new GenieException("OOPS!!! The description of a deadline cannot be empty.");
        }
        String content = input.substring(8).trim();
        int byIndex = content.indexOf(" /by ");
        if (byIndex == -1) {
            throw new GenieException("OOPS!!! Please specify a deadline using /by.");
        }
        String description = content.substring(0, byIndex).trim();
        String by = content.substring(byIndex + 5).trim();

        if (description.isEmpty() || by.isEmpty()) {
            throw new GenieException("OOPS!!! Description and deadline cannot be empty.");
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
     * @throws GenieException If the event format is invalid or fields are empty
     */
    private static void handleEvent(String input) throws GenieException {
        if (input.length() <= 5) {
            throw new GenieException("OOPS!!! The description of an event cannot be empty.");
        }
        String content = input.substring(5).trim();
        int fromIndex = content.indexOf(" /from ");
        int toIndex = content.indexOf(" /to ");

        if (fromIndex == -1 || toIndex == -1) {
            throw new GenieException("OOPS!!! Please specify event timing using /from and /to.");
        }

        String description = content.substring(0, fromIndex).trim();
        String from = content.substring(fromIndex + 7, toIndex).trim();
        String to = content.substring(toIndex + 5).trim();

        if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
            throw new GenieException("OOPS!!! Description and timing cannot be empty.");
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
