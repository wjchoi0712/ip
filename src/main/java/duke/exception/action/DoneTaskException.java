package duke.exception.action;

import duke.exception.DukeException;

/**
 * Signals that the {@link duke.task.Task} has already been marked as done
 */
public class DoneTaskException extends DukeException {
    @Override
    public String getMessage() {
        return " This task has already been marked as done!";
    }
}
