package duke.task;

import duke.command.CommandType;
import duke.exception.DoneTaskException;
import duke.parser.Parser;
import duke.exception.InvalidDescriptionException;
import duke.exception.NoDescriptionException;

import java.util.ArrayList;

public class TaskList {

    protected ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<Task>();
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public Task getLastTaskInTheList() {
        return tasks.get(tasks.size() - 1);
    }

    public int getTotalNoOfTasks() {
        return tasks.size();
    }

    public void addTask(String userInput, CommandType commandType) throws NoDescriptionException, InvalidDescriptionException {
        String[] taskComponents = new Parser().prepareForAdd(userInput);
        switch (commandType) {
        case TODO:
            tasks.add(new ToDo(taskComponents[0]));
            break;
        case DEADLINE:
            if (taskComponents[1].length() > 0) {
                tasks.add(new Deadline(taskComponents[0], taskComponents[1]));
            } else {
                throw new InvalidDescriptionException();
            }
            break;
        case EVENT:
            if (taskComponents[2].length() > 0) {
                tasks.add(new Event(taskComponents[0], taskComponents[2]));
            } else {
                throw new InvalidDescriptionException();
            }
            break;
        }
    }

    public void completeTask(int completedTaskNo) throws DoneTaskException {
        if (!tasks.get(completedTaskNo - 1).isDone()) {
            tasks.get(completedTaskNo - 1).markAsDone();
        } else {
            throw new DoneTaskException();
        }
    }

    public void deleteTask(int deletedTaskNo) {
        tasks.remove(tasks.get(deletedTaskNo - 1));
    }
}
