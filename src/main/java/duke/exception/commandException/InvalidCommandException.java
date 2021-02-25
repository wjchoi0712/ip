package duke.exception.commandException;

import duke.exception.DukeException;

public class InvalidCommandException extends DukeException {
    @Override
    public String getMessage() {
        return " OOPS! I'm sorry but I don't understand your command :(";
    }
}
