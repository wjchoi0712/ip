package duke.task;

public class Event extends Task {
    protected String eventTime;

    public Event(String description, String eventTime) {
        super(description);
        this.eventTime = eventTime;
    }

    @Override
    public String getType() {
        return "event";
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " /at " + eventTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + eventTime + ")";
    }
}
