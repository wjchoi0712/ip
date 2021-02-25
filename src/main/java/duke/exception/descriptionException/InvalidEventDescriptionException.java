package duke.exception.descriptionException;

public class InvalidEventDescriptionException extends DescriptionException {
    public String getMessage() {
        return " OOPS! Description of Event task should be in \"event [description] /at [event date]\" format";
    }
}
