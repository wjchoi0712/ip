package duke.ui;

import duke.exception.commandException.EmptyListException;
import duke.task.TaskList;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class TextUi {
    private static final String LS = System.lineSeparator();
    private static final String DIVIDER = "____________________________________________________________";
    private static final String LINE_PREFIX = "   ";
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

    private final Scanner in;
    private final PrintStream out;

    public TextUi() {
        this(System.in, System.out);
    }

    public TextUi(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public void showToUser(String... message) {
        out.println(DIVIDER);
        for (String m : message) {
            out.println(m);
        }
        out.println(DIVIDER);
    }

    public void showGreetingMessage() {
        showToUser(LOGO, MESSAGE_GREETING);
    }

    public void showEndingMessage() {
        showToUser(MESSAGE_ENDING);
    }

    public String getUserCommand() {
        return in.nextLine();
    }

    public void showTaskList(TaskList tasks) throws EmptyListException {
        boolean isEmpty = (tasks.getTotalNoOfTasks() == 0);
        if (!isEmpty) {
            out.println(DIVIDER + LS + MESSAGE_LISTTASK);
            for (int i = 0; i < tasks.getTotalNoOfTasks(); i++) {
                out.println(" " + (i + 1) + ". " + tasks.getTask(i).toString());
            }
            out.println(DIVIDER);
        } else {
            throw new EmptyListException();
        }
    }

    public void showAddResponse(TaskList tasks) {
        String addedTask = LINE_PREFIX + tasks.getLastTaskInTheList().toString();
        showToUser(MESSAGE_ADDTASK, addedTask, String.format(MESSAGE_REMAININGTASK, tasks.getTotalNoOfTasks()));
    }

    public void showDoneResponse(TaskList tasks, int taskNo) {
        String completedTask = LINE_PREFIX + tasks.getTask(taskNo - 1).toString();
        showToUser(MESSAGE_DONETASK, completedTask);
    }

    public void showDeleteResponse(TaskList tasks, int taskNo) {
        String deletedTask = LINE_PREFIX + tasks.getTask(taskNo - 1).toString();
        showToUser(MESSAGE_DELETETASK, deletedTask, String.format(MESSAGE_REMAININGTASK, tasks.getTotalNoOfTasks() - 1));
    }
}
