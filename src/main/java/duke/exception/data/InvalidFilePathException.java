package duke.exception.data;

/**
 * Signals that the filepath is invalid
 */
public class InvalidFilePathException extends  StorageOperationException {
    @Override
    public String getMessage() {
        return " OOPS! Error occurred while creating directory or file :(";
    }
}
