import java.util.Scanner;

public class Duke {

    //Prints out the argument with line surrounding it
    public static void printWithLine(String str) {
        String line = "\t____________________________________________________________";
        System.out.println(line + System.lineSeparator() + "\t " + str + System.lineSeparator() + line);
    }

    public static void main(String[] args) {
        printWithLine("Hello! I'm Duke" + System.lineSeparator() + "\t What can I do for you?");
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        while (!line.equals("bye")) {
            printWithLine(line);
            line = in.nextLine();
        }
        printWithLine("Bye. Hope to see you again soon!");
    }
}
