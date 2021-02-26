package duke.storage;

import duke.command.CommandType;
import duke.exception.action.InvalidCommandException;
import duke.exception.data.LoadDataOperationException;
import duke.exception.description.DescriptionException;
import duke.parser.Parser;
import duke.task.TaskList;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

public class TaskFileDecoder {
    public TaskList decodeTaskData(Path path) throws LoadDataOperationException {
        TaskList tasks = new TaskList();
        try {
            Scanner s = new Scanner(path);
            while (s.hasNext()) {
                String data = s.nextLine();
                CommandType commandType = Parser.scanCommandType(data);
                String[] inputs = separateTaskDescriptionAndStatus(data);
                tasks.addTask(commandType, inputs[0]);
                if (inputs[1].equals("\u2705")) {
                    tasks.getLastTaskInTheList().markAsDone();
                }
            }
        } catch (IOException | InvalidCommandException | DescriptionException e) {
            throw new LoadDataOperationException();
        }
        return tasks;
    }

    public String[] separateTaskDescriptionAndStatus(String data) {
        String[] inputs = new String[2];
        inputs[0] = data.substring(0, data.indexOf('[') - 1);
        inputs[1] = data.substring(data.indexOf('[') + 1, data.indexOf(']'));
        return inputs;
    }
}
