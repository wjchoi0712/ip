package duke.exception.action;

import duke.exception.DukeException;

/**
 * Signals that the task number that user inputted is out of bound or is not an integer
 */
public class InvalidTaskNoException extends DukeException {
    @Override
    public String getMessage() {
        return " OOPS! You must enter an \"integer\" that is \"within the bound\" :(";
    }
}
