package duke.exception.description;

/**
 * Signals that the user did not input task description
 */
public class NoDescriptionException extends DescriptionException {
    @Override
    public String getMessage() {
        return " OOPS! Description of a task cannot be empty:(";
    }
}
