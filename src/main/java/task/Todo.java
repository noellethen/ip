package task;

/**
 * A class representing a Todo task. Inherits from Task.
 * A Todo task has a description.
 */
public class Todo extends Task {

    /**
     * Constructor for Todo task.
     * @param description the description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the Todo task.
     * @return the string representation of the Todo task.
     */
    @Override
    public String toString() {
        return "[T]" +  super.toString();
    }
}
