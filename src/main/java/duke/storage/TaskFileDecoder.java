package duke.storage;

import duke.command.CommandType;
import duke.exception.commandException.InvalidCommandException;
import duke.exception.descriptionException.InvalidDeadlineDescriptionException;
import duke.exception.descriptionException.InvalidDescriptionException;
import duke.exception.descriptionException.InvalidEventDescriptionException;
import duke.exception.descriptionException.NoDescriptionException;
import duke.exception.storageException.LoadDataOperationException;
import duke.parser.Parser;
import duke.task.TaskList;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

public class TaskFileDecoder {
    public TaskList decodeTaskData(Path path) throws LoadDataOperationException {
        TaskList tasks = new TaskList();
        try {
            Scanner taskData = new Scanner(path);
            while (taskData.hasNext()) {
                String data = taskData.nextLine();
                CommandType commandType = Parser.scanCommandType(data);
                String[] inputs = separateTaskDescriptionAndStatus(data);
                tasks.addTask(inputs[0], commandType);
                if (inputs[1].equals("\u2705")) {
                    tasks.getTask(tasks.getTotalNoOfTasks() - 1).markAsDone();
                }
            }
        } catch (IOException | InvalidCommandException | NoDescriptionException | InvalidDescriptionException e) {
            throw new LoadDataOperationException();
        } catch (InvalidEventDescriptionException e) {
            e.printStackTrace();
        } catch (InvalidDeadlineDescriptionException e) {
            e.printStackTrace();
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
