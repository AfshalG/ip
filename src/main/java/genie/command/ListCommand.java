package genie.command;

import genie.storage.Storage;
import genie.task.TaskList;
import genie.ui.Ui;

/**
 * Command that lists all tasks.
 */
public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks);
    }
}
