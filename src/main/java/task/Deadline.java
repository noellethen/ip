package task;

/**
 * A class representing a Deadline task. Inherits from Task.
 * A Deadline task has a description and a deadline date.
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Constructor for Deadline task.
     * @param description the description of the task.
     * @param by the deadline date of the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Gets the deadline date for the task.
     * @return the deadline date.
     */
    public String getBy() {
        return by;
    }

    /**
     * Returns a string representation of the Deadline task.
     * @return the string representation of the Deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
