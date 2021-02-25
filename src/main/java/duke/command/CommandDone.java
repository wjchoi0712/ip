package duke.command;

import duke.exception.commandException.DoneTaskException;
import duke.exception.commandException.InvalidCommandException;
import duke.exception.commandException.InvalidTaskNoException;
import duke.exception.storageException.SaveDataOperationException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.TextUi;

public class CommandDone extends Command {
    public CommandDone(String userInput) throws InvalidCommandException {
        super(userInput);
    }

    public void execute(TaskList tasks, TextUi ui, Storage storage) throws SaveDataOperationException {
        try {
            int completedTaskNo = new Parser().prepareForDoneOrDelete(userInput, "done ", tasks.getTotalNoOfTasks());
            tasks.completeTask(completedTaskNo);
            ui.showDoneResponse(tasks, completedTaskNo);
            storage.saveData(tasks);
        } catch (InvalidTaskNoException | DoneTaskException e) {
            ui.showToUser(e.getMessage());
        }
    }
}
