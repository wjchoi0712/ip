package duke.exception.action;

import duke.exception.DukeException;

public class DoneTaskException extends DukeException {
    public String getMessage() {
        return " This task has already been marked as done!";
    }
}
