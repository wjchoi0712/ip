package duke.exception;

public class InvalidDescriptionException extends DukeException {

    public String getMessage() {
        return " OOPS! Description of DEADLINE and EVENT tasks require \"by\" and \"at\" respectively!";
    }
}
