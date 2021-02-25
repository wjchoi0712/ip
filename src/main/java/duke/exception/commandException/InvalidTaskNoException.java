package duke.exception.commandException;

import duke.exception.DukeException;

public class InvalidTaskNoException extends DukeException {
    public String getMessage() {
        return " OOPS! You must enter an \"integer number\" that is \"within the bound\" :(";
    }
}
