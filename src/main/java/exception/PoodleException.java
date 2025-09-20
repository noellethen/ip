package exception;

public class PoodleException extends RuntimeException {

    public enum Code {
        UNKNOWN_COMMAND,
        MISSING_ARGUMENT,
        WRONG_ARGUMENT,
        OUT_OF_RANGE,
        FILE_ERROR
    }

    public PoodleException(Code code, String message) {
        super(message);
    }

    public static PoodleException unknownCommandException(String input) {
        return new PoodleException(Code.UNKNOWN_COMMAND,
                "sorry :c i don't know what you mean by " + input);
    }

    public static PoodleException missingArgumentException(String input) {
        String message = switch (input) {
            case "todo" -> "todo what?? input something after todo!";
            case "deadline" -> "what has a deadline?? input something after deadline!";
            case "event" -> "what event?? input something after event!";
            case "mark", "unmark" -> "enter something like this: " + input + " 2";
            case "delete" -> "enter something like this: " + input + " 3";
            default -> "missing arguments for command " + input;
        };

        return new PoodleException(Code.MISSING_ARGUMENT, message);
    }

    public static PoodleException wrongFormatException(String input) {
        return new PoodleException(Code.WRONG_ARGUMENT, input);
    }

    public static PoodleException outOfRangeException(int count) {
        return new PoodleException(Code.OUT_OF_RANGE,
                "which task is that? >< from 1-" + count + " pls!");
    }

    public static PoodleException fileError(String message) {
        return new PoodleException(Code.FILE_ERROR, message);
    }
}
