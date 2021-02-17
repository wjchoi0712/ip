package duke.task;

public class Task {

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    /**
     * Marks the isDone boolean to true
     * */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Returns the status icon of the task
     * */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }
}
