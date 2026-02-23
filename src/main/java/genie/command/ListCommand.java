package genie.command;

import genie.storage.Storage;
import genie.task.TaskList;
import genie.ui.Ui;

/**
 * Command that lists all tasks.
 */
public class ListCommand extends Command {

    /**
     * Displays all tasks in the task list.
     *
     * @param tasks   The current task list
     * @param ui      The Ui instance for user interactions
     * @param storage The Storage instance (unused)
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks);
    }
}
