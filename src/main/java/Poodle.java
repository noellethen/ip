import java.util.Scanner;

public class Poodle {
    private static final String DIVIDER = "--------------------------------------------";
    private static final String EXIT_COMMAND = "bye";
    private static final String ENTRY_TEXT = """
            --------------------------------------------
            hiiiiii from
                       ╔╦╗
                       ║║║
            ╔══╦══╦══╦═╝║║╔══╗
            ║╔╗║╔╗║╔╗║╔╗║║║║═╣
            ║╚╝║╚╝║╚╝║╚╝║╚╣║═╣
            ║╔═╩══╩══╩══╩═╩══╝
            ║║
            ╚╝
    
            is there anything i can do for you?
            --------------------------------------------
            """;
    private static final String EXIT_TEXT = """
            --------------------------------------------
            awwwww bye :c hope to see you again soon! <3
            --------------------------------------------
            """;
    private static final String LIST_COMMAND = "list";
    private static final int MAX_TASKS = 100;
    private static final Task[] tasks = new Task[MAX_TASKS];
    private static int taskCount = 0;
    private static final String MARK_COMMAND = "mark";
    private static final String UNMARK_COMMAND = "unmark";

    private static void processInput(Scanner sc) {
        String input;
        while (true) {
            input = sc.nextLine();
            if (EXIT_COMMAND.equals(input)) {
                break;
            }
            processLine(input);
        }
    }

    private static void processLine(String input) {
        if (input.isEmpty()) {
            System.out.println("why empty >:c enter something!!");
        } else if (input.equals(LIST_COMMAND)) {
            showTasks();
        } else if (input.startsWith(MARK_COMMAND + " ")) {
            handleMark(input.substring(MARK_COMMAND.length()).trim());
        } else if (input.startsWith(UNMARK_COMMAND + " ")) {
            handleUnmark(input.substring(UNMARK_COMMAND.length()).trim());
        } else {
            addTask(input);
        }
    }

    private static void handleMark(String input) {
        try {
            int idx = Integer.parseInt(input);
            if (idx < 1 || idx > taskCount) {
                System.out.println("which task is that? >< from 1-100 pls!");
                return;
            }

            tasks[idx - 1].markAsDone();
            printDivider();
            System.out.println("yay good job! the task is done c:");
            System.out.println(" " + tasks[idx - 1]);
            printDivider();
        } catch (NumberFormatException e) {
            System.out.println("enter something like this: mark 2");
        }
    }

    private static void handleUnmark(String input) {
        try {
            int idx = Integer.parseInt(input);
            if (idx < 1 || idx > taskCount) {
                System.out.println("which task is that? >< from 1-100 pls!");
                return;
            }

            tasks[idx - 1].unmarkAsDone();
            printDivider();
            System.out.println("oh nooo go do your task :c");
            System.out.println(" " + tasks[idx - 1]);
            printDivider();
        } catch (NumberFormatException e) {
            System.out.println("enter something like this: unmark 2");
        }
    }

    private static void addTask(String input) {
        printDivider();
        if (taskCount < MAX_TASKS) {
            tasks[taskCount] = new Task(input);
            taskCount++;
            System.out.println("yay :D added: " + input);
        } else {
            System.out.println("you have too many tasks >< pls clear first");
        }
        printDivider();
    }

    private static void showTasks() {
        printDivider();
        if (taskCount == 0) {
            System.out.println("no tasks to show :c add one?");
        } else {
            for (int i = 0; i < taskCount; i++) {
                System.out.println((i + 1) + ". " + tasks[i].toString());
            }
        }
        printDivider();
    }

    private static void printDivider() {
        System.out.println(DIVIDER);
    }

    public static void main(String[] args) {
        System.out.println(ENTRY_TEXT);
        try (Scanner sc = new Scanner(System.in)) {
            processInput(sc);
        }
        System.out.println(EXIT_TEXT);
    }
}
