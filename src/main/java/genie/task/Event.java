package genie.task;

/**
 * Represents an event task with start and end times.
 */
public class Event extends Task {
    private String from;
    private String to;

    /**
     * Creates a new event task with the given description, start and end times.
     *
     * @param description The event description
     * @param from The start time (as a string)
     * @param to The end time (as a string)
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the type icon for event tasks.
     *
     * @return The string "E"
     */
    @Override
    public String getTypeIcon() {
        return "E";
    }

    /**
     * Returns the description with event timing information.
     *
     * @return Description with "(from: START to: END)" appended
     */
    @Override
    public String getDescription() {
        return super.getDescription() + " (from: " + from + " to: " + to + ")";
    }

    /**
     * Returns a string representation of this event for saving to file.
     * Format: E | STATUS | DESCRIPTION | FROM | TO
     *
     * @return The file string representation
     */
    @Override
    public String toFileString() {
        return super.toFileString() + " | " + from + " | " + to;
    }
}
