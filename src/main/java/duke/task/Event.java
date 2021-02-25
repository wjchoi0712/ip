package duke.task;

public class Event extends Task {
    protected String eventDate;

    public Event(String description, String eventTime) {
        super(description);
        this.eventDate = eventTime;
    }

    @Override
    public String getType() {
        return "event";
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " /at " + eventDate;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + eventDate + ")";
    }
}
