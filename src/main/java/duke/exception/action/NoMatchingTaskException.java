package duke.exception.action;

import duke.exception.DukeException;

public class NoMatchingTaskException extends DukeException {
    public String getMessage() {
        return " OOPS! There are no tasks which matches your keyword :(";
    }
}
