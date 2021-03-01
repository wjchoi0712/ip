package duke.exception.action;

import duke.exception.DukeException;

/**
 * Signals that the user's command is not one of the {@link duke.command.CommandType}
 */
public class InvalidCommandException extends DukeException {
    @Override
    public String getMessage() {
        return " OOPS! I'm sorry but I don't understand your command :(";
    }
}
