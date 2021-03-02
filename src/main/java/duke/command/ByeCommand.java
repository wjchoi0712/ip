package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.TextUi;

/**
 * Terminates the program
 */
public class ByeCommand extends Command {

    private static final String LS = System.lineSeparator();
    public static final String MESSAGE_USAGE = "- bye: Terminates the program." + LS
            + " Example: bye";

    public ByeCommand(String userInput) {
        super(userInput);
    }

    /** Overrides the {@link Command#isExit()} method to terminate the program */
    @Override
    public boolean isExit() {
        return true;
    }

    /** Prints out the ending message */
    @Override
    public void execute(TaskList tasks, TextUi ui, Storage storage){
        ui.showEndingMessage();
    }
}
