package genie.command;

import genie.exception.GenieException;
import genie.storage.Storage;
import genie.task.Event;
import genie.task.TaskList;
import genie.ui.Ui;

/**
 * Command that adds an event task.
 */
public class AddEventCommand extends Command {
    private static final String FROM_SEPARATOR = " /from ";
    private static final String TO_SEPARATOR = " /to ";
    private final String description;
    private final String from;
    private final String to;

    /**
     * Creates an AddEventCommand by parsing the input.
     *
     * @param input The full user input string
     * @throws GenieException If the format is invalid or fields are empty
     */
    public AddEventCommand(String input) throws GenieException {
        String content = input.substring("event".length()).trim();
        if (content.isEmpty()) {
            throw new GenieException("OOPS!!! The description of an event cannot be empty.");
        }
        int fromIndex = content.indexOf(FROM_SEPARATOR);
        int toIndex = content.indexOf(TO_SEPARATOR);
        if (fromIndex == -1 || toIndex == -1) {
            throw new GenieException("OOPS!!! Please specify event timing using /from and /to.");
        }
        description = content.substring(0, fromIndex).trim();
        from = content.substring(fromIndex + FROM_SEPARATOR.length(), toIndex).trim();
        to = content.substring(toIndex + TO_SEPARATOR.length()).trim();
        if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
            throw new GenieException("OOPS!!! Description and timing cannot be empty.");
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Event event = new Event(description, from, to);
        tasks.addTask(event);
        storage.save(tasks.getTasks());
        ui.showTaskAdded(event, tasks.size());
    }
}
