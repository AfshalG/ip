package genie.command;

import genie.exception.GenieException;
import genie.storage.Storage;
import genie.task.TaskList;
import genie.ui.Ui;

/**
 * Represents an executable command.
 */
public abstract class Command {

    /**
     * Executes this command.
     *
     * @param tasks   The current task list
     * @param ui      The Ui instance for user interactions
     * @param storage The Storage instance for saving tasks
     * @throws GenieException If the command encounters an error
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws GenieException;

    /**
     * Returns whether this command should exit the program.
     *
     * @return true if the program should exit, false otherwise
     */
    public boolean isExit() {
        return false;
    }
}
