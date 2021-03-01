package duke.exception.action;

import duke.exception.DukeException;

/**
 * Signals that the {@link duke.task.TaskList} is empty
 */
public class EmptyListException extends DukeException {
    @Override
    public String getMessage() {
        return " Hurray! You currently have no tasks :)";
    }
}
