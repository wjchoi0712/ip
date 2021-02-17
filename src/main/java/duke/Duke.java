package duke;

import java.util.Scanner;
import java.util.ArrayList;
import duke.exception.*;
import duke.task.*;
import java.io.*;

public class Duke {

    /**
     * Message lines that will be shown to the user
     */
    private static final String LINE_SEPARATOR = "____________________________________________________________";
    private static final String LOGO = " ____        _\n"
            + "|  _ \\ _   _| | _____\n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n\n";
    private static final String MESSAGE_GREETING = " Hello! I'm Duke" + "\n What can I do for you?";
    private static final String MESSAGE_ENDING = " Bye. Hope to see you again soon!";
    private static final String MESSAGE_LISTTASK = " Here are the tasks in your list:";
    private static final String MESSAGE_ADDTASK = " Got it. I've added this task:";
    private static final String MESSAGE_REMAININGTASK = " Now you have %d tasks in the list.";
    private static final String MESSAGE_DONETASK = "Nice! I've marked this task as done:";

    public static void printResponse(String response) {
        System.out.println(LINE_SEPARATOR);
        System.out.println(response);
        System.out.println(LINE_SEPARATOR);
    }

    public static void printResponse(ArrayList<Task> tasks, String response, int taskNo) {
        System.out.println(LINE_SEPARATOR);
        System.out.println(response);
        if (response.equals(MESSAGE_ADDTASK)) {
            System.out.println("   " + tasks.get(taskNo - 1).toString());
            System.out.println(String.format(MESSAGE_REMAININGTASK, tasks.size()));
        } else {
            System.out.println("   " + tasks.get(taskNo - 1).toString());
        }
        System.out.println(LINE_SEPARATOR);
    }

    public static void printList(ArrayList<Task> tasks) throws EmptyListException {
        System.out.println(LINE_SEPARATOR);
        if (tasks.size() != 0) {
            System.out.println(MESSAGE_LISTTASK);
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(" " + (i + 1) + ". " + tasks.get(i).toString());
            }
        } else {
            throw new EmptyListException();
        }
        System.out.println(LINE_SEPARATOR);
    }

    public static String getDescriptionOfTask(String userInput) {
        return userInput.substring(userInput.indexOf(" ") + 1);
    }

    public static String[] separateDescriptionAndTime(String description) {
        String[] inputs = new String[2];

        //Get just the task description
        inputs[0] = description.substring(0, description.indexOf("/") - 1);

        //Get the due date
        inputs[1] = description.substring(description.indexOf("/") + 4);
        return inputs;
    }

    public static void addTask(ArrayList<Task> tasks, String userInput, CommandType command) throws NoDescriptionException, InvalidDescriptionException {
        if (userInput.contains(" ")) {
            String description = getDescriptionOfTask(userInput);

            switch (command) {
            case TODO:
                tasks.add(new ToDo(description));
                break;
            case DEADLINE:
                if (description.contains("/by")) {
                    String[] inputs = separateDescriptionAndTime(description);
                    tasks.add(new Deadline(inputs[0], inputs[1]));
                } else {
                    throw new InvalidDescriptionException();
                }
                break;
            case EVENT:
                if (description.contains("/at")) {
                    String[] inputs = separateDescriptionAndTime(description);
                    tasks.add(new Event(inputs[0], inputs[1]));
                } else {
                    throw new InvalidDescriptionException();
                }
                break;
            }
        } else {
            throw new NoDescriptionException();
        }
    }

    public static CommandType scanCommand(String userInput) throws InvalidCommandException {
        CommandType command;
        if (userInput.equals("bye")) {
            command = CommandType.BYE;
        } else if (userInput.equals("list")) {
            command = CommandType.LIST;
        } else if (userInput.startsWith("done")) {
            command = CommandType.DONE;
        } else if (userInput.startsWith("todo")) {
            command = CommandType.TODO;
        } else if (userInput.startsWith("deadline")) {
            command = CommandType.DEADLINE;
        } else if (userInput.startsWith("event")) {
            command = CommandType.EVENT;
        } else {
            throw new InvalidCommandException();
        }
        return command;
    }

    public static void manageTasks() {
        ArrayList<Task> tasks = new ArrayList<Task>();
        boolean runDuke = true;
        File f = new File("duke.txt");
        try {
            loadPrevData(tasks, "duke.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Scanner in = new Scanner(System.in);
        while (runDuke) {
            String userInput = in.nextLine();
            try {
                CommandType command = scanCommand(userInput);
                switch (command) {
                case BYE:
                    runDuke = false;
                    break;
                case LIST:
                    printList(tasks);
                    break;
                case DONE:
                    int completedTaskNo = Integer.parseInt(userInput.replace("done ", ""));
                    tasks.get(completedTaskNo - 1).markAsDone();
                    printResponse(tasks, MESSAGE_DONETASK, completedTaskNo);
                    updateData(tasks);
                    break;
                case TODO:
                case EVENT:
                case DEADLINE:
                    addTask(tasks, userInput, command);
                    printResponse(tasks, MESSAGE_ADDTASK, tasks.size());
                    AddTasksasTxt(tasks, tasks.size() - 1);
                    break;
                }
            } catch (InvalidCommandException e) {
                printResponse(e.getMessage());
            } catch (EmptyListException e) {
                printResponse(e.getMessage());
            } catch (NoDescriptionException e) {
                printResponse(e.getMessage());
            } catch (InvalidDescriptionException e) {
                printResponse(e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void AddTasksasTxt(ArrayList<Task> tasks, int index) throws IOException {
        appendToFile(tasks.get(index).toString());
    }

    public static void updateData(ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter("duke.txt");
        fw.write("");
        for (Task t : tasks) {
            appendToFile(t.toString());
        }
    }

    public static void appendToFile(String textToAppend) throws IOException {
        FileWriter fw = new FileWriter("duke.txt", true);
        fw.write(textToAppend + System.lineSeparator());
        fw.close();
    }

    public static void loadPrevData(ArrayList<Task> tasks, String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String data = s.nextLine();
            char taskType = data.charAt(1);
            String description = data.substring(7);
            String detail;
            String time;
            switch (taskType) {
            case 'T':
                tasks.add(new ToDo(description));
                break;
            case 'D':
                detail = description.substring(0, description.indexOf("by:") - 2);
                time = description.substring(description.indexOf("by:") + 4, description.length() - 1);
                tasks.add(new Deadline(detail, time));
                break;
            case 'E':
                detail = description.substring(0, description.indexOf("at:") - 2);
                time = description.substring(description.indexOf("at:") + 4, description.length() - 1);
                tasks.add(new Deadline(detail, time));
                break;
            }
            if (data.charAt(4) == 'X') {
                tasks.get(tasks.size() - 1).markAsDone();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        printResponse(LOGO + MESSAGE_GREETING);
        manageTasks();
        printResponse(MESSAGE_ENDING);
    }
}
