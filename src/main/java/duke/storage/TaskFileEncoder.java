package duke.storage;

import duke.exception.storageException.SaveDataOperationException;
import duke.task.Task;
import duke.task.TaskList;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

public class TaskFileEncoder {

    private static final String SPACING = " ";
    private static final String LS = System.lineSeparator();

    public void encodeTaskData(TaskList tasks, Path filePath) throws SaveDataOperationException {
        try {
            FileWriter fw = new FileWriter(filePath.toString());
            for (int i = 0; i < tasks.getTotalNoOfTasks(); i++) {
                Task currentTask = tasks.getTask(i);
                String taskType = currentTask.getType();
                String taskStatus = currentTask.getStatusIcon();
                String taskDescription = currentTask.getDescription();
                fw.write(taskType + SPACING + taskDescription + SPACING + taskStatus + LS);
            }
            fw.close();
        } catch (IOException ioe) {
            throw new SaveDataOperationException();
        }
    }
}
