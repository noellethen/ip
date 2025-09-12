package ui;

import storage.Storage;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;
import exception.PoodleException;

import java.util.Scanner;
import java.util.ArrayList;

public class Poodle {
    // Commands
    private static final String LIST_COMMAND = "list";
    private static final String MARK_COMMAND = "mark";
    private static final String UNMARK_COMMAND = "unmark";
    private static final String EXIT_COMMAND = "bye";
    private static final String TODO_COMMAND = "todo";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String EVENT_COMMAND = "event";
    private static final String DELETE_COMMAND = "delete";

    private static final int SLASH_BY_LENGTH = 4;
    private static final int SLASH_FROM_LENGTH = 6;
    private static final int SLASH_TO_LENGTH = 4;

    private static void loadTasks() {
        try {
            Storage.loadTaskListFromFile();
        } catch (PoodleException e) {
            System.out.println("errorrrrrr: " + e.getMessage());
        }
    }

    private static void saveTasks() {
        try {
            Storage.saveTaskListToFile();
        }  catch (PoodleException e) {
            System.out.println("errorrrrrr: " + e.getMessage());
        }
    }

    private static void handleMark(String firstWord, String input) {
        int firstSpaceIndex = input.indexOf(' ');
        if (firstSpaceIndex == -1) {
            throw PoodleException.missingArgumentException(firstWord);
        }

        int taskNumber;
        try {
            taskNumber = Integer.parseInt(input.substring(firstSpaceIndex + 1));
        } catch (NumberFormatException e) {
            throw PoodleException.missingArgumentException(firstWord);
        }

        int count = Task.getTaskCount();
        if (taskNumber < 1 || taskNumber > count) {
            throw PoodleException.outOfRangeException(count);
        }

        Task task = Task.getTaskList().get(taskNumber - 1);
        if (firstWord.equals(MARK_COMMAND)) {
            task.markAsDone();
            Printer.printTaskMarkedAsDone(task);
        } else if (firstWord.equals(UNMARK_COMMAND)) {
            task.unmarkAsDone();
            Printer.printTaskUnmarked(task);
        }
    }

    private static Todo processTodo(String input) {
        int firstSpaceIndex = input.indexOf(' ') + 1;

        if (firstSpaceIndex == 0) {
            throw PoodleException.missingArgumentException(TODO_COMMAND);
        }

        String description = input.substring(firstSpaceIndex).trim();
        if (description.isEmpty()) {
            throw PoodleException.missingArgumentException(TODO_COMMAND);
        }
        return new Todo(description);
    }

    private static Deadline processDeadline(String input) {
        int firstSpaceIndex = input.indexOf(' ') + 1;

        if (firstSpaceIndex == 0) {
            throw PoodleException.missingArgumentException(DEADLINE_COMMAND);
        }

        int byIndex = input.indexOf("/by");
        if (byIndex == -1) {
            throw PoodleException.wrongFormatException(
                    "type something like this: deadline <description> /by <when>!");
        }

        String by = input.substring(byIndex + SLASH_BY_LENGTH).trim();
        String description = input.substring(firstSpaceIndex, byIndex - 1).trim();
        if (description.isEmpty() || by.isEmpty()) {
            throw PoodleException.missingArgumentException(DEADLINE_COMMAND);
        }

        return new Deadline(description, by);

    }

    private static Event processEvent(String input) {
        int firstSpaceIndex = input.indexOf(' ') + 1;

        if (firstSpaceIndex == 0) {
            throw PoodleException.missingArgumentException(EVENT_COMMAND);
        }

        int fromIndex = input.indexOf("/from");
        int toIndex = input.indexOf("/to");
        if (fromIndex == -1 || toIndex == -1 || toIndex < fromIndex) {
            throw PoodleException.wrongFormatException(
                    "type something like this: event <description> /from <start> /to <end>!");
        }

        String from = input.substring(fromIndex + SLASH_FROM_LENGTH, toIndex - 1).trim();
        String to = input.substring(toIndex + SLASH_TO_LENGTH).trim();
        String description = input.substring(firstSpaceIndex, fromIndex - 1).trim();
        if  (description.isEmpty() ||  to.isEmpty() || from.isEmpty()) {
            throw PoodleException.missingArgumentException(EVENT_COMMAND);
        }

        return new Event(description, from, to);
    }

    private static void processTasks(String firstWord, String input) {
        Task task = null;

        switch (firstWord) {
        case TODO_COMMAND:
            task = processTodo(input);
            break;
        case DEADLINE_COMMAND:
            task = processDeadline(input);
            break;
        case EVENT_COMMAND:
            task = processEvent(input);
            break;
        default:
            System.out.println("what do you want me to do for you?");
        }

        saveTasks();

        Printer.printTaskAdded(task);
    }

    private static void showTasks() {
        Printer.printTaskList();
    }

    private static void deleteTask(String input) {
        int firstSpaceIndex = input.indexOf(' ') + 1;
        int index = Integer.parseInt(input.substring(firstSpaceIndex)) - 1;

        if (firstSpaceIndex == 0) {
            throw PoodleException.missingArgumentException(DELETE_COMMAND);
        }

        Task task = Task.getTaskList().get(index);
        task.removeTask();
        saveTasks();

        Printer.printTaskDeleted(task);
    }

    private static String returnFirstWord(String input) {
        int firstSpaceIndex = input.indexOf(' ');
        if (firstSpaceIndex != -1) {
            return input.substring(0, firstSpaceIndex);
        } else {
            return input;
        }
    }

    private static void runPoodle() {
        Scanner sc = new Scanner(System.in);
        String input;

        loadTasks();

        while (true) {
            input = sc.nextLine();
            if (EXIT_COMMAND.equals(input)) {
                break;
            }
            try {
                String firstWord = returnFirstWord(input);
                switch (firstWord) {
                case TODO_COMMAND:
                case DEADLINE_COMMAND:
                case EVENT_COMMAND:
                    processTasks(firstWord, input);
                    break;
                case MARK_COMMAND:
                case UNMARK_COMMAND:
                    handleMark(firstWord, input);
                    break;
                case LIST_COMMAND:
                    showTasks();
                    break;
                case DELETE_COMMAND:
                    deleteTask(input);
                    break;
                default:
                    throw PoodleException.unknownCommandException(input);
                }
            } catch (PoodleException e) {
                Printer.printErrorMessage(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        Printer.printEntryText();
        runPoodle();
        Printer.printTaskList();
    }
}
