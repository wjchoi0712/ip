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

    public ListCommand(String userInput) {
        super(userInput);
    }

    /**
     * Prints out the {@link Task} in the {@link TaskList}.
     */
    @Override
    public void execute(TaskList tasks, TextUi ui, Storage storage) {
        try {
            ui.showListResponse(tasks);
        } catch (EmptyListException ele) {
            ui.showToUser(ele.getMessage());
        }
    }
}
