package duke.task;

import duke.command.CommandType;
import duke.exception.commandException.DoneTaskException;
import duke.exception.descriptionException.InvalidDeadlineDescriptionException;
import duke.exception.descriptionException.InvalidDescriptionException;
import duke.exception.descriptionException.InvalidEventDescriptionException;
import duke.exception.descriptionException.NoDescriptionException;
import duke.parser.Parser;

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

    public void addTask(String userInput, CommandType commandType) throws NoDescriptionException, InvalidDescriptionException, InvalidDeadlineDescriptionException, InvalidEventDescriptionException {
        String[] taskComponents;
        switch (commandType) {
        case TODO:
            String taskDescription = new Parser().prepareForAddTodo(userInput);
            tasks.add(new ToDo(taskDescription));
            break;
        case DEADLINE:
            taskComponents = new Parser().prepareForAddDeadline(userInput);
            tasks.add(new Deadline(taskComponents[0], taskComponents[1]));
            break;
        case EVENT:
            taskComponents = new Parser().prepareForAddEvent(userInput);
            tasks.add(new Event(taskComponents[0], taskComponents[2]));
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
