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
        this.isDone = false;
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
        this.isDone = true;
    }

    /**
     * Marks this task as not done.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns the type icon of this task.
     *
     * @return The type icon (default "?")
     */
    public String getTypeIcon() {
        return "?";
    }
}
