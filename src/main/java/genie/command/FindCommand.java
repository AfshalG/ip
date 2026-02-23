package genie.command;

import genie.exception.GenieException;
import genie.storage.Storage;
import genie.task.Task;
import genie.task.TaskList;
import genie.ui.Ui;

/**
 * Command that searches for tasks whose description contains a keyword.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Creates a FindCommand by parsing the input.
     *
     * @param input The full user input string
     * @throws GenieException If no keyword is provided
     */
    public FindCommand(String input) throws GenieException {
        String content = input.substring("find".length()).trim();
        if (content.isEmpty()) {
            throw new GenieException("OOPS!!! Please provide a keyword to search for.");
        }
        keyword = content;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList matching = new TaskList();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.getTask(i);
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                matching.addTask(task);
            }
        }
        ui.showMatchingTasks(matching);
    }
}
