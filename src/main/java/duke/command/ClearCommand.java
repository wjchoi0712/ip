package duke.command;

import duke.exception.action.ClearTaskException;
import duke.exception.data.SaveDataOperationException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.TextUi;

/**
 * Clears the {@link TaskList}.
 */
public class ClearCommand extends Command {
    public ClearCommand(String userInput) {
        super(userInput);
    }

    /** Deletes all the {@link Task} in the {@link TaskList} */
    @Override
    public void execute(TaskList tasks, TextUi ui, Storage storage) throws SaveDataOperationException {
        try {
            ui.showClearResponse(tasks);
            tasks.clearTaskList();
        } catch (ClearTaskException e) {
            ui.showToUser(e.getMessage());
        }
    }
}
