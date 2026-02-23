package genie.command;

import genie.storage.Storage;
import genie.task.TaskList;
import genie.ui.Ui;

/**
 * Command that ends the program.
 */
public class ByeCommand extends Command {

    /**
     * Displays the goodbye message.
     *
     * @param tasks   The current task list (unused)
     * @param ui      The Ui instance for user interactions
     * @param storage The Storage instance (unused)
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showBye();
    }

    /**
     * Returns true since this command exits the program.
     *
     * @return true
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
