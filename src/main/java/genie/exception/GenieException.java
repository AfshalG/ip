package genie.exception;

/**
 * Represents exceptions specific to Genie chatbot.
 */
public class GenieException extends Exception {

    /**
     * Creates a new GenieException with the specified error message.
     *
     * @param message The error message
     */
    public GenieException(String message) {
        super(message);
    }
}
