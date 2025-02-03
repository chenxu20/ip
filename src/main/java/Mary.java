import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileWriter;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Mary {

    ArrayList<Task> storage = new ArrayList<>();
    // int counter = 0;
    File taskFile = new File("../Task.txt");

    public void store() {
        Scanner inputScanner = new Scanner(System.in);

        try {
            taskFile.createNewFile();
            Scanner fileScanner = new Scanner(taskFile);
            while (fileScanner.hasNextLine()) {
                String indivTask = fileScanner.nextLine();
                String[] taskStatus = indivTask.split("\\|");
                switch (taskStatus[0]) {
                    case "T":
                        storage.add(new Todo(taskStatus[2], Integer.parseInt(taskStatus[1])));
                        break;
                    case "D":
                        storage.add(new Deadline(taskStatus[2], Integer.parseInt(taskStatus[1]),
                                LocalDateTime.parse(taskStatus[3])));
                        break;
                    case "E":
                        storage.add(new Event(taskStatus[2], Integer.parseInt(taskStatus[1]),
                                LocalDateTime.parse(taskStatus[3]),
                                LocalDateTime.parse(taskStatus[4])));
                        break;
                }
            }
            fileScanner.close();
        } catch (IOException e) {
            System.out.println(e);
        }

        while (true) {
            String input = inputScanner.nextLine();
            String[] splitInput = input.split(" ", 2);
            String command = splitInput[0];
            this.printLine();

            switch (command) {
                case "todo":
                    this.addToDoTask(splitInput);
                    this.writeback();
                    break;
                case "deadline":
                    this.addDeadlineTask(splitInput);
                    this.writeback();
                    break;
                case "event":
                    this.addEventTask(splitInput);
                    this.writeback();
                    break;
                case "mark":
                    this.markTask(splitInput);
                    this.writeback();
                    break;
                case "unmark":
                    this.unmarkTask(splitInput);
                    this.writeback();
                    break;
                case "list":
                    this.listTasks();
                    break;
                case "bye":
                    this.exit();
                    break;
                case "delete":
                    this.deleteTask(splitInput);
                    this.writeback();
                    break;
                default:
                    System.out.println(
                            "Sorry I don't quite understand what you are saying, please use a different command!");
            }

            this.printNewline();

            if (command.equals("bye")) {
                break;
            }
        }
        inputScanner.close();
    }

    public void writeback() {
        try {
            FileWriter taskWriter = new FileWriter("../Task.txt");
            for (Task task : storage) {
                taskWriter.write(task.writeTask() + System.lineSeparator());
            }
            taskWriter.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void printLine() {
        System.out.println("_________________________________________________________");
    }

    public void printNewline() {
        System.out.println("_________________________________________________________\n\n");
    }

    public void printAddStatus() {
        System.out.println("Got it. I've added this task:");
    }

    public void printNumberofTasks() {
        System.out.println("Now you have " + this.storage.size() + " tasks in the list.");
    }

    public void printTask() {
        this.printAddStatus();
        System.out.println(storage.get(storage.size() - 1).toString());
        this.printNumberofTasks();
    }

    public void listTasks() {
        int count = 1;
        System.out.println("Here are the tasks in your list:");
        for (Task task : storage) {
            System.out.println((count++) + ". " + task.toString());
        }
    }

    public void markTask(String[] input) {
        try {
            storage.get(Integer.parseInt(input[1]) - 1).mark();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("There are no tasks at this position!");
        }
    }

    public void unmarkTask(String[] input) {
        try {
            storage.get(Integer.parseInt(input[1]) - 1).unmark();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("There are no tasks at this position!");
        }
    }

    public void addToDoTask(String[] input) {
        try {
            storage.add(new Todo(input[1], 0));
            this.printTask();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Description of tasks cannot be empty!");
        }
    }

    public void addDeadlineTask(String[] input) {
        try {
            String[] extractDay = input[1].split("/");

            String[] deadlineDateTime = extractDay[1].split(" ");
            String deadlineDate = deadlineDateTime[1];
            String deadlineTime = deadlineDateTime[2];
            LocalDateTime deadline = LocalDateTime.parse(deadlineDate + "T" + deadlineTime + ":00");

            storage.add(new Deadline(extractDay[0].trim(), 0, deadline));
            this.printTask();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Description of tasks or deadline cannot be empty!");
            System.out.println("Format of task deadline: \\\"/by YYYY-MM-DD HH:MM\\\"");
        } catch (DateTimeException e) {
            System.out.println("Format of start or end date is wrong!");
            System.out.println("Correct Format: \"/by YYYY-MM-DD HH:MM\"");
        }
    }

    public void addEventTask(String[] input) {
        try {
            String[] extractDay = input[1].split("/");

            String[] startDateTime = extractDay[1].split(" ");
            String startDate = startDateTime[1];
            String startTime = startDateTime[2];
            LocalDateTime start = LocalDateTime.parse(startDate + "T" + startTime + ":00");

            String[] endDateTime = extractDay[2].split(" ");
            String endDate;
            String endTime;
            if (endDateTime.length == 2) {
                endDate = startDate;
                endTime = endDateTime[1];
            } else if (endDateTime.length == 3) {
                endDate = endDateTime[1];
                endTime = endDateTime[2];
            } else {
                throw new DateTimeException(null);
            }
            LocalDateTime end = LocalDateTime.parse(endDate + "T" + endTime + ":00");

            if (end.isBefore(start)) {
                throw new MaryException("End date cannot be before start date!");
            }

            storage.add(new Event(extractDay[0].trim(), 0, start, end));
            this.printTask();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Description of tasks or time of task cannot be empty!");
            System.out.println(
                    "Format of event duration: \"/from YYYY-MM-DD HH:MM /to YYYY-MM-DD HH:MM\", or you can omit the end date if it is the same as the starting date");
        } catch (DateTimeException e) {
            System.out.println("Format of start or end date is wrong!");
            System.out.println(
                    "Correct Format: \"/from YYYY-MM-DD HH:MM /to YYYY-MM-DD HH:MM\", or you can omit the end date if it is the same as the starting date");
        } catch (MaryException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteTask(String[] input) {
        try {
            int index = Integer.parseInt(input[1]);
            System.out.println("Noted. I've removed this task:");
            System.out.println(storage.get(index - 1).toString());
            storage.remove(index - 1);
            this.printNumberofTasks();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Task does not exist!");
        }
    }

    public static void main(String[] args) {
        Mary mary = new Mary();
        String welcome = "Hello! I'm Mary\n"
                + "What can I do for you?\n\n"
                + "----------------------------";

        System.out.println(welcome);
        mary.store();
    }
}
