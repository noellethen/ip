import java.util.Scanner;

/**
 * Simple echo program that prints user input wrapped in a divider
 * until the user types "bye".
 */
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

    /**
     * Reads lines from standard input and echoes them between dividers
     * until the exit command is entered.
     */
    public static void echo() {
        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();
        while (!EXIT_COMMAND.equals(input)) {
            System.out.println(DIVIDER);
            System.out.println(input);
            System.out.println(DIVIDER);
            input = sc.nextLine();
        }
    }

    public static void main(String[] args) {
        System.out.println(ENTRY_TEXT);
        echo();
        System.out.println(EXIT_TEXT);
    }
}
