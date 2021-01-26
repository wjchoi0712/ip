import java.util.Scanner;

public class Duke {

    /**
     * Prints out the arguments with LINE_SEPARATOR surrounding it
     *
     * @param message The string message to be printed out
     * @param tasks The list of tasks to be printed out
     * @return No return value.
     */
    public static void printWithLine(String message, Task[] tasks) {
        final String LINE_SEPARATOR = "____________________________________________________________";
        if (tasks == null) {
            System.out.println(LINE_SEPARATOR + "\n" + message+ "\n" + LINE_SEPARATOR);
        } else {
            System.out.println(LINE_SEPARATOR);
            if (message != null) {
                System.out.println(message);
            }
            for (int i = 0; i < tasks.length; i++) {
                if (tasks[i] == null) {
                    break;
                }
                System.out.println(" " + (i + 1) + "." + tasks[i].getStatusAndDescription());
            }
            System.out.println(LINE_SEPARATOR);
        }
    }

    public static void main(String[] args) {
        final String LOGO = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        printWithLine(LOGO + "\n Hello! I'm Duke" + "\n What can I do for you?", null);

        Scanner in = new Scanner(System.in);
        String userInput = in.nextLine();

        Task[] tasks = new Task[100];
        int taskCounter = 0;

        while (!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                printWithLine(" Here are the tasks in your list:", tasks);
            } else if (userInput.startsWith("done")) {
                String[] temp = userInput.split(" ");
                int completedTaskNo = Integer.parseInt(temp[1])-1;
                tasks[completedTaskNo].markAsDone();
                printWithLine(" Nice! I've marked this task as done:" + "\n   "
                        + tasks[completedTaskNo].getStatusAndDescription(), null);
            } else {
                tasks[taskCounter] = new Task(userInput);
                printWithLine(" added: " + tasks[taskCounter].description, null);
                taskCounter++;
            }
            userInput = in.nextLine();
        }
        printWithLine(" Bye. Hope to see you again soon!", null);
    }
}
