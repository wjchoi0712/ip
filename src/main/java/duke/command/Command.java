package duke.command;

import duke.exception.data.SaveDataOperationException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.TextUi;

/**
 * Represents an executable command.
 */
public class Command {

    protected String userInput;

    /**
     * Constructs a command object to be processed.
     *
     * @param userInput full command of the user
     */
    public Command(String userInput) {
        this.userInput = userInput;
    }

    /** Checks whether application should be closed */
    public boolean isExit() {
        return false;
    }

    public void execute(TaskList tasks, TextUi ui, Storage storage) throws SaveDataOperationException {
    }
}
