package duke.exception.commandException;

import duke.exception.DukeException;

public class EmptyListException extends DukeException {
    @Override
    public String getMessage() {
        return " OOPS! You currently have no tasks!";
    }
}
