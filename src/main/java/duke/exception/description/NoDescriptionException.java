package duke.exception.description;

public class NoDescriptionException extends DescriptionException {
    @Override
    public String getMessage() {
        return " OOPS! Description of a task cannot be empty!";
    }
}
