package mary.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import mary.exception.MaryException;
import mary.task.Deadline;
import mary.task.Event;
import mary.task.Task;
import mary.task.TaskList;
import mary.task.Todo;

/**
 * Handles both the storage and reading of the list of tasks locally in the
 * user's computer.
 */
public class Storage {

    private File taskFile;
    private ArrayList<Task> taskList = new ArrayList<>();

    /**
     * Obtains an instance of File from the given filepath input.
     * 
     * @param filePath Indicates where the file should be stored/is stored.
     */
    public Storage(String filePath) {
        this.taskFile = new File(filePath);
    }

    /**
     * Loads the list of tasks from the hard drive into the program.
     * 
     * @return A list of tasks that was stored locally on the computer from previous
     *         runs, or initialises an empty list of tasks if the current run is the
     *         first run.
     * @throws MaryException When file reading is unsuccessful.
     */
    public ArrayList<Task> load() throws MaryException {
        try {
            taskFile.createNewFile();
            Scanner fileScanner = new Scanner(taskFile);
            while (fileScanner.hasNextLine()) {
                String indivTask = fileScanner.nextLine();
                String[] taskStatus = indivTask.split("\\|", 3);
                switch (taskStatus[0]) {
                case "T":
                    taskList.add(new Todo(taskStatus[2], Integer.parseInt(taskStatus[1])));
                    break;
                case "D":
                    String[] deadlineTaskDetails = taskStatus[2].split("\\|");
                    taskList.add(new Deadline(deadlineTaskDetails[0], Integer.parseInt(taskStatus[1]),
                            LocalDateTime.parse(deadlineTaskDetails[2])));
                    break;
                case "E":
                    String[] eventTaskDetails = taskStatus[2].split("\\|");
                    taskList.add(new Event(eventTaskDetails[0], Integer.parseInt(taskStatus[1]),
                            LocalDateTime.parse(eventTaskDetails[1]),
                            LocalDateTime.parse(eventTaskDetails[2])));
                    break;
                default:
                    continue;
                }
            }
            fileScanner.close();
        } catch (IOException e) {
            throw new MaryException(null);
        }

        return taskList;
    }

    /**
     * Stores the list of tasks in the program back to the hard drive.
     * 
     * @param taskList Obtains TaskList containing list of tasks from the program.
     */
    public void store(TaskList taskList) {
        try {
            FileWriter taskWriter = new FileWriter(this.taskFile);
            for (Task task : taskList.getTaskList()) {
                taskWriter.write(task.writeTask() + System.lineSeparator());
            }
            taskWriter.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
