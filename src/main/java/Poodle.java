import parser.Parser;
import task.Task;
import exception.PoodleException;
import task.TaskList;
import ui.Ui;

public class Poodle {

    private static final Ui ui = new Ui();
    private static final TaskList taskList = new TaskList(ui);

    private static void processTasks(String firstWord, String input) {
        Task task = Parser.parseTask(firstWord, input);

        taskList.saveTasks();
        ui.printTaskAdded(task);
    }

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

    public static void main(String[] args) {
        ui.printEntryText();
        runPoodle();
        ui.printExitText();
    }
}
