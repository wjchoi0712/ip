package duke.exception.data;

/**
 * Signals that an error occurred while saving or encoding the data
 */
public class SaveDataOperationException extends StorageOperationException {
    @Override
    public String getMessage() {
        return " OOPS! Error occurred while trying to save current task list :(";
    }
}
