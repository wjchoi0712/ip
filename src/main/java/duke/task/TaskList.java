package duke.task;

import duke.exception.description.InvalidDeadlineDescriptionException;
import duke.exception.description.InvalidDescriptionException;
import duke.exception.description.InvalidEventDescriptionException;
import duke.exception.description.NoDescriptionException;
import duke.parser.Parser;


import java.util.ArrayList;

/**
 * Represents a list of {@link Task}
 */
public class TaskList {

    protected ArrayList<Task> tasks;

    public TaskList() {
        this(new ArrayList<Task>());
    }

    /**
     * Constructs a list of tasks
     *
     * @param tasks list of ToDo, Event, Deadline tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /** Returns true when the task list is empty */
    public boolean isEmpty() {
        return tasks.size() == 0;
    }

    /** Returns a task at a specific index */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /** Returns the last task in the task list */
    public Task getLastTaskInTheList() {
        return tasks.get(tasks.size() - 1);
    }

    /** Returns the total number of tasks in the task list */
    public int getTotalNoOfTasks() {
        return tasks.size();
    }

    /** Adds a new task into task list */
    public void addTask(Task newTask) {
        tasks.add(newTask);
    }

    /**
     * Adds a new task into the task list by receiving command from user Input
     *
     * @param commandType type task to add
     * @param userInput full user input String
     * @throws NoDescriptionException if task description is empty
     * @throws InvalidDescriptionException if no time description is given for Deadline and event task
     * @throws InvalidDeadlineDescriptionException if "/by" is not included in the user input for deadline task
     * @throws InvalidEventDescriptionException if "/at" is not included in the user input for deadline task
     */
    public void addTask(String commandType, String userInput) throws NoDescriptionException,
            InvalidDescriptionException, InvalidDeadlineDescriptionException, InvalidEventDescriptionException {

        //Container to carry task description and task time
        String[] taskComponents;

        switch (commandType) {
        case "todo":
            String taskDescription = new Parser().prepareForAddTodo(userInput);
            tasks.add(new ToDo(taskDescription));
            break;
        case "deadline":
            taskComponents = new Parser().prepareForAddDeadline(userInput);
            tasks.add(new Deadline(taskComponents[0], taskComponents[1]));
            break;
        case "event":
            taskComponents = new Parser().prepareForAddEvent(userInput);
            tasks.add(new Event(taskComponents[0], taskComponents[1]));
            break;
        }
    }

    /**
     * Marks a specific task in the list as done
     *
     * @param completedTaskNo task number to be marked as done
     */
    public void completeTask(int completedTaskNo) {
        tasks.get(completedTaskNo - 1).markAsDone();
    }

    /**
     * Deletes a specific task in the list
     *
     * @param deletedTaskNo task number to be deleted
     */
    public void deleteTask(int deletedTaskNo) {
        tasks.remove(tasks.get(deletedTaskNo - 1));
    }

    /** Deletes all the tasks in the task list */
    public void clearTaskList() {
        tasks.clear();
    }


    /** Returns a full list of tasks as String */
    public String getTaskListAsString() {
        StringBuilder fullTaskList = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            fullTaskList.append(" " + (i + 1) + ". " + tasks.get(i).toString());
            fullTaskList.append(System.lineSeparator());
        }
        return fullTaskList.deleteCharAt(fullTaskList.lastIndexOf(System.lineSeparator())).toString();
    }
}
