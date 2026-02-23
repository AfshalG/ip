package genie.parser;

import genie.command.AddDeadlineCommand;
import genie.command.AddEventCommand;
import genie.command.AddTodoCommand;
import genie.command.ByeCommand;
import genie.command.Command;
import genie.command.DeleteCommand;
import genie.command.FindCommand;
import genie.command.ListCommand;
import genie.command.MarkCommand;
import genie.command.UnmarkCommand;
import genie.exception.GenieException;

/**
 * Parses user input into executable Command objects.
 */
public class Parser {

    /**
     * Parses the given input string and returns the corresponding Command.
     *
     * @param input The full user input string
     * @return The Command corresponding to the input
     * @throws GenieException If the command is unknown or badly formed
     */
    public static Command parse(String input) throws GenieException {
        if (input.equals("bye")) {
            return new ByeCommand();
        } else if (input.equals("list")) {
            return new ListCommand();
        } else if (input.startsWith("mark")) {
            return new MarkCommand(input);
        } else if (input.startsWith("unmark")) {
            return new UnmarkCommand(input);
        } else if (input.startsWith("todo")) {
            return new AddTodoCommand(input);
        } else if (input.startsWith("deadline")) {
            return new AddDeadlineCommand(input);
        } else if (input.startsWith("event")) {
            return new AddEventCommand(input);
        } else if (input.startsWith("delete")) {
            return new DeleteCommand(input);
        } else if (input.startsWith("find")) {
            return new FindCommand(input);
        } else {
            throw new GenieException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
