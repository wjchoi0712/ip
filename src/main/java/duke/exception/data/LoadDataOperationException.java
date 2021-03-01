package duke.exception.data;

/**
 * Signals that an error occurred while loading or decoding the data
 */
public class LoadDataOperationException extends StorageOperationException {
    @Override
    public String getMessage() {
        return " OOPS! Error occurred while trying to load previous task list :(";
    }
}
