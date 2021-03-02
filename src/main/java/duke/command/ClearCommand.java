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

    public static final String LS = System.lineSeparator();
    public static final String MESSAGE_USAGE = "- clear: Deletes all the tasks in the task list." + LS
            + " Example: clear" + LS;
    public static final String MESSAGE_SUCCESS = " Noted. I've cleared %d tasks in the list.";


    public ClearCommand(String userInput) {
        super(userInput);
    }

    /** Deletes all the {@link Task} in the {@link TaskList} */
    @Override
    public void execute(TaskList tasks, TextUi ui, Storage storage) throws SaveDataOperationException {
        try {
            ui.showClearResponse(tasks);
            tasks.clearTaskList();
            storage.saveData(tasks);
        } catch (ClearTaskException e) {
            ui.showToUser(e.getMessage());
        }
    }
}
