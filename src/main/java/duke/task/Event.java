package duke.task;

/**
 * Represents an Event type task with description and an indicator of whether the task has been completed or not.
 * Like Deadline type, Event task has an extra component of time, which represents the event date of the task.
 */
public class Event extends Task {
    protected String eventDate;

    /**
     * Constructs an Event task from a String of description and the date of the event.
     * Also sets the boolean variable which represents whether the task has be completed to false.
     *
     * @param description description of the task
     * @param eventDate date when the event is to take place
     *
     */
    public Event(String description, String eventDate) {
        super(description);
        this.eventDate = eventDate;
    }

    /** Returns the task description and the date of the event */
    @Override
    public String getDescription() {
        return super.getDescription() + " /at " + eventDate;
    }

    /** Returns a String representation of the task type */
    @Override
    public String getType() {
        return "event";
    }

    /**
     * Returns a String representation of the event which includes
     * the status of the task, task description and date of the event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + eventDate + ")";
    }
}
