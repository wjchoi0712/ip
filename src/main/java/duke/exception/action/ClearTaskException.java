package duke.exception.action;

import duke.exception.DukeException;

/**
 * Signals that there are no {@link duke.task.Task} to clear in {@link duke.task.TaskList}
 */
public class ClearTaskException extends DukeException{
        @Override
        public String getMessage() {
            return " Your task list is already empty!";
        }
}
