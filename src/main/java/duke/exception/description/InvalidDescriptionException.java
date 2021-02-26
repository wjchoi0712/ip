package duke.exception.description;

public class InvalidDescriptionException extends DescriptionException {

    public String getMessage() {
        return " OOPS! DEADLINE and EVENT tasks requires both task description and task time";
    }
}
