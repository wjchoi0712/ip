import java.util.Scanner;

public class Duke {

    //Prints out the input with line surrounding it
    public static void printWithLine(String message, String tasks[]) {
        final String LINE_SEPARATOR = "____________________________________________________________";
        if (tasks == null) {
            System.out.println(LINE_SEPARATOR + "\n" + message+ "\n" + LINE_SEPARATOR);
        } else {
            System.out.println(LINE_SEPARATOR);
            for (int i = 0; i < tasks.length; i++) {
                if (tasks[i] == null) {
                    break;
                }
                System.out.println(" " + (i + 1) + ". " + tasks[i]);
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

        String[] tasks = new String[100];
        int taskCounter = 0;

        while (!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                printWithLine(null, tasks);
            } else{
                tasks[taskCounter] = userInput;
                printWithLine(" added: " + tasks[taskCounter], null);
                taskCounter++;
            }
            userInput = in.nextLine();
        }
        printWithLine(" Bye. Hope to see you again soon!", null);
    }
}
