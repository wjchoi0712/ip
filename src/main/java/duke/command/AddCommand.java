package duke.command;

import duke.exception.action.InvalidCommandException;
import duke.exception.description.DescriptionException;
import duke.exception.data.SaveDataOperationException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.TextUi;

public class AddCommand extends Command {
    public AddCommand(String userInput) throws InvalidCommandException {
        super(userInput);
    }

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
