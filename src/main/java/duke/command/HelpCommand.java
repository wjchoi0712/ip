package duke.command;

import duke.exception.data.SaveDataOperationException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.TextUi;

public class HelpCommand extends Command {

    public HelpCommand(String userInput) {
        super(userInput);
    }

    @Override
    public void execute(TaskList tasks, TextUi ui, Storage storage) throws SaveDataOperationException {
        ui.showHelpResponse();
    }
}
