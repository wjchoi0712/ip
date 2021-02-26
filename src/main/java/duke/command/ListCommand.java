package duke.command;

import duke.exception.action.EmptyListException;
import duke.exception.action.InvalidCommandException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.TextUi;

public class ListCommand extends Command {

    public ListCommand(String userInput) throws InvalidCommandException {
        super(userInput);
    }

    @Override
    public void execute(TaskList tasks, TextUi ui, Storage storage) {
        try {
            ui.showListResponse(tasks);
        } catch (EmptyListException ele) {
            ui.showToUser(ele.getMessage());
        }
    }
}
