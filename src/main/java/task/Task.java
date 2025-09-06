package task;

public class Task {
    private final String description;
    protected boolean isDone;
    private static int taskCount = 0;

    private static final int MAX_TASKS = 100;
    private static Task[] taskList = new Task[MAX_TASKS];

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        taskList[taskCount] = this;
        taskCount++;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmarkAsDone() {
        this.isDone = false;
    }

    public static int getTaskCount() {
        return taskCount;
    }

    public static Task[] getTaskList() {
        return taskList;
    }

    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + description;
    }
}
