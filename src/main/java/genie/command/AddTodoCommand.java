package genie.command;

import genie.exception.GenieException;
import genie.storage.Storage;
import genie.task.TaskList;
import genie.task.Todo;
import genie.ui.Ui;

/**
 * Command that adds a todo task.
 */
public class AddTodoCommand extends Command {
    private final String description;

    /**
     * Creates an AddTodoCommand by parsing the input.
     *
     * @param input The full user input string
     * @throws GenieException If the description is empty
     */
    public AddTodoCommand(String input) throws GenieException {
        String content = input.substring("todo".length()).trim();
        if (content.isEmpty()) {
            throw new GenieException("OOPS!!! The description of a todo cannot be empty.");
        }
        description = content;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Todo todo = new Todo(description);
        tasks.addTask(todo);
        storage.save(tasks.getTasks());
        ui.showTaskAdded(todo, tasks.size());
    }
}
