public class Deadline extends Task {
    private String by;

    public Deadline(String description, String dueDate) {
        super(description);
        this.by = dueDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
