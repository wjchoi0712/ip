package duke.exception;

public class NoDescriptionException extends DukeException {
    @Override
    public String getMessage() {
        return " OOPS! Description of a task cannot be empty!";
    }
}
