package duke.command;

import duke.exception.action.InvalidCommandException;
import duke.exception.action.NoMatchingTaskException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.TextUi;

public class FindCommand extends Command {
    public FindCommand(String userInput) throws InvalidCommandException {
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
