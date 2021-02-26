package duke.command;

import duke.exception.action.InvalidCommandException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.TextUi;

public class ByeCommand extends Command {

    public ByeCommand(String userInput) throws InvalidCommandException {
        super(userInput);
    }

    public void execute(TaskList tasks, TextUi ui, Storage storage){
        ui.showEndingMessage();
    }
}
