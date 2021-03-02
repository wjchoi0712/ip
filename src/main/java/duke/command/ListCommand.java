package duke.command;

import duke.exception.action.EmptyListException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.TextUi;

/**
 * Prints out all the {@link Task} in the {@link TaskList}.
 */
public class ListCommand extends Command {

    public static final String LS = System.lineSeparator();
    public static final String MESSAGE_USAGE = "- list: Shows all the tasks in the task list." + LS
            + " Example: list" + LS;
    public static final String MESSAGE_SUCCESS = " Here are the tasks in your list:";

    public ListCommand(String userInput) {
        super(userInput);
    }

    /** Prints out the {@link Task} in the {@link TaskList} */
    @Override
    public void execute(TaskList tasks, TextUi ui, Storage storage) {
        try {
            ui.showListResponse(tasks);
        } catch (EmptyListException ele) {
            ui.showToUser(ele.getMessage());
        }
    }
}
