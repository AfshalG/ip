package genie.task;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
    private String by;

    /**
     * Creates a new deadline task with the given description and deadline.
     *
     * @param description The task description
     * @param by The deadline (as a string)
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the type icon for deadline tasks.
     *
     * @return The string "D"
     */
    @Override
    public String getTypeIcon() {
        return "D";
    }

    /**
     * Returns the description with deadline information.
     *
     * @return Description with "(by: DATE)" appended
     */
    @Override
    public String getDescription() {
        return super.getDescription() + " (by: " + by + ")";
    }

    /**
     * Returns a string representation of this deadline for saving to file.
     * Format: D | STATUS | DESCRIPTION | BY
     *
     * @return The file string representation
     */
    @Override
    public String toFileString() {
        return super.toFileString() + " | " + by;
    }
}
