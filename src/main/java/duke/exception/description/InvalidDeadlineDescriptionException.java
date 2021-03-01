package duke.exception.description;

/**
 * Signals that description of {@link duke.task.Deadline} given by the user is invalid
 */
public class InvalidDeadlineDescriptionException extends DescriptionException {
    @Override
    public String getMessage() {
        return " OOPS! Description of Deadline task should be in \"deadline [description] /by [due date]\" format";
    }
}
