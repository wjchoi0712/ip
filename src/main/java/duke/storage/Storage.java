package duke.storage;

import duke.exception.storageException.InvalidFilePathException;
import duke.exception.storageException.LoadDataOperationException;
import duke.exception.storageException.SaveDataOperationException;
import duke.task.TaskList;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Storage {
    protected final Path path;

    public Storage(String filePath) {
        path = Paths.get(filePath);
    }

    public void createDirectory() throws InvalidFilePathException {
        if (!Files.exists(path.getParent())) {
            try {
                Files.createDirectory(path.getParent());
            } catch (IOException ioe) {
                throw new InvalidFilePathException();
            }
        }
    }

    public void createFile() throws InvalidFilePathException {
        if (!Files.exists(path)) {
            try {
                Files.createFile(path);
            } catch (IOException ioe) {
                throw new InvalidFilePathException();
            }
        }
    }

    public void saveData(TaskList tasks) throws SaveDataOperationException {
        new TaskFileEncoder().encodeTaskData(tasks, path);
    }

    public TaskList loadData() throws LoadDataOperationException {
        return new TaskFileDecoder().decodeTaskData(path);
    }
}
