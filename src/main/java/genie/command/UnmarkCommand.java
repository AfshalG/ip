package genie.command;

import genie.exception.GenieException;
import genie.storage.Storage;
import genie.task.Task;
import genie.task.TaskList;
import genie.ui.Ui;

/**
 * Command that marks a task as not done.
 */
public class UnmarkCommand extends Command {
    private final int taskIndex;

    /**
     * Creates an UnmarkCommand by parsing the input.
     *
     * @param input The full user input string
     * @throws GenieException If the task number is missing or invalid
     */
    public UnmarkCommand(String input) throws GenieException {
        String[] parts = input.split(" ", 2);
        if (parts.length < 2) {
            throw new GenieException("OOPS!!! Please specify which task to unmark.");
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
        Task task = tasks.getTask(taskIndex);
        if (!task.isDone()) {
            throw new GenieException("OOPS!!! This task is already unmarked.");
        }
        task.markAsUndone();
        storage.save(tasks.getTasks());
        ui.showTaskUnmarked(task);
    }
}
