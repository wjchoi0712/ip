package duke.parser;


import duke.command.*;
import duke.exception.action.DoneTaskException;
import duke.exception.action.InvalidCommandException;
import duke.exception.action.InvalidTaskNoException;
import duke.exception.description.InvalidDeadlineDescriptionException;
import duke.exception.description.InvalidDescriptionException;
import duke.exception.description.InvalidEventDescriptionException;
import duke.exception.description.NoDescriptionException;
import duke.task.Task;
import duke.task.TaskList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Parses user input.
 */
public class Parser {

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws InvalidCommandException if user command is not one of the command type that the program can execute
     */
    public static Command prepareForCommandExecution(String userInput) throws InvalidCommandException {
        Command command;
        String strippedUserInput = userInput.strip();

        if (strippedUserInput.equals("bye")) {
            command = new ByeCommand(strippedUserInput);
        } else if (strippedUserInput.startsWith("todo")) {
            command = new AddCommand(strippedUserInput, "todo");
        } else if (strippedUserInput.startsWith("deadline")) {
            command = new AddCommand(strippedUserInput, "deadline");
        } else if (strippedUserInput.startsWith("event")) {
            command = new AddCommand(strippedUserInput, "event");
        } else if (strippedUserInput.startsWith("done")) {
            command = new DoneCommand(strippedUserInput);
        } else if (strippedUserInput.startsWith("delete")) {
            command = new DeleteCommand(strippedUserInput);
        } else if (strippedUserInput.equals("list")) {
            command = new ListCommand(strippedUserInput);
        } else if (strippedUserInput.startsWith("find")) {
            command = new FindCommand(strippedUserInput);
        } else if (strippedUserInput.equals("clear")) {
            command = new ClearCommand(strippedUserInput);
        } else if (strippedUserInput.equals("help")) {
            command = new HelpCommand(strippedUserInput);
        } else {
            throw new InvalidCommandException();
        }
        return command;
    }

    /**
     * Parses user input into task description for {@link duke.task.ToDo} task
     *
     * @param userInput full user input string
     * @return the task description
     * @throws NoDescriptionException if user input does not contain task description
     */
    public String prepareForAddTodo(String userInput) throws NoDescriptionException {
        try {
            String taskDescription = userInput.substring(userInput.indexOf(" "));
            if (taskDescription.isBlank()) {
                throw new NoDescriptionException();
            } else {
                return taskDescription.strip();
            }
        } catch (StringIndexOutOfBoundsException e) {
            throw new NoDescriptionException();
        }
    }

    private static final String CONDITION_FOR_DEADLINE = "/by";
    private static final String CONDITION_FOR_EVENT = "/at";

    /**
     * Parses user input into task description and due date for {@link duke.task.Deadline} task
     *
     * @param userInput full user input string
     * @return the task description and due date
     * @throws NoDescriptionException if user input does not contain task description
     * @throws InvalidDescriptionException if user input does not contain task description or due date
     * @throws InvalidDeadlineDescriptionException if user input does not contain {@code CONDITION_FOR_DEADLINE}
     */
    public String[] prepareForAddDeadline(String userInput) throws InvalidDescriptionException,
            NoDescriptionException, InvalidDeadlineDescriptionException {

        String taskDescriptionAndDate = prepareForAddTodo(userInput);
        if (userInput.contains(CONDITION_FOR_DEADLINE)) {
            return separateDescriptionAndDate(taskDescriptionAndDate);
        } else {
            throw new InvalidDeadlineDescriptionException();
        }
    }

    /**
     * Parses user input into task description and event date for {@link duke.task.Event} task
     *
     * @param userInput full user input string
     * @return the task description and event date
     * @throws NoDescriptionException if user input does not contain task description
     * @throws InvalidDescriptionException if user input does not contain task description or event date
     * @throws InvalidEventDescriptionException if user input does not contain {@code CONDITION_FOR_EVENT}
     */
    public String[] prepareForAddEvent(String userInput) throws InvalidDescriptionException,
            NoDescriptionException, InvalidEventDescriptionException {

        String taskDescriptionAndDate = prepareForAddTodo(userInput);
        if (userInput.contains(CONDITION_FOR_EVENT)) {
            return separateDescriptionAndDate(taskDescriptionAndDate);
        } else {
            throw new InvalidEventDescriptionException();
        }
    }

    /**
     * Separates the user input into task description and date component.
     * Also parse the date into MMM D YYY format if date input by user is in YYYY-MM-DD format.
     *
     * @param descriptionAndDate a String containing both task description and task date
     * @return the {@code taskComponents} containing task description and date as elements
     * @throws InvalidDescriptionException if user input does not contain task description or date
     */
    public String[] separateDescriptionAndDate(String descriptionAndDate) throws InvalidDescriptionException {

        //Container to carry the task description and date
        String[] taskComponents = new String[2];

        boolean isDescriptionEmpty = (descriptionAndDate.indexOf("/") == 0);
        boolean isDateEmpty = (descriptionAndDate.indexOf("/") + 4 == descriptionAndDate.length() + 1);

        if (!isDescriptionEmpty && !isDateEmpty) {
            taskComponents[0] = descriptionAndDate.substring(0, descriptionAndDate.indexOf("/") - 1);
            String date = descriptionAndDate.substring(descriptionAndDate.indexOf("/") + 4);
            try {
                LocalDate dueDate = LocalDate.parse(date);
                taskComponents[1] = dueDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            } catch (DateTimeParseException e) {
                //When date input by the user is not in YYYY-MM-DD format, just store date as String
                taskComponents[1] = date;
            }
        } else {
            throw new InvalidDescriptionException();
        }
        return taskComponents;
    }

    /**
     * Parses user input into task number to mark as done
     *
     * @param tasks task list containing the task to be mark as done
     * @return the task number to be marked as done
     * @throws InvalidTaskNoException if task number is out of bound or is not an integer
     * @throws DoneTaskException if the task is already marked as done
     */
    public int prepareForDone(TaskList tasks, String userInput) throws InvalidTaskNoException, DoneTaskException {
        try {
            int taskNo = Integer.parseInt(userInput.replace("done ", ""));
            if (isWithinBound(tasks, taskNo)) {
                if (tasks.getTask(taskNo - 1).isDone()) {
                    throw new DoneTaskException();
                } else {
                    return taskNo;
                }
            } else {
                throw new InvalidTaskNoException();
            }
        } catch (NumberFormatException e) {
            throw new InvalidTaskNoException();
        }
    }

    /**
     * Parses user input into task number to delete
     *
     * @param tasks task list containing the task to be deleted
     * @return the task number to be deleted
     * @throws InvalidTaskNoException if task number is out of bound or is not an integer
     */
    public int prepareForDelete(TaskList tasks, String userInput) throws InvalidTaskNoException {
        try {
            int taskNo = Integer.parseInt(userInput.replace("delete ", ""));
            if (isWithinBound(tasks, taskNo)) {
                return taskNo;
            } else {
                throw new InvalidTaskNoException();
            }
        } catch (NumberFormatException e) {
            throw new InvalidTaskNoException();
        }
    }

    /** Returns true when the task number is an integer number that is within bound of the {@link TaskList} */
    public boolean isWithinBound(TaskList tasks, int taskNo) {
        return (taskNo <= tasks.getTotalNoOfTasks() && taskNo > 0);
    }

    /**
     * Select out the {@link Task} which contains the user's keyword in its description
     *
     * @param tasks task list to search for keyword
     * @return the list of matching tasks
     */
    public TaskList prepareForFind(TaskList tasks, String userInput) {
        TaskList matchingTasks = new TaskList();
        String keyword = userInput.replace("find ", "");
        for (int i = 0; i < tasks.getTotalNoOfTasks(); i++) {
            Task currentTask = tasks.getTask(i);
            if (currentTask.getDescription().contains(keyword)) {
                matchingTasks.addTask(currentTask);
            }
        }
        return matchingTasks;
    }
}