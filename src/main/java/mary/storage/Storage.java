package mary.storage;

import mary.exception.MaryException;
import mary.task.Task;
import mary.task.TaskList;
import mary.task.Todo;
import mary.task.Deadline;
import mary.task.Event;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

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
                String[] taskStatus = indivTask.split("\\|");
                switch (taskStatus[0]) {
                    case "T":
                        taskList.add(new Todo(taskStatus[2], Integer.parseInt(taskStatus[1])));
                        break;
                    case "D":
                        taskList.add(new Deadline(taskStatus[2], Integer.parseInt(taskStatus[1]),
                                LocalDateTime.parse(taskStatus[3])));
                        break;
                    case "E":
                        taskList.add(new Event(taskStatus[2], Integer.parseInt(taskStatus[1]),
                                LocalDateTime.parse(taskStatus[3]),
                                LocalDateTime.parse(taskStatus[4])));
                        break;
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
