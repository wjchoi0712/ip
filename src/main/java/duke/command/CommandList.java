package duke.command;

import duke.exception.EmptyListException;
import duke.exception.InvalidCommandException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.TextUi;

public class CommandList extends Command {

    public CommandList(String userInput) throws InvalidCommandException {
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
