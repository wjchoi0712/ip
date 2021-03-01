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

public class Parser {
    private static final String CONDITIONFORDEADLINE = "/by";
    private static final String CONDITIONFOREVENT = "/at";

    public static Command prepareForCommandExecution(String userInput) throws InvalidCommandException {
        Command command;
        CommandType commandType = Parser.scanCommandType(userInput);
        switch (commandType) {
        case BYE:
            command = new ByeCommand(userInput);
            break;
        case LIST:
            command = new ListCommand(userInput);
            break;
        case DONE:
            command = new DoneCommand(userInput);
            break;
        case DELETE:
            command = new DeleteCommand(userInput);
            break;
        case FIND:
            command = new FindCommand(userInput);
            break;
        case TODO:
        case EVENT:
        case DEADLINE:
            command = new AddCommand(userInput, commandType);
            break;
        default:
            throw new InvalidCommandException();
        }
        return command;
    }

    public static CommandType scanCommandType(String userInput) throws InvalidCommandException {
        CommandType commandType;
        if (userInput.equals("bye")) {
            commandType = CommandType.BYE;
        } else if (userInput.equals("list")) {
            commandType = CommandType.LIST;
        } else if (userInput.startsWith("done")) {
            commandType = CommandType.DONE;
        } else if (userInput.startsWith("delete")) {
            commandType = CommandType.DELETE;
        } else if (userInput.startsWith("find")) {
            commandType = CommandType.FIND;
        } else if (userInput.startsWith("todo")) {
            commandType = CommandType.TODO;
        } else if (userInput.startsWith("deadline")) {
            commandType = CommandType.DEADLINE;
        } else if (userInput.startsWith("event")) {
            commandType = CommandType.EVENT;
        } else {
            throw new InvalidCommandException();
        }
        return commandType;
    }

    public boolean isWithinBound(TaskList tasks, int taskNo) {
        return (taskNo <= tasks.getTotalNoOfTasks() && taskNo > 0);
    }

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

    public String[] prepareForAddDeadline(String userInput) throws InvalidDescriptionException, NoDescriptionException, InvalidDeadlineDescriptionException {
        String taskDescription = prepareForAddTodo(userInput);
        if (userInput.contains(CONDITIONFORDEADLINE)) {
            return separateDescriptionAndTime(taskDescription);
        } else {
            throw new InvalidDeadlineDescriptionException();
        }
    }

    public String[] prepareForAddEvent(String userInput) throws InvalidDescriptionException, NoDescriptionException, InvalidEventDescriptionException {
        String taskDescription = prepareForAddTodo(userInput);
        if (userInput.contains(CONDITIONFOREVENT)) {
            return separateDescriptionAndTime(taskDescription);
        } else {
            throw new InvalidEventDescriptionException();
        }
    }

    public String[] separateDescriptionAndTime(String description) throws InvalidDescriptionException {
        String[] inputs = new String[2];
        boolean isDescriptionEmpty = (description.indexOf("/") == 0);
        boolean isTimeEmpty = (description.indexOf("/") + 4 == description.length() + 1);

        if (!isDescriptionEmpty && !isTimeEmpty) {
            inputs[0] = description.substring(0, description.indexOf("/") - 1);
            String time = description.substring(description.indexOf("/") + 4);
            try {
                LocalDate dueDate = LocalDate.parse(time);
                inputs[1] = dueDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            } catch (DateTimeParseException e) {
                inputs[1] = time;
            }
        } else {
            throw new InvalidDescriptionException();
        }
        return inputs;
    }

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