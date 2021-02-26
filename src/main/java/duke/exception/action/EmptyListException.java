package duke.exception.action;

import duke.exception.DukeException;

public class EmptyListException extends DukeException {
    @Override
    public String getMessage() {
        return " Hurray! You currently have no tasks :)";
    }
}
