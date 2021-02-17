package duke;

import java.util.Scanner;
import java.util.ArrayList;
import duke.exception.*;
import duke.task.*;

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
    private static final String MESSAGE_DELETETASK = " Noted. I've removed this task:";
    private static final String MESSAGE_REMAININGTASK = " Now you have %d tasks in the list.";
    private static final String MESSAGE_DONETASK = "Nice! I've marked this task as done:";

    public static void printResponse(String response) {
        System.out.println(LINE_SEPARATOR);
        System.out.println(response);
        System.out.println(LINE_SEPARATOR);
    }

    public static void printAddResponse(ArrayList<Task> tasks, int taskNo) {
        System.out.println(LINE_SEPARATOR);
        System.out.println(MESSAGE_ADDTASK);
        System.out.println("   " + tasks.get(taskNo - 1).toString());
        System.out.println(String.format(MESSAGE_REMAININGTASK, tasks.size()));
        System.out.println(LINE_SEPARATOR);
    }

    public static void printDeleteResponse(ArrayList<Task> tasks, int taskNo) {
        System.out.println(LINE_SEPARATOR);
        System.out.println(MESSAGE_ADDTASK);
        System.out.println("   " + tasks.get(taskNo - 1).toString());
        System.out.println(String.format(MESSAGE_REMAININGTASK, tasks.size() - 1));
        System.out.println(LINE_SEPARATOR);
    }

    public static void printDoneResponse(ArrayList<Task> tasks, int taskNo) {
        System.out.println(LINE_SEPARATOR);
        System.out.println(MESSAGE_DONETASK);
        System.out.println("   " + tasks.get(taskNo - 1).toString());
        System.out.println(LINE_SEPARATOR);
    }

    public static void printList(ArrayList<Task> tasks) throws EmptyListException {
        if (tasks.size() != 0) {
            System.out.println(LINE_SEPARATOR);
            System.out.println(MESSAGE_LISTTASK);
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(" " + (i + 1) + ". " + tasks.get(i).toString());
            }
            System.out.println(LINE_SEPARATOR);
        } else {
            throw new EmptyListException();
        }
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
        } else if (userInput.startsWith("delete")) {
            command = CommandType.DELETE;
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

    private static void manageTasks() {
        ArrayList<Task> tasks = new ArrayList<Task>();
        boolean runDuke = true;
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
                    printDoneResponse(tasks, completedTaskNo);
                    break;
                case DELETE:
                    int deletedTaskNo = Integer.parseInt(userInput.replace("delete ", ""));
                    printDeleteResponse(tasks, deletedTaskNo);
                    tasks.remove(tasks.get(deletedTaskNo-1));
                    break;
                case TODO:
                case EVENT:
                case DEADLINE:
                    addTask(tasks, userInput, command);
                    printAddResponse(tasks, tasks.size());
                    break;
                }
            } catch (InvalidCommandException e) {
                printResponse(e.getMessage());
            } catch (EmptyListException e) {
                printResponse(e.getMessage());
            } catch (NoDescriptionException e) {
                printResponse(e.getMessage());
            } catch (InvalidDescriptionException e){
                printResponse(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        printResponse(LOGO + MESSAGE_GREETING);
        manageTasks();
        printResponse(MESSAGE_ENDING);
    }
}
