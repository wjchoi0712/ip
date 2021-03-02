package duke.command;

import duke.exception.action.InvalidTaskNoException;
import duke.exception.data.SaveDataOperationException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.TextUi;

/**
 * Deletes a specific {@link Task} from the {@link TaskList}.
 */
public class DeleteCommand extends Command {
    public DeleteCommand(String userInput) {
        super(userInput);
    }

    /**
     * Deletes a {@link Task} from the {@link TaskList} and prints out a message showing that
     * the command has been successfully executed.
     *
     * @param tasks task list containing the task to be marked as done
     * @param ui user interface to print out message
     * @param storage data file where the edited task list is saved
     * @throws SaveDataOperationException if error occurs while encoding/saving the task list
     */
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
