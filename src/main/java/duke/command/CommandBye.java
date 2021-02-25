package duke.command;

import duke.exception.commandException.InvalidCommandException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.TextUi;

public class CommandBye extends Command {

    public CommandBye(String userInput) throws InvalidCommandException {
        super(userInput);
    }

    public void execute(TaskList tasks, TextUi ui, Storage storage){
        ui.showEndingMessage();
    }
}
