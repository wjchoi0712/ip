package duke.command;

import duke.exception.InvalidCommandException;
import duke.exception.NoMatchingTaskException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.TextUi;

public class CommandFind extends Command {
    public CommandFind(String userInput) throws InvalidCommandException {
        super(userInput);
    }

    @Override
    public void execute(TaskList tasks, TextUi ui, Storage storage) {
        try {
            TaskList matchingTasks = new Parser().prepareForFind(tasks, userInput);
            ui.showFindResponse(matchingTasks);
        } catch (NoMatchingTaskException e) {
            ui.showToUser(e.getMessage());
        }
    }
}
