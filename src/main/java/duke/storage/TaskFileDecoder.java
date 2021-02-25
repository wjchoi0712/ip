package duke.storage;

import duke.command.CommandType;
import duke.exception.InvalidCommandException;
import duke.exception.InvalidDescriptionException;
import duke.exception.LoadDataOperationException;
import duke.exception.NoDescriptionException;
import duke.parser.Parser;
import duke.task.TaskList;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

public class TaskFileDecoder {
    public TaskList decodeTaskData(Path path) throws LoadDataOperationException {
        try {
            TaskList tasks = new TaskList();
            Scanner taskData = new Scanner(path);
            while (taskData.hasNext()) {
                String data = taskData.nextLine();
                CommandType commandType = Parser.scanCommandType(data);
                String[] inputs = separateTaskDescriptionAndStatus(data);
                tasks.addTask(inputs[0], commandType);
                if (inputs[1].equals("X")) {
                    tasks.getTask(tasks.getTotalNoOfTasks() - 1).markAsDone();
                }
            }
            return tasks;
        } catch (IOException | InvalidCommandException | NoDescriptionException | InvalidDescriptionException e) {
            throw new LoadDataOperationException();
        }

    }

    public String[] separateTaskDescriptionAndStatus(String data) {
        String[] inputs = new String[2];
        inputs[0] = data.substring(0, data.indexOf('[') - 1);
        inputs[1] = data.substring(data.indexOf('[') + 1, data.indexOf(']'));
        return inputs;
    }
}
