package duke.exception.action;

import duke.exception.DukeException;

/**
 * Signals that there are no {@link duke.task.Task} with description that matches the user's keyword
 */
public class NoMatchingTaskException extends DukeException {
    @Override
    public String getMessage() {
        return " OOPS! There are no tasks which matches your keyword :(";
    }
}
