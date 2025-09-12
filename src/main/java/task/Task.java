package task;

import java.util.ArrayList;

public class Task {
    private final String description;
    protected boolean isDone;
    private static final ArrayList<Task> taskList = new ArrayList<>();

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        taskList.add(this);
    }

    public void removeTask() {
        taskList.remove(this);
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmarkAsDone() {
        this.isDone = false;
    }

    public static int getTaskCount() {
        return taskList.size();
    }

    public static ArrayList<Task> getTaskList() {
        return taskList;
    }

    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + description;
    }
}
