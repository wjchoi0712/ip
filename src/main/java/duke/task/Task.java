package duke.task;

/**
 * Represents a task with description and an indicator of whether the task has been completed or not.
 */
public class Task {

    protected String description;
    protected boolean isDone;

    /**
     * Constructs a task from a String of description and sets the boolean
     * variable which represents whether the task has be completed to false.
     *
     * @param description description of the task
     */
    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    public String getDescription() {
        return description;
    }

    /** Returns true when the task has been completed */
    public boolean isDone() {
        return isDone;
    }

    /** Returns a String representation of the task type */
    public String getType() {
        return "task";
    }

    public void markAsDone() {
        isDone = true;
    }

    /**
     * Returns a tick mark(\u2705) with brackets surrounding it when the task has been marked as done.
     * Else return a blank space with brackets when the task is incomplete.
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    /**
     * Returns a String representation of the task which includes
     * the status of the task and the task description.
     */
    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }
}
