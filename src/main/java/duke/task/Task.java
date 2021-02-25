package duke.task;

public class Task {

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    public boolean isDone() {
        return isDone;
    }

    public String getType() {
        return "task";
    }

    public String getDescription() {
        return description;
    }

    public void markAsDone() {
        isDone = true;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }


    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }
}
