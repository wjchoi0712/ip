package duke.task;

import duke.command.CommandType;
import duke.parser.Parser;
import duke.exception.InvalidDescriptionException;
import duke.exception.NoDescriptionException;

import java.util.ArrayList;

public class TaskList {

    protected ArrayList<Task> tasks;

    public TaskList() {
        this(new ArrayList<Task>());
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
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

    public boolean isEmpty() {
        return tasks.size() == 0;
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

    public void addTask(Task newTask) {
        tasks.add(newTask);
    }


    public void completeTask(int completedTaskNo) {
        tasks.get(completedTaskNo - 1).markAsDone();
    }

    public void deleteTask(int deletedTaskNo) {
        tasks.remove(tasks.get(deletedTaskNo - 1));
    }

    public String getTaskListAsString() {
        StringBuilder fullTaskList = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            fullTaskList.append(" " + (i + 1) + ". " + tasks.get(i).toString());
            fullTaskList.append(System.lineSeparator());
        }
        return fullTaskList.deleteCharAt(fullTaskList.lastIndexOf(System.lineSeparator())).toString();

    }
}
