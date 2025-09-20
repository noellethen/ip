package task;

import exception.PoodleException;
import parser.Parser;
import storage.Storage;
import ui.Ui;

import java.util.ArrayList;

public class TaskList {

    private final Ui ui;

    public TaskList(Ui ui) {
        this.ui = ui;
    }

    public void loadTasks() {
        try {
            Storage.loadTaskListFromFile();
        } catch (PoodleException e) {
            ui.printErrorMessage("i got an error! " + e.getMessage());
        }
    }

    public void saveTasks() {
        try {
            Storage.saveTaskListToFile();
        } catch (PoodleException e) {
            ui.printErrorMessage("i got an error! " + e.getMessage());
        }
    }

    public void handleMark(String firstWord, String input) {
        int firstSpaceIndex = Parser.getFirstSpaceIndex(input);
        if (firstSpaceIndex == -1) {
            throw PoodleException.missingArgumentException(firstWord);
        }

        int taskNumber;
        try {
            taskNumber = Integer.parseInt(input.substring(firstSpaceIndex));
        } catch (NumberFormatException e) {
            throw PoodleException.missingArgumentException(firstWord);
        }

        int count = Task.getTaskCount();
        if (taskNumber < 1 || taskNumber > count) {
            throw PoodleException.outOfRangeException(count);
        }

        Task task = Task.getTaskList().get(taskNumber - 1);
        if (firstWord.equals(Ui.MARK_COMMAND)) {
            task.markAsDone();
            ui.printTaskMarkedAsDone(task);
        } else if (firstWord.equals(Ui.UNMARK_COMMAND)) {
            task.unmarkAsDone();
            ui.printTaskUnmarked(task);
        }
    }

    public void deleteTask(String input) {
        int firstSpaceIndex = Parser.getFirstSpaceIndex(input);

        if (firstSpaceIndex == 0) {
            throw PoodleException.missingArgumentException(Ui.DELETE_COMMAND);
        }

        int index = Integer.parseInt(input.substring(firstSpaceIndex)) - 1;

        Task task = Task.getTaskList().get(index);
        task.removeTask();
        saveTasks();
        ui.printTaskDeleted(task);
    }

    public void showTasks() {
        ui.printTaskList();
    }

    public void findTasks(String input) {
        int firstSpaceIndex = Parser.getFirstSpaceIndex(input);

        if (firstSpaceIndex == 0) {
            throw PoodleException.missingArgumentException(Ui.FIND_COMMAND);
        }

        String keyword = input.substring(firstSpaceIndex).trim();

        if (keyword.isEmpty()) {
            throw PoodleException.missingArgumentException(Ui.FIND_COMMAND);
        }

        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : Task.getTaskList()) {
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.add(task);
            }
        }

        if (matchingTasks.isEmpty()) {
            ui.printDivider();
            System.out.println("couldn't find any tasks with " + keyword + " in it :c");
            ui.printDivider();
            return;
        }

        String[] result = new String[matchingTasks.size() + 1];
        for (int i = 0; i < matchingTasks.size(); i++) {
            result[i] = (i + 1) + ". " + matchingTasks.get(i);
        }

        ui.printTasksFound(result);
    }
}
