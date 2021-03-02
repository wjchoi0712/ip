package duke.storage;

import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;

/**
 * Encodes the {@link TaskList} object into a data file for storage
 */
public class TaskFileEncoder {

    private static final String SPACING = " ";

    /**
     * Encodes all the {@link Task} in the {@link TaskList} into a list of String
     *
     * @param tasks task list to be encoded
     * @return the task list that has been encoded
     */
    public ArrayList<String> encodeTaskData(TaskList tasks) {
        ArrayList<String> encodedTaskList = new ArrayList<>();
        for (int i = 0; i < tasks.getTotalNoOfTasks(); i++) {
            encodedTaskList.add(encodeTaskToString(tasks.getTask(i)));
        }
        return encodedTaskList;
    }

    /**
     * Encodes the {@link Task} into a format for storage
     *
     * @param task task to be encoded
     */
    public String encodeTaskToString(Task task) {
        StringBuilder encodedTask = new StringBuilder();

        String taskType = task.getType();
        encodedTask.append(taskType).append(SPACING);

        String taskDescription = task.getDescription();
        encodedTask.append(taskDescription).append(SPACING);

        String taskStatus = task.getStatusIcon();
        encodedTask.append(taskStatus);

        return encodedTask.toString();
    }
}
