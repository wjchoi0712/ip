package duke;

import duke.command.Command;
import duke.exception.action.InvalidCommandException;
import duke.exception.data.InvalidFilePathException;
import duke.exception.data.SaveDataOperationException;
import duke.exception.data.StorageOperationException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.TextUi;

/**
 * Entry point of the Duke application.
 * Initializes the application and starts the interaction with the user.
 */
public class Duke {
    private static TextUi ui;
    private static Storage storage;
    private static TaskList tasks;

    public static void main(String[] args) {
        new Duke().run();
    }

    /** Sets up and run the program */
    private void run() {
        try {
            this.ui = new TextUi();
            this.storage = initializeStorage("data/tasks.txt");
            this.tasks = storage.loadData();
            ui.showGreetingMessage();
            runCommandLoopUntilExitCommand();
        } catch(StorageOperationException e) {
            ui.showToUser(e.getMessage());
        }
    }

    /** Creates new directory and file according to the {@code filepath} if it does not already exists. */
    private Storage initializeStorage(String filePath) throws InvalidFilePathException {
        Storage storage = new Storage(filePath);
        storage.createDirectory();
        storage.createFile();
        return storage;
    }

    /** Reads the user command and executes it, until the user issues the bye command. */
    private static void runCommandLoopUntilExitCommand() throws SaveDataOperationException {
        boolean isExit = false;
        while (!isExit) {
            try {
                String userInput = ui.getUserCommand();
                Command command = Parser.prepareForCommandExecution(userInput);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (InvalidCommandException ice) {
                ui.showToUser(ice.getMessage());
            }
        }
    }
}
