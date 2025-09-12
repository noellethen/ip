package storage;

import exception.PoodleException;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

import java.util.Scanner;

public class Storage {
    private static final String DATA_FILE_PATH = "./data/Poodle.txt";

    public static void saveTaskListToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DATA_FILE_PATH, false))) {
            for (Task task : Task.getTaskList()) {
                String taskLine = "";
                if (task instanceof Todo) {
                    taskLine = "T | " + task.formatForSave();
                } else if (task instanceof Deadline deadline) {
                    taskLine = "D | " + task.formatForSave() + " | " + deadline.getBy();
                } else if (task instanceof Event event) {
                    taskLine = "E | " + task.formatForSave() + event.getFrom() + " to " + event.getTo();
                }
                writer.write(taskLine + "\n");
            }
        } catch (IOException e) {
            throw PoodleException.fileError("nooo i failed to save task(s): " + e.getMessage());
        }
    }

    public static void loadTaskListFromFile() {
        File file = new File(DATA_FILE_PATH);

        if (!file.exists()) {
            throw PoodleException.fileError("i'm recording the tasks in a separate file! but it can't be found... so i'll start a new one!");
        }

        try (Scanner scanner = new Scanner(file)) {
            Task.getTaskList().clear();

            while (scanner.hasNextLine()) {
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
