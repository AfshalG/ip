package genie.task;

/**
 * Represents a task with a description and completion status.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a new task with the given description.
     * Task is initially marked as not done.
     */
    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    /**
     * Returns the description of this task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the status icon of this task.
     * return "X" if task is done, " " otherwise
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns whether this task is done.
     *
     * @return true if task is done, false otherwise
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Marks this task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks this task as not done.
     */
    public void markAsUndone() {
        isDone = false;
    }

    /**
     * Returns the type icon of this task.
     *
     * @return The type icon (default "?")
     */
    public String getTypeIcon() {
        return "?";
    }

    /**
     * Returns a string representation of this task for saving to file.
     * Format: TYPE | STATUS | DESCRIPTION
     *
     * @return The file string representation
     */
    public String toFileString() {
        return getTypeIcon() + " | " + (isDone ? "1" : "0") + " | " + description;
    }
}
