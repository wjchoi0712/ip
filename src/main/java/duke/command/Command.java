package duke.command;

import duke.exception.EmptyListException;
import duke.exception.InvalidCommandException;
import duke.exception.SaveDataOperationException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.TextUi;

public class Command {

    protected CommandType commandType;
    protected String userInput;

    public Command(String userInput) throws InvalidCommandException {
        this.userInput = userInput;
        this.commandType = Parser.scanCommandType(userInput);
    }

    public CommandType getCommandType() {
        return commandType;
    }

    public void setCommandType(CommandType commandType) {
        this.commandType = commandType;
    }

    public void execute(TaskList tasks, TextUi ui, Storage storage) throws SaveDataOperationException {
    }

    public boolean isExit() {
        return commandType == CommandType.BYE;
    }
}
