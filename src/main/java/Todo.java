/**
 * Represents a todo task without any date/time attached.
 */
public class Todo extends Task {

    /**
     * Creates a new todo task with the given description.
     *
     * @param description The task description
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the type icon for todo tasks.
     *
     * @return The string "T"
     */
    @Override
    public String getTypeIcon() {
        return "T";
    }
}
