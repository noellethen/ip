package task;

import java.util.ArrayList;

/**
 * A class representing a generic Task. It holds a description and tracks whether the task is completed.
 */
public class Task {
    protected final String description;
    protected boolean isDone;
    private static final ArrayList<Task> taskList = new ArrayList<>();

    /**
     * Constructor for Task class.
     * @param description the description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        taskList.add(this);
    }

    /**
     * Removes a task from the task list.
     */
    public void removeTask() {
        taskList.remove(this);
    }

    /**
     * Gets the description of the task.
     * @return the description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Marks a task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Unmarks a task as done.
     */
    public void unmarkAsDone() {
        this.isDone = false;
    }

    /**
     * Gets the total number of tasks in the list.
     * @return the task count.
     */
    public static int getTaskCount() {
        return taskList.size();
    }

    /**
     * Gets the task list.
     * @return the list of tasks.
     */
    public static ArrayList<Task> getTaskList() {
        return taskList;
    }

    /**
     * Formats the task for saving to file.
     * @return a formatted string representing the task for saving.
     */
    public String formatForSave() {
        return (isDone ? "1" : "0") + " | " + description;
    }

    /**
     * Returns a string representation of the task.
     * @return the string representation of the task.
     */
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + description;
    }
}
