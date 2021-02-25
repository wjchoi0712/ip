package duke.command;

import duke.exception.commandException.InvalidCommandException;
import duke.exception.descriptionException.*;
import duke.exception.storageException.SaveDataOperationException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.TextUi;

public class CommandAdd extends Command {
    public CommandAdd(String userInput) throws InvalidCommandException {
        super(userInput);
    }

    @Override

    public void execute(TaskList tasks, TextUi ui, Storage storage) throws SaveDataOperationException {
        try {
            tasks.addTask(userInput, commandType);
            ui.showAddResponse(tasks);
            storage.saveData(tasks);
        } catch (DescriptionException de) {
            ui.showToUser(de.getMessage());
        }
    }
}
