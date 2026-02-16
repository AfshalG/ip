package genie.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import genie.task.Deadline;
import genie.task.Event;
import genie.task.Task;
import genie.task.Todo;

/**
 * Handles loading and saving tasks to a file.
 * File is stored at ./data/genie.txt relative to the project root.
 */
public class Storage {
    private static final String FILE_PATH = "./data/genie.txt";
    private static final String DIR_PATH = "./data";

    /**
     * Loads tasks from the data file.
     * Returns an empty list if the file does not exist.
     * Skips corrupted lines with a warning.
     *
     * @return ArrayList of tasks loaded from file
     */
    public static ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(FILE_PATH);

        if (!file.exists()) {
            return tasks;
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) {
                    continue;
                }
                try {
                    Task task = parseTask(line);
                    if (task != null) {
                        tasks.add(task);
                    }
                } catch (Exception e) {
                    System.out.println(" [Warning] Skipping corrupted line: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println(" [Warning] Could not read data file: " + e.getMessage());
        }

        return tasks;
    }

    /**
     * Saves all tasks to the data file.
     * Creates the data directory and file if they do not exist.
     *
     * @param tasks The list of tasks to save
     */
    public static void save(ArrayList<Task> tasks) {
        File dir = new File(DIR_PATH);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            for (Task task : tasks) {
                writer.write(task.toFileString() + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println(" [Warning] Could not save data: " + e.getMessage());
        }
    }

    /**
     * Parses a single line from the data file into a Task object.
     * Expected formats:
     *   T | 0 | description
     *   D | 1 | description | by
     *   E | 0 | description | from | to
     *
     * @param line The line to parse
     * @return The parsed Task, or null if the type is unknown
     * @throws Exception If the line format is invalid
     */
    private static Task parseTask(String line) throws Exception {
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) {
            throw new Exception("Invalid format");
        }

        String type = parts[0].trim();
        boolean isDone = parts[1].trim().equals("1");
        String description = parts[2].trim();

        Task task;
        switch (type) {
        case "T":
            task = new Todo(description);
            break;
        case "D":
            if (parts.length < 4) {
                throw new Exception("Missing deadline field");
            }
            task = new Deadline(description, parts[3].trim());
            break;
        case "E":
            if (parts.length < 5) {
                throw new Exception("Missing event fields");
            }
            task = new Event(description, parts[3].trim(), parts[4].trim());
            break;
        default:
            return null;
        }

        if (isDone) {
            task.markAsDone();
        }
        return task;
    }
}
