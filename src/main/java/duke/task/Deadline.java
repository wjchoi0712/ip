package duke.task;

/**
 * Represents an Deadline type task with description and an indicator of whether the task has been completed or not.
 * Unlike ToDo type, Deadline task has an extra component of time, which represents the due date of the task.
 */
public class Deadline extends Task {
    protected String dueDate;

    /**
     * Constructs an Deadline task from a String of description and the due date of the task.
     * Also sets the boolean variable which represents whether the task has be completed to false.
     *
     * @param description description of the task
     * @param dueDate date when the task must be completed by
     *
     */
    public Deadline(String description, String dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    /** Returns the task description and the due date of the task */
    @Override
    public String getDescription() {
        return super.getDescription() + " /by " + dueDate;
    }

    /** Returns a String representation of the task type */
    @Override
    public String getType() {
        return "deadline";
    }

    /**
     * Returns a String representation of the deadline task which includes
     * the status of the task, task description and due date.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dueDate + ")";
    }
}
