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

public class Duke {
    private static TextUi ui;
    private static Storage storage;
    private static TaskList tasks;

    public static void main(String[] args) {
        new Duke().run();
    }

    private void run() {
        try {
            this.ui = new TextUi();
            this.storage = initializeStorage("data/tasks.txt");
            this.tasks = storage.loadData();
            ui.showGreetingMessage();
            runCommandLoopUntilExitCommand();
        } catch(StorageOperationException soe) {
            ui.showToUser(soe.getMessage());
        }
    }

    private Storage initializeStorage(String filePath) throws InvalidFilePathException {
        Storage storage = new Storage(filePath);
        storage.createDirectory();
        storage.createFile();
        return storage;
    }

    public static void runCommandLoopUntilExitCommand() throws SaveDataOperationException {
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
