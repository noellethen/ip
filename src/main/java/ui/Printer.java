package ui;

import task.Task;

public class Printer {

    private static final String DIVIDER = "--------------------------------------------";

    public static void printDivider() {
        System.out.println(DIVIDER);
    }

    public static void printEntryText() {
        System.out.println("""
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
            """);
    }

    public static void printExitText() {
        System.out.println("""
            --------------------------------------------
            awwwww bye :c hope to see you again soon! <3
            --------------------------------------------
            """);
    }

    public static void printTaskAdded(Task task) {
        printDivider();
        System.out.println("okie i added your task:");
        System.out.println(task);
        System.out.println("now you have " + Task.getTaskCount() + " tasks to dooo");
        printDivider();
    }

    public static void printTaskList() {
        printDivider();
        for (int i = 1; i <= Task.getTaskCount(); i++) {
            System.out.println(i + "." + Task.getTaskList().get(i - 1));
        }
        printDivider();
    }

    public static void printTaskMarkedAsDone(Task task) {
        printDivider();
        System.out.println("yay good job! the task is done c:");
        System.out.println(" " + task);
        printDivider();
    }

    public static void printTaskUnmarked(Task task) {
        printDivider();
        System.out.println("oh nooo go do your task :c");
        System.out.println(" " + task);
        printDivider();
    }

    public static void printTaskDeleted(Task task) {
        printDivider();
        System.out.println("okie i deleted your task:");
        System.out.println(task);
        System.out.println("now you have " + Task.getTaskCount() + " tasks left to dooo");
        printDivider();
    }

    public static void printErrorMessage(String message) {
        printDivider();
        System.out.println(message);
        printDivider();
    }
}
