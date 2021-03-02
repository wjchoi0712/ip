package duke.storage;


import duke.exception.data.InvalidFilePathException;
import duke.exception.data.LoadDataOperationException;
import duke.exception.data.SaveDataOperationException;
import duke.exception.description.InvalidDeadlineDescriptionException;
import duke.exception.description.InvalidDescriptionException;
import duke.exception.description.InvalidEventDescriptionException;
import duke.exception.description.NoDescriptionException;
import duke.task.TaskList;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the file used to store {@link TaskList}.
 */
public class Storage {
    protected final Path path;

    /**
     * Constructs a storage by setting path of the data storage as the String input from the user
     *
     * @param filePath path of the data storage
     */
    public Storage(String filePath) {
        path = Paths.get(filePath);
    }

    /**
     * Creates a new directory if there is no existing directory
     *
     * @throws InvalidFilePathException if the file path is invalid
     */
    public void createDirectory() throws InvalidFilePathException {
        if (!Files.exists(path.getParent())) {
            try {
                Files.createDirectory(path.getParent());
            } catch (IOException ioe) {
                throw new InvalidFilePathException();
            }
        }
    }

    /**
     * Creates a new text file to store the data of task if there is no existing file
     *
     * @throws InvalidFilePathException if the file path is invalid
     */
    public void createFile() throws InvalidFilePathException {
        if (!Files.exists(path)) {
            try {
                Files.createFile(path);
            } catch (IOException ioe) {
                throw new InvalidFilePathException();
            }
        }
    }

    /**
     * Saves the encoded {@link TaskList} data into the storage file
     *
     * @param tasks task list to be saved
     * @throws SaveDataOperationException if there is error while encoding/ storing the data to file.
     */
    public void saveData(TaskList tasks) throws SaveDataOperationException {
        try {
            ArrayList<String> encodedTaskList = new TaskFileEncoder().encodeTaskData(tasks);
            Files.write(path, encodedTaskList);
        } catch (IOException ioe) {
            throw new SaveDataOperationException();
        }
    }

    /**
     * Decodes and returns the {@link TaskList} that is encoded in the storage file
     *
     * @return the list that has been decoded
     * @throws LoadDataOperationException if there is error while reading/ decoding the saved data
     */
    public TaskList loadData() throws LoadDataOperationException {
        TaskList tasks = new TaskList();
        try {
            Scanner s = new Scanner(path);
            while (s.hasNext()) {
                String data = s.nextLine();
                tasks.addTask(new TaskFileDecoder().decodeTaskData(data));
            }
        } catch (IOException | InvalidDescriptionException | NoDescriptionException |
                InvalidDeadlineDescriptionException | InvalidEventDescriptionException e) {
            throw new LoadDataOperationException();
        }
        return tasks;
    }
}
