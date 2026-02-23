package genie.ui;

import java.util.Scanner;

import genie.task.Task;
import genie.task.TaskList;

/**
 * Handles all interactions with the user.
 */
public class Ui {
    private static final String LINE = "____________________________________________________________";
    private final Scanner scanner;

    /**
     * Creates a new Ui instance.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Reads the next command from the user.
     *
     * @return The full input line from the user
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays the divider line.
     */
    public void showLine() {
        System.out.println(LINE);
    }

    /**
     * Displays the welcome message.
     */
    public void showWelcome() {
        System.out.println(LINE);
        System.out.println(" Hello! I'm Genie");
        System.out.println(" What can I do for you?");
        System.out.println(LINE);
    }

    /**
     * Displays the goodbye message.
     */
    public void showBye() {
        System.out.println(" Bye. Hope to see you again soon!");
    }

    /**
     * Displays an error message.
     *
     * @param message The error message to show
     */
    public void showError(String message) {
        System.out.println(" " + message);
    }

    /**
     * Displays the loading error message when tasks cannot be loaded.
     */
    public void showLoadingError() {
        System.out.println(" [Warning] Could not load tasks. Starting with empty list.");
    }

    /**
     * Displays all tasks in the list.
     *
     * @param tasks The task list to display
     */
    public void showTaskList(TaskList tasks) {
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.getTask(i);
            System.out.println(" " + (i + 1) + ".["
                + task.getTypeIcon() + "]["
                + task.getStatusIcon() + "] "
                + task.getDescription());
        }
    }

    /**
     * Displays a confirmation that a task was added.
     *
     * @param task      The task that was added
     * @param totalSize The new total number of tasks
     */
    public void showTaskAdded(Task task, int totalSize) {
        System.out.println(" Got it. I've added this task:");
        System.out.println("   [" + task.getTypeIcon() + "]["
            + task.getStatusIcon() + "] " + task.getDescription());
        System.out.println(" Now you have " + totalSize + " tasks in the list.");
    }

    /**
     * Displays a confirmation that a task was marked as done.
     *
     * @param task The task that was marked
     */
    public void showTaskMarked(Task task) {
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println("   [" + task.getStatusIcon()
            + "] " + task.getDescription());
    }

    /**
     * Displays a confirmation that a task was marked as not done.
     *
     * @param task The task that was unmarked
     */
    public void showTaskUnmarked(Task task) {
        System.out.println(" OK, I've marked this task as not done yet:");
        System.out.println("   [" + task.getStatusIcon()
            + "] " + task.getDescription());
    }

    /**
     * Displays a confirmation that a task was deleted.
     *
     * @param task      The task that was removed
     * @param totalSize The new total number of tasks
     */
    public void showTaskDeleted(Task task, int totalSize) {
        System.out.println(" Noted. I've removed this task:");
        System.out.println("   [" + task.getTypeIcon() + "]["
            + task.getStatusIcon() + "] " + task.getDescription());
        System.out.println(" Now you have " + totalSize + " tasks in the list.");
    }

    /**
     * Closes the scanner.
     */
    public void close() {
        scanner.close();
    }
}
