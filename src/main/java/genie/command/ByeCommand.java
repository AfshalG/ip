package genie.command;

import genie.storage.Storage;
import genie.task.TaskList;
import genie.ui.Ui;

/**
 * Command that ends the program.
 */
public class ByeCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showBye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
