package duke.command;

import duke.exception.action.DoneTaskException;
import duke.exception.action.InvalidTaskNoException;
import duke.exception.data.SaveDataOperationException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.TextUi;

/**
 * Marks a specific {@link Task} in the {@link TaskList} as done.
 */
public class DoneCommand extends Command {

    public static final String LS = System.lineSeparator();
    public static final String MESSAGE_USAGE = "- done: Marks the chosen task as done." + LS
            + " Parameters: INDEX" + LS
            + " Example: done 1" + LS;
    public static final String MESSAGE_SUCCESS = "Nice! I've marked this task as done:";

    public DoneCommand(String userInput) {
        super(userInput);
    }

    /**
     * Marks a {@link Task} from the {@link TaskList} as done and prints out a message showing that
     * the command has been successfully executed.
     *
     * @param tasks task list containing the task to be deleted
     * @param ui user interface to print out message
     * @param storage data file where the edited task list is saved
     * @throws SaveDataOperationException if error occurs while encoding/saving the task list
     */
    @Override
    public void execute(TaskList tasks, TextUi ui, Storage storage) throws SaveDataOperationException {
        try {
            int completedTaskNo = new Parser().prepareForDone(tasks, userInput);
            tasks.completeTask(completedTaskNo);
            ui.showDoneResponse(tasks, completedTaskNo);
            storage.saveData(tasks);
        } catch (InvalidTaskNoException | DoneTaskException e) {
            ui.showToUser(e.getMessage());
        }
    }
}
