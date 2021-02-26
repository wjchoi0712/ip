package duke.command;

import duke.exception.action.DoneTaskException;
import duke.exception.action.InvalidCommandException;
import duke.exception.action.InvalidTaskNoException;
import duke.exception.data.SaveDataOperationException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.TextUi;

public class DoneCommand extends Command {
    public DoneCommand(String userInput) throws InvalidCommandException {
        super(userInput);
    }

    public void execute(TaskList tasks, TextUi ui, Storage storage) throws SaveDataOperationException {
        try {
            int completedTaskNo = new Parser().prepareForDone(tasks, userInput);
            tasks.completeTask(completedTaskNo);
            ui.showDoneResponse(tasks, completedTaskNo);
            storage.saveData(tasks);
        } catch (InvalidTaskNoException | DoneTaskException e) {
            ui.showToUser(e.getMessage());
        }
    }
}
