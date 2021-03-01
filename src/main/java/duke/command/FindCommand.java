package duke.command;

import duke.exception.action.NoMatchingTaskException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.TextUi;

/**
 * Prints out all the {@link Task} which has the user's keyword in its description.
 */
public class FindCommand extends Command {
    public FindCommand(String userInput) {
        super(userInput);
    }

    /**
     * Generates and prints out a {@link TaskList} of matching {@link Task}
     */
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
