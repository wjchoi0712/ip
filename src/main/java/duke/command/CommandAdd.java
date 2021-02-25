package duke.command;

import duke.exception.InvalidCommandException;
import duke.exception.InvalidDescriptionException;
import duke.exception.NoDescriptionException;
import duke.exception.SaveDataOperationException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.TextUi;

public class CommandAdd extends Command{
    public CommandAdd(String userInput) throws InvalidCommandException {
        super(userInput);
    }

    @Override
    public void execute(TaskList tasks, TextUi ui, Storage storage) throws SaveDataOperationException {
        try {
            tasks.addTask(userInput, commandType);
            ui.showAddResponse(tasks);
            storage.saveData(tasks);
        } catch (NoDescriptionException | InvalidDescriptionException e) {
            ui.showToUser(e.getMessage());
        }
    }
}
