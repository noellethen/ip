package storage;

import exception.PoodleException;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;
import ui.Ui;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

import java.util.Scanner;

/**
 * A class for handling file storage operations, including saving and loading task data.
 * It manages reading from and writing to a file for persistent task storage.
 */
public class Storage {
    private static final String DATA_FILE_PATH = "data/Poodle.txt";

    /**
     * Saves the task list to a file.
     * Each task is formatted and written to the file in a structured format.
     */
    public static void saveTaskListToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DATA_FILE_PATH, false))) {
            for (Task task : Task.getTaskList()) {
                String taskLine = "";
                if (task instanceof Todo) {
                    taskLine = "T | " + task.formatForSave();
                } else if (task instanceof Deadline deadline) {
                    taskLine = "D | " + task.formatForSave() + " | " + deadline.getBy();
                } else if (task instanceof Event event) {
                    taskLine = "E | " + task.formatForSave() + " | " + event.getFrom() + " | " + event.getTo();
                }
                writer.write(taskLine + "\n");
            }
        } catch (IOException e) {
            throw PoodleException.fileError("nooo i failed to save task(s): " + e.getMessage());
        }
    }

    /**
     * Loads the task list from a file.
     * If the file doesn't exist, it creates a new one.
     * The task data is read and parsed into task objects.
     */
    public static void loadTaskListFromFile() {
        File file = new File(DATA_FILE_PATH);

        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            if (!parentDir.mkdirs()) {
                throw PoodleException.fileError("failed to create data directory!!");
            }
        }

        if (!file.exists()) {
            try {
                boolean created = file.createNewFile();
                if (!created) {
                    throw PoodleException.fileError("failed to create a new file T-T");
                }
                Ui.printDivider();
                System.out.println("the .txt file to store your data doesn't exist, but i created a new one yay");
                Ui.printDivider();
            } catch (IOException e) {
                throw PoodleException.fileError("i'm trying to create the file, but it failed :c: " + e.getMessage());
            }
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" \\| ");
                String type = parts[0].trim();
                boolean isDone = parts[1].equals("1");
                String description = parts[2];

                Task task;
                switch (type) {
                case "T":
                    task = new Todo(description);
                    break;
                case "D":
                    if (parts.length < 4) {
                        throw PoodleException.wrongFormatException("i need a /by for deadline task!");
                    }
                    String by = parts[3].trim();
                    task = new Deadline(description, by);
                    break;
                case "E":
                    if (parts.length < 5) {
                        throw PoodleException.wrongFormatException("i need a /from or /to for event task!");
                    }
                    String from = parts[3].trim();
                    String to = parts[4].trim();
                    task = new Event(description, from, to);
                    break;
                default:
                    throw PoodleException.wrongFormatException("there's an invalid task type: " + type);
                }

                if (isDone) {
                    task.markAsDone();
                }
            }
        } catch (FileNotFoundException e) {
            throw PoodleException.fileError("uhhhh i can't find the file: " + e.getMessage());
        }
    }
}
