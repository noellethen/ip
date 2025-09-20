package ui;

import task.Task;

import java.util.Scanner;

public class Ui {

    private static final String DIVIDER = "--------------------------------------------";
    public static final String LIST_COMMAND = "list";
    public static final String MARK_COMMAND = "mark";
    public static final String UNMARK_COMMAND = "unmark";
    public static final String EXIT_COMMAND = "bye";
    public static final String TODO_COMMAND = "todo";
    public static final String DEADLINE_COMMAND = "deadline";
    public static final String EVENT_COMMAND = "event";
    public static final String DELETE_COMMAND = "delete";
    public static final String FIND_COMMAND = "find";

    public static final int SLASH_BY_LENGTH = 4;
    public static final int SLASH_FROM_LENGTH = 6;
    public static final int SLASH_TO_LENGTH = 4;

    public void printDivider() {
        System.out.println(DIVIDER);
    }

    public void printEntryText() {
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

    public void printExitText() {
        System.out.println("""
            --------------------------------------------
            awwwww bye :c hope to see you again soon! <3
            --------------------------------------------
            """);
    }

    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public String getUserInput() {
        return sc.nextLine();
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

    public void printTaskAdded(Task task) {
        printDivider();
        System.out.println("okie i added your task:");
        System.out.println(task);
        System.out.println("now you have " + Task.getTaskCount() + " tasks to dooo");
        printDivider();
    }

    public void printTaskList() {
        printDivider();
        for (int i = 1; i <= Task.getTaskCount(); i++) {
            System.out.println(i + "." + Task.getTaskList().get(i - 1));
        }
        printDivider();
    }

    public void printTaskMarkedAsDone(Task task) {
        printDivider();
        System.out.println("yay good job! the task is done c:");
        System.out.println(" " + task);
        printDivider();
    }

    public void printTaskUnmarked(Task task) {
        printDivider();
        System.out.println("oh nooo go do your task :c");
        System.out.println(" " + task);
        printDivider();
    }

    public void printTaskDeleted(Task task) {
        printDivider();
        System.out.println("okie i deleted your task:");
        System.out.println(task);
        System.out.println("now you have " + Task.getTaskCount() + " tasks left to dooo");
        printDivider();
    }

    public void printTasksFound(String[] list) {
        printDivider();
        System.out.println("yay here are the tasks i found: ");
        for (int i = 0; i < list.length - 1; i++) {
            System.out.println(list[i]);
        }
        printDivider();
    }

    public void printErrorMessage(String message) {
        printDivider();
        System.out.println(message);
        printDivider();
    }
}
