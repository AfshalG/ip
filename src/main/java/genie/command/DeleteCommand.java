package genie.command;

import genie.exception.GenieException;
import genie.storage.Storage;
import genie.task.Task;
import genie.task.TaskList;
import genie.ui.Ui;

/**
 * Command that deletes a task.
 */
public class DeleteCommand extends Command {
    private final int taskIndex;

    /**
     * Creates a DeleteCommand by parsing the input.
     *
     * @param input The full user input string
     * @throws GenieException If the task number is missing or invalid
     */
    public DeleteCommand(String input) throws GenieException {
        String[] parts = input.split(" ", 2);
        if (parts.length < 2) {
            throw new GenieException("OOPS!!! Please specify which task to delete.");
        }
        try {
            taskIndex = Integer.parseInt(parts[1].trim()) - 1;
        } catch (NumberFormatException e) {
            throw new GenieException("OOPS!!! Please provide a valid task number.");
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws GenieException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new GenieException("OOPS!!! Invalid task number. Please use a number from 1 to "
                + tasks.size() + ".");
        }
        Task removed = tasks.deleteTask(taskIndex);
        storage.save(tasks.getTasks());
        ui.showTaskDeleted(removed, tasks.size());
    }
}
