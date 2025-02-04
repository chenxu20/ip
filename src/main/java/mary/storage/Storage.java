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

public class Storage {

    private File taskFile;
    private ArrayList<Task> taskList = new ArrayList<>();

    public Storage(String filePath) {
        this.taskFile = new File(filePath);
    }

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
