package duke.storage;

import duke.exception.description.InvalidDeadlineDescriptionException;
import duke.exception.description.InvalidDescriptionException;
import duke.exception.description.InvalidEventDescriptionException;
import duke.exception.description.NoDescriptionException;
import duke.parser.Parser;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * Decodes the encoded task list data in the storage file
 */
public class TaskFileDecoder {

    /**
     * Decodes stored data and returns the decoded {@link Task}
     * The methods in {@link Parser} are used to decode the data.
     */
    public Task decodeTaskData(String data) throws InvalidDescriptionException, InvalidDeadlineDescriptionException,
            NoDescriptionException, InvalidEventDescriptionException {

        String[] taskDescriptionAndStatus = separateTaskDescriptionAndStatus(data);
        boolean isDone = taskDescriptionAndStatus[1].equals("X");

        Task decodedTask;

        //Container to carry task description and task time
        String[] taskComponents;

        if (data.startsWith("todo")) {
            String taskDescription = new Parser().prepareForAddTodo(taskDescriptionAndStatus[0]);
            decodedTask = new ToDo(taskDescription);
        } else if (data.startsWith("deadline")) {
            taskComponents = new Parser().prepareForAddDeadline(taskDescriptionAndStatus[0]);
            decodedTask = new Deadline(taskComponents[0],taskComponents[1]);
        } else { //if data starts with "event"
            taskComponents = new Parser().prepareForAddEvent(taskDescriptionAndStatus[0]);
            decodedTask = new Event(taskComponents[0], taskComponents[1]);
        }

        if (isDone) {
            decodedTask.markAsDone();
        }
        return decodedTask;
    }

    /**
     * Separates and returns the task description and task status.
     * The first element of the {@code taskDescriptionAndStatus} contains the task description
     * and the second element contains task status
     */
    public String[] separateTaskDescriptionAndStatus(String data) {
        String[] taskDescriptionAndStatus = new String[2];

        //Extract out just the task description
        taskDescriptionAndStatus[0] = data.substring(0, data.indexOf('[') - 1);

        //Extract out just the task status
        taskDescriptionAndStatus[1] = data.substring(data.indexOf('[') + 1, data.indexOf(']'));

        return taskDescriptionAndStatus;
    }
}
