package duke.command;

import duke.exception.InvalidCommandException;
import duke.exception.InvalidTaskNoException;
import duke.exception.SaveDataOperationException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.TextUi;

public class CommandDelete extends Command {
    public CommandDelete(String userInput) throws InvalidCommandException {
        super(userInput);
    }

    @Override
    public void execute(TaskList tasks, TextUi ui, Storage storage) throws SaveDataOperationException {
        try {
            int deletedTaskNo = new Parser().prepareForDelete(tasks, userInput);
            ui.showDeleteResponse(tasks, deletedTaskNo);
            tasks.deleteTask(deletedTaskNo);
            storage.saveData(tasks);
        } catch (InvalidTaskNoException e) {
            ui.showToUser(e.getMessage());
        }
    }
}
