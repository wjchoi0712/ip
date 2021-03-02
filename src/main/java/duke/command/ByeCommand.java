package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.TextUi;

/**
 * Terminates the program
 */
public class ByeCommand extends Command {

    public ByeCommand(String userInput) {
        super(userInput);
    }

    /** Overrides the {@link Command#isExit()} method to terminate the program */
    @Override
    public boolean isExit() {
        return true;
    }

    /** Prints out the ending message */
    public void execute(TaskList tasks, TextUi ui, Storage storage){
        ui.showEndingMessage();
    }
}
