package duke.command;

import duke.exception.DoneTaskException;
import duke.exception.InvalidCommandException;
import duke.exception.InvalidTaskNoException;
import duke.exception.SaveDataOperationException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.TextUi;

public class CommandDone extends Command {
    public CommandDone(String userInput) throws InvalidCommandException {
        super(userInput);
    }

    @Override
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
