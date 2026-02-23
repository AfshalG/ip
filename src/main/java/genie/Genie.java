package genie;

import genie.command.Command;
import genie.exception.GenieException;
import genie.parser.Parser;
import genie.storage.Storage;
import genie.task.TaskList;
import genie.ui.Ui;

/**
 * Main class for the Genie task manager chatbot.
 */
public class Genie {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Creates a Genie instance with the given data file path.
     *
     * @param filePath Path to the file used for saving and loading tasks
     */
    public Genie(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the main command loop until the user exits.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (GenieException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
        ui.close();
    }

    /**
     * Entry point for the Genie application.
     *
     * @param args Command-line arguments (not used)
     */
    public static void main(String[] args) {
        new Genie("data/genie.txt").run();
    }
}
