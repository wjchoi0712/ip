public class InvalidDescriptionException extends DukeException {
    @Override
    public String getMessage() {
        return " OOPS! Description of DEADLINE and EVENT tasks require \"by\" and \"at\" respectively!";
    }
}
