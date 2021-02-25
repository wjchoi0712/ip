package duke.exception;

public class DoneTaskException extends DukeException {
    public String getMessage() {
        return " This task has already been marked as done!";
    }
}
