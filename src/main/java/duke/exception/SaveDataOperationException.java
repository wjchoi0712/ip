package duke.exception;

public class SaveDataOperationException extends StorageOperationException {
    public String getMessage() {
        return " OOPS! Error occurred while trying to save task list :(";
    }
}
