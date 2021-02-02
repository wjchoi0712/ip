import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    /**
     * Message lines that will be shown to the user
     * */
    private static final String LINE_SEPARATOR = "____________________________________________________________";
    private static final String LOGO = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n\n";
    private static final String MESSAGE_GREETING = " Hello! I'm Duke" + "\n What can I do for you?";
    private static final String MESSAGE_ENDING = " Bye. Hope to see you again soon!";
    private static final String MESSAGE_LIST = " Here are the tasks in your list:";
    private static final String MESSAGE_ADDTASK = " Got it. I've added this task:";
    private static final String MESSAGE_REMAININGTASK = " Now you have %d tasks in the list.";
    private static final String MESSAGE_DONETASK = "Nice! I've marked this task as done:";

    /**
     * Prints out the response to the user with line boundaries surrounding it
     *
     * @param respones message that is shown to the user
     *
     */
    public static void printResponse(String response) {
        System.out.println(LINE_SEPARATOR);
        System.out.println(response);
        System.out.println(LINE_SEPARATOR);
    }

    /**
     * Prints out the full list of tasks with line boundaries surrounding it
     *
     * @param tasks list of tasks to be printed out
     * @param response message that is shown to the user
     *
     */
    public static void printResponse(ArrayList<Task> tasks, String response) {
        System.out.println(LINE_SEPARATOR);
        System.out.println(response);
        for (int i = 0; i < tasks.size(); i ++) {
            System.out.println(" " + (i + 1) + ". " + tasks.get(i).toString());
        }
        System.out.println(LINE_SEPARATOR);
    }

    /**
     * Prints out either the newly added task or completed task
     *
     * @param tasks to access specific task in the Arraylist
     * @param response message that is shown to the user
     * @param taskNo either the newly added task no. or the completed task no.
     *
     */
    public static void printResponse(ArrayList<Task> tasks, String response, int taskNo) {
        System.out.println(LINE_SEPARATOR);
        System.out.println(response);
        if (response.equals(MESSAGE_ADDTASK)) {
            System.out.println("   " + tasks.get(taskNo - 1).toString());
            System.out.println(String.format(MESSAGE_REMAININGTASK, taskNo));
        } else {
            System.out.println("   " + tasks.get(taskNo - 1).toString());
        }
        System.out.println(LINE_SEPARATOR);
    }

    /**
     * Separates the userInput to description and time string
     *
     * @param description userInput to be separated
     * @return inputs description and time is stored in the string array
     *
     */
    public static String[] separateDescriptionAndTime(String description) {
        String[] inputs = new String[2];
        inputs[0] = description.substring(0, description.indexOf("/") - 1);
        inputs[1] = description.substring(description.indexOf("/") + 4);
        return inputs;
    }

    /**
     * Adds new task(either todo, deadline or event)
     *
     * @param tasks Arraylist where new tasks are stored
     * @param command the userInput which stores the description
     *                and time of the task
     *
     */
    public static void addTask(ArrayList<Task> tasks, String command) {
        String description = command.substring(command.indexOf(" ") + 1);
        String time;
        if (description.contains("/by")) {
            String[] inputs = separateDescriptionAndTime(description);
            tasks.add(new Deadline(inputs[0], inputs[1]));
        } else if (description.contains("/at")) {
            String[] inputs = separateDescriptionAndTime(description);
            tasks.add(new Event(inputs[0], inputs[1]));
        } else {
            tasks.add(new ToDo(description));
        }
    }

    /**
     * The central method which takes in input from the user and add, complete, print out tasks
     */
    private static void manageTasks() {
        ArrayList<Task> tasks = new ArrayList<Task>();
        int taskCounter = 0;

        Scanner in = new Scanner(System.in);
        String userInput = in.nextLine();

        while (!userInput.equals("bye")) {
            if (userInput.startsWith("todo") || userInput.startsWith("deadline") || userInput.startsWith("event")) {
                addTask(tasks, userInput);
                taskCounter++;
                printResponse(tasks, MESSAGE_ADDTASK, taskCounter);
            } else if (userInput.equals("list")) {
                printResponse(tasks, MESSAGE_LIST);
            } else if (userInput.startsWith("done")) {
                int completedTaskNo = Integer.parseInt(userInput.replace("done ", ""));
                tasks.get(completedTaskNo - 1).markAsDone();
                printResponse(tasks, MESSAGE_DONETASK, completedTaskNo);
            } else {
                tasks.add(new Task(userInput));
                taskCounter++;
                printResponse(tasks, MESSAGE_ADDTASK, taskCounter);
            }
            userInput = in.nextLine();
        }
    }

    public static void main(String[] args) {
        printResponse(MESSAGE_GREETING);
        manageTasks();
        printResponse(MESSAGE_ENDING);
    }
}
