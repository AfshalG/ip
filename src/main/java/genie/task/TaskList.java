package genie.task;

import java.util.ArrayList;

/**
 * Represents the list of tasks, with operations to add, delete, and access tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Creates a TaskList from an existing list of tasks.
     *
     * @param tasks The initial list of tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Creates an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to add
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes and returns the task at the given index.
     *
     * @param index Zero-based index of the task to remove
     * @return The removed task
     */
    public Task deleteTask(int index) {
        return tasks.remove(index);
    }

    /**
     * Returns the task at the given index.
     *
     * @param index Zero-based index of the task
     * @return The task at that index
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The size of the task list
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the underlying ArrayList of tasks (for storage purposes).
     *
     * @return The list of tasks
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
