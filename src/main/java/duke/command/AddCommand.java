package duke.command;

import duke.exception.description.DescriptionException;
import duke.exception.data.SaveDataOperationException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.TextUi;

/**
 * Adds a {@link Task} into the {@link TaskList}.
 */
public class AddCommand extends Command {

    public static final String LS = System.lineSeparator();
    public static final String MESSAGE_USAGE = "- todo/ deadline/ event: Adds a task to the task list." + LS
            + " Parameters:" + LS
            + "  For todo: DESCRIPTION" + LS
            + "  For deadline: DESCRIPTION /by DUEDATE" + LS
            + "  For event: DESCRIPTION /by EVENTDATE" + LS
            + " Example: " + LS
            + "  todo smile" + LS
            + "  deadline submit hw /by 2021-12-01" + LS
            + "  event watch netflix /at 2021-12-01" + LS;

    public static final String MESSAGE_SUCCESS = " Got it. I've added this task:";

    protected String commandType;

    public AddCommand(String userInput, String commandType) {
        super(userInput);
        this.commandType = commandType;
    }

    /**
     * Adds a new {@link Task} into the {@link TaskList} and prints out a message showing that
     * the command has been successfully executed.
     *
     * @param tasks task list where the new task is added
     * @param ui user interface to print out message
     * @param storage data file where the edited task list is saved
     * @throws SaveDataOperationException if error occurs while encoding/saving the task list
     */
    @Override
    public void execute(TaskList tasks, TextUi ui, Storage storage) throws SaveDataOperationException {
        try {
            tasks.addTask(commandType, userInput);
            ui.showAddResponse(tasks);
            storage.saveData(tasks);
        } catch (DescriptionException de) {
            ui.showToUser(de.getMessage());
        }
    }
}
