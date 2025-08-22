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
    private static final String[] tasks = new String[MAX_TASKS];
    private static int taskCount = 0;

    private static void processInput(Scanner sc) {
        String input;
        while (true) {
            input = sc.nextLine();
            if (EXIT_COMMAND.equals(input)) {
                break;
            }
            if (input.isEmpty()) {
                System.out.println("why empty >:c enter something!!");
            } else if (input.equals(LIST_COMMAND)) {
                showTasks();
            } else {
                addTask(input);
            }
        }
    }

    private static void addTask(String input) {
        printDivider();
        if (taskCount < MAX_TASKS) {
            tasks[taskCount] = input;
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
                System.out.println((i + 1) + ". " + tasks[i]);
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
