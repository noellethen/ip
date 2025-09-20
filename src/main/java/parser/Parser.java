package parser;

import exception.PoodleException;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;
import ui.Ui;

/**
 * A parser class for handling user input commands.
 * It processes various task types such as Todo, Deadline, and Event.
 */
public class Parser {

    /**
     * Returns the first word from the input string, which represents the command.
     * @param input the full input string from the user.
     * @return the first word of the input string.
     */
    public static String returnFirstWord(String input) {
        int firstSpaceIndex = getFirstSpaceIndex(input);
        if (firstSpaceIndex != 0) {
            return input.substring(0, firstSpaceIndex - 1);
        } else {
            return input;
        }
    }

    public static int getFirstSpaceIndex(String input) {
        return input.indexOf(' ') + 1;
    }

    /**
     * Processes the input string to create a Todo task.
     * @param input the user input containing the task description.
     * @return a new Todo task.
     * @throws PoodleException if the input format is invalid.
     */
    public static Todo processTodo(String input) {
        int firstSpaceIndex = getFirstSpaceIndex(input.trim());

        if (firstSpaceIndex == 0) {
            throw PoodleException.missingArgumentException(Ui.TODO_COMMAND);
        }

        String description = input.substring(firstSpaceIndex).trim();
        if (description.isEmpty()) {
            throw PoodleException.missingArgumentException(Ui.TODO_COMMAND);
        }
        return new Todo(description);
    }

    /**
     * Processes the input string to create a Deadline task.
     * @param input the user input containing the task description and deadline.
     * @return a new Deadline task.
     * @throws PoodleException if the input format is invalid.
     */
    public static Deadline processDeadline(String input) {
        int firstSpaceIndex = getFirstSpaceIndex(input);

        if (firstSpaceIndex == 0) {
            throw PoodleException.missingArgumentException(Ui.DEADLINE_COMMAND);
        }

        int byIndex = input.indexOf("/by");
        if (byIndex == -1) {
            throw PoodleException.wrongFormatException(
                    "type something like this: deadline <description> /by <when>!");
        }

        String by = input.substring(byIndex + Ui.SLASH_BY_LENGTH).trim();
        String description = input.substring(firstSpaceIndex, byIndex - 1).trim();
        if (description.isEmpty() || by.isEmpty()) {
            throw PoodleException.missingArgumentException(Ui.DEADLINE_COMMAND);
        }

        return new Deadline(description, by);

    }

    /**
     * Processes the input string to create an Event task.
     * @param input the user input containing the task description, start time, and end time.
     * @return a new Event task.
     * @throws PoodleException if the input format is invalid.
     */
    public static Event processEvent(String input) {
        int firstSpaceIndex = getFirstSpaceIndex(input);

        if (firstSpaceIndex == 0) {
            throw PoodleException.missingArgumentException(Ui.EVENT_COMMAND);
        }

        int fromIndex = input.indexOf("/from");
        int toIndex = input.indexOf("/to");
        if (fromIndex == -1 || toIndex == -1 || toIndex < fromIndex) {
            throw PoodleException.wrongFormatException(
                    "type something like this: event <description> /from <start> /to <end>!");
        }

        String from = input.substring(fromIndex + Ui.SLASH_FROM_LENGTH, toIndex - 1).trim();
        String to = input.substring(toIndex + Ui.SLASH_TO_LENGTH).trim();
        String description = input.substring(firstSpaceIndex, fromIndex - 1).trim();
        if  (description.isEmpty() ||  to.isEmpty() || from.isEmpty()) {
            throw PoodleException.missingArgumentException(Ui.EVENT_COMMAND);
        }

        return new Event(description, from, to);
    }

    /**
     * Parses the task based on the first word of the input command.
     * @param firstWord the first word from the user input.
     * @param input the full user input.
     * @return the corresponding Task object (Todo, Deadline, or Event).
     * @throws PoodleException if the command is unknown.
     */
    public static Task parseTask(String firstWord, String input) {
        switch (firstWord) {
        case Ui.TODO_COMMAND:
            return processTodo(input);
        case Ui.DEADLINE_COMMAND:
            return processDeadline(input);
        case Ui.EVENT_COMMAND:
            return processEvent(input);
        default:
            throw PoodleException.unknownCommandException(input);
        }
    }
}
