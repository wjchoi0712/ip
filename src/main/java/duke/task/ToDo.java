package duke.task;

/**
 * Represents a ToDo type task with description and an indicator of whether the task has been completed or not.
 */
public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String getDescription() {
        return super.getDescription();
    }

    /** Returns a String representation of the task type */
    @Override
    public String getType() {
        return "todo";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
