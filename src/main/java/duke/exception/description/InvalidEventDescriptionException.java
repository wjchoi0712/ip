package duke.exception.description;

/**
 * Signals that description of {@link duke.task.Event} given by the user is invalid
 */
public class InvalidEventDescriptionException extends DescriptionException {
    @Override
    public String getMessage() {
        return " OOPS! Description of Event task should be in \"event [description] /at [event date]\" format";
    }
}
