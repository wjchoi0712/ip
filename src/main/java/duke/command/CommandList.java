package duke.command;

import duke.exception.commandException.EmptyListException;
import duke.exception.commandException.InvalidCommandException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.TextUi;

public class CommandList extends Command {

    public CommandList(String userInput) throws InvalidCommandException {
        super(userInput);
    }

    public void execute(TaskList tasks, TextUi ui, Storage storage) {
        try {
            ui.showTaskList(tasks);
        } catch (EmptyListException ele) {
            ui.showToUser(ele.getMessage());
        }
    }
}
