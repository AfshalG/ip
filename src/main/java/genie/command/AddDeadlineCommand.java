package genie.command;

import genie.exception.GenieException;
import genie.storage.Storage;
import genie.task.Deadline;
import genie.task.TaskList;
import genie.ui.Ui;

/**
 * Command that adds a deadline task.
 */
public class AddDeadlineCommand extends Command {
    private static final String BY_SEPARATOR = " /by ";
    private final String description;
    private final String by;

    /**
     * Creates an AddDeadlineCommand by parsing the input.
     *
     * @param input The full user input string
     * @throws GenieException If the format is invalid or fields are empty
     */
    public AddDeadlineCommand(String input) throws GenieException {
        String content = input.substring("deadline".length()).trim();
        if (content.isEmpty()) {
            throw new GenieException("OOPS!!! The description of a deadline cannot be empty.");
        }
        int byIndex = content.indexOf(BY_SEPARATOR);
        if (byIndex == -1) {
            throw new GenieException("OOPS!!! Please specify a deadline using /by.");
        }
        description = content.substring(0, byIndex).trim();
        by = content.substring(byIndex + BY_SEPARATOR.length()).trim();
        if (description.isEmpty() || by.isEmpty()) {
            throw new GenieException("OOPS!!! Description and deadline cannot be empty.");
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Deadline deadline = new Deadline(description, by);
        tasks.addTask(deadline);
        storage.save(tasks.getTasks());
        ui.showTaskAdded(deadline, tasks.size());
    }
}
