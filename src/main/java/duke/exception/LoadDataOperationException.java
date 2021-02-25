package duke.exception;

public class LoadDataOperationException extends StorageOperationException {
    public String getMessage() {
        return " OOPS! Error occurred while trying to load previous task list :(";
    }
}
