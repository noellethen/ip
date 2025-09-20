import parser.Parser;
import task.Task;
import exception.PoodleException;
import task.TaskList;
import ui.Ui;

/**
 * Main class for the Poodle application.
 * Handles the flow of task management operations including adding, marking, unmarking,
 * deleting, listing, and finding tasks.
 */
public class Poodle {

    private static final Ui ui = new Ui();
    private static final TaskList taskList = new TaskList(ui);

    /**
     * Processes a task based on the given first word and input string.
     * @param firstWord the first word of the user input, which determines the task type.
     * @param input the full input string containing task details.
     */
    private static void processTasks(String firstWord, String input) {
        Task task = Parser.parseTask(firstWord, input);

        taskList.saveTasks();
        ui.printTaskAdded(task);
    }

    /**
     * Runs the Poodle task management application.
     * Continuously accepts user input to perform task management operations.
     */
    private static void runPoodle() {
        taskList.loadTasks();

        while (true) {
            String input = ui.getUserInput();
            if (Ui.EXIT_COMMAND.equals(input)) {
                break;
            }
            try {
                String firstWord = Parser.returnFirstWord(input);
                switch (firstWord) {
                case Ui.TODO_COMMAND:
                case Ui.DEADLINE_COMMAND:
                case Ui.EVENT_COMMAND:
                    processTasks(firstWord, input);
                    break;
                case Ui.MARK_COMMAND:
                case Ui.UNMARK_COMMAND:
                    taskList.handleMark(firstWord, input);
                    break;
                case Ui.LIST_COMMAND:
                    taskList.showTasks();
                    break;
                case Ui.DELETE_COMMAND:
                    taskList.deleteTask(input);
                    break;
                case Ui.FIND_COMMAND:
                    taskList.findTasks(input);
                    break;
                default:
                    throw PoodleException.unknownCommandException(input);
                }
            } catch (PoodleException e) {
                ui.printErrorMessage(e.getMessage());
            }
        }
    }

    /**
     * Main method to start the Poodle application.
     * Prints entry text and runs the task management system.
     * @param args command-line arguments (not used).
     */
    public static void main(String[] args) {
        ui.printEntryText();
        runPoodle();
        ui.printExitText();
    }
}
