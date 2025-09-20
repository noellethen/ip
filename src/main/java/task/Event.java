package task;

/**
 * A class representing an Event task. Inherits from Task.
 * An Event task has a description and a start and end time/date.
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Constructor for Event task.
     * @param description the description of the event.
     * @param from the start time of the event.
     * @param to the end time of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Gets the start time of the event.
     * @return the start time.
     */
    public String getFrom() {
        return from;
    }

    /**
     * Gets the end time of the event.
     * @return the end time.
     */
    public String getTo() {
        return to;
    }

    /**
     * Returns a string representation of the Event task.
     * @return the string representation of the Event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
