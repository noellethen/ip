package exception;

/**
 * A custom exception class for handling various errors in the Poodle application.
 * It provides different error codes for missing arguments, unknown commands, file errors, etc.
 */
public class PoodleException extends RuntimeException {

    public enum Code {
        UNKNOWN_COMMAND,
        MISSING_ARGUMENT,
        WRONG_ARGUMENT,
        OUT_OF_RANGE,
        FILE_ERROR
    }

    /**
     * Constructor for PoodleException.
     * @param code the error code indicating the type of exception.
     * @param message the error message explaining the exception.
     */
    public PoodleException(Code code, String message) {
        super(message);
    }

    /**
     * Creates an exception for unknown commands.
     * @param input the unknown command that caused the exception.
     * @return a new PoodleException with an unknown command message.
     */
    public static PoodleException unknownCommandException(String input) {
        return new PoodleException(Code.UNKNOWN_COMMAND,
                "sorry :c i don't know what you mean by " + input);
    }

    /**
     * Creates an exception for missing arguments in a command.
     * @param input the command that is missing arguments.
     * @return a new PoodleException with a missing argument message.
     */
    public static PoodleException missingArgumentException(String input) {
        String message = switch (input) {
            case "todo" -> "todo what?? input something after todo!";
            case "deadline" -> "what has a deadline?? input something after deadline!";
            case "event" -> "what event?? input something after event!";
            case "mark", "unmark" -> "enter something like this: " + input + " 2";
            case "delete" -> "enter something like this: " + input + " 3";
            case "find" -> "find what??";
            default -> "missing arguments for command " + input;
        };

        return new PoodleException(Code.MISSING_ARGUMENT, message);
    }

    /**
     * Creates an exception for wrong format errors.
     * @param input the input that has the wrong format.
     * @return a new PoodleException with a wrong format message.
     */
    public static PoodleException wrongFormatException(String input) {
        return new PoodleException(Code.WRONG_ARGUMENT, input);
    }

    /**
     * Creates an exception for out-of-range task numbers.
     * @param count the number of tasks in the list.
     * @return a new PoodleException indicating the task number is out of range.
     */
    public static PoodleException outOfRangeException(int count) {
        return new PoodleException(Code.OUT_OF_RANGE,
                "which task is that? >< from 1-" + count + " pls!");
    }

    /**
     * Creates an exception for file errors.
     * @param message the error message related to file operations.
     * @return a new PoodleException indicating a file error.
     */

    public static PoodleException fileError(String message) {
        return new PoodleException(Code.FILE_ERROR, message);
    }
}
