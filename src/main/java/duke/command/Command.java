package duke.command;

import duke.exception.action.InvalidCommandException;
import duke.exception.data.SaveDataOperationException;
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

    public boolean isExit() {
        return commandType == CommandType.BYE;
    }

    public void execute(TaskList tasks, TextUi ui, Storage storage) throws SaveDataOperationException {
    }
}
