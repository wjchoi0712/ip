package duke.task;

import duke.command.CommandType;

import duke.exception.descriptionException.InvalidDeadlineDescriptionException;
import duke.exception.descriptionException.InvalidDescriptionException;
import duke.exception.descriptionException.InvalidEventDescriptionException;
import duke.exception.descriptionException.NoDescriptionException;

import duke.parser.Parser;

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

    public boolean isEmpty() {
        return tasks.size() == 0;
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
