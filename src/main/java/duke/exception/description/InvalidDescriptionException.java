package duke.exception.description;

/**
 * Signals that the description and time component cannot be empty for
 * {@link duke.task.Deadline} and {@link duke.task.Event}
 */
public class InvalidDescriptionException extends DescriptionException {
    @Override
    public String getMessage() {
        return " OOPS! DEADLINE and EVENT tasks requires both task description and task time :(";
    }
}
