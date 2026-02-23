package genie.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a deadline date.
 */
public class Deadline extends Task {
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy");

    private final LocalDate by;

    /**
     * Creates a new deadline task with the given description and deadline date string.
     * Accepts date in yyyy-MM-dd format (e.g., 2019-12-01).
     *
     * @param description The task description
     * @param by          The deadline date string in yyyy-MM-dd format
     * @throws DateTimeParseException If the date string is not in the expected format
     */
    public Deadline(String description, String by) throws DateTimeParseException {
        super(description);
        this.by = LocalDate.parse(by, INPUT_FORMAT);
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
     * Returns the description with the deadline date formatted for display.
     *
     * @return Description with "(by: MMM dd yyyy)" appended
     */
    @Override
    public String getDescription() {
        return super.getDescription() + " (by: " + by.format(OUTPUT_FORMAT) + ")";
    }

    /**
     * Returns a string representation of this deadline for saving to file.
     * Saves the date in yyyy-MM-dd format so it can be re-parsed on load.
     * Format: D | STATUS | DESCRIPTION | yyyy-MM-dd
     *
     * @return The file string representation
     */
    @Override
    public String toFileString() {
        return super.toFileString() + " | " + by.format(INPUT_FORMAT);
    }
}
