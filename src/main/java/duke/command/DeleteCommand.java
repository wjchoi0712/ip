package duke.command;

import duke.exception.action.InvalidCommandException;
import duke.exception.action.InvalidTaskNoException;
import duke.exception.data.SaveDataOperationException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.TextUi;

public class DeleteCommand extends Command {
    public DeleteCommand(String userInput) throws InvalidCommandException {
        super(userInput);
    }

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
