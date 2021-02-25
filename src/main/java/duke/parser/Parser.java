package duke.parser;

import duke.command.*;
import duke.exception.InvalidCommandException;
import duke.exception.InvalidTaskNoException;
import duke.exception.NoDescriptionException;

public class Parser {
    private static final String CONDITIONFORDEADLINE = "/by";
    private static final String CONDITIONFOREVENT = "/at";

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

    public static Command prepareForCommandExecution(String userInput) throws InvalidCommandException {
        Command command = new Command(userInput);
        command.setCommandType(scanCommandType(userInput));
        switch (command.getCommandType()) {
        case BYE:
            command = new CommandBye(userInput);
            break;
        case LIST:
            command = new CommandList(userInput);
            break;
        case DONE:
            command = new CommandDone(userInput);
            break;
        case DELETE:
            command = new CommandDelete(userInput);
            break;
        case TODO:
        case EVENT:
        case DEADLINE:
            command = new CommandAdd(userInput);
            break;
        default:
            throw new InvalidCommandException();
        }
        return command;
    }

    public int prepareForDoneOrDelete(String userInput, String commandType, int totalNoOfTasks) throws InvalidTaskNoException {
        try {
            int TaskNo = Integer.parseInt(userInput.replace(commandType, ""));
            if (TaskNo > totalNoOfTasks || TaskNo < 1) {
                throw new InvalidTaskNoException();
            } else {
                return TaskNo;
            }
        } catch (NumberFormatException e) {
            throw new InvalidTaskNoException();
        }
    }

    public String[] prepareForAdd(String userInput) throws NoDescriptionException {
        String[] taskComponent = {"", "", ""};
        if (userInput.contains(" ")) {
            taskComponent[0] = getDescriptionOfTask(userInput);
            if (taskComponent[0].contains(CONDITIONFORDEADLINE)) {
                taskComponent = separateDescriptionAndTime(taskComponent[0], CONDITIONFORDEADLINE);
            } else if (taskComponent[0].contains(CONDITIONFOREVENT)) {
                taskComponent = separateDescriptionAndTime(taskComponent[0], CONDITIONFOREVENT);
            }
        } else {
            throw new NoDescriptionException();
        }
        return taskComponent;
    }

    public String getDescriptionOfTask(String userInput) {
        return userInput.substring(userInput.indexOf(" ") + 1);
    }

    public String[] separateDescriptionAndTime(String description, String condition) {
        String[] inputs = new String[3];
        //Extract out just the task description
        inputs[0] = description.substring(0, description.indexOf("/") - 1);

        //Extract out the time component
        String time = description.substring(description.indexOf("/") + 4);

        switch (condition) {
        case CONDITIONFORDEADLINE:
            inputs[1] = time;
            break;
        case CONDITIONFOREVENT:
            inputs[2] = time;
        }
        return inputs;
    }
}
