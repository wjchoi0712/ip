package duke.exception.description;

public class InvalidDeadlineDescriptionException extends DescriptionException {
    public String getMessage() {
        return " OOPS! Description of Deadline task should be in \"deadline [description] /by [due date]\" format";
    }
}
