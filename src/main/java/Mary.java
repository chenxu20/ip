import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileWriter;

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
                        storage.add(new Deadline(taskStatus[2], Integer.parseInt(taskStatus[1]), taskStatus[3]));
                        break;
                    case "E":
                        storage.add(new Event(taskStatus[2], Integer.parseInt(taskStatus[1]), taskStatus[3],
                                taskStatus[4]));
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
        storage.get(Integer.parseInt(input[1]) - 1).mark();
    }

    public void unmarkTask(String[] input) {
        storage.get(Integer.parseInt(input[1]) - 1).unmark();
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
            storage.add(new Deadline(extractDay[0].trim(), 0, extractDay[1].split(" ", 2)[1]));
            this.printTask();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Description of tasks or deadline cannot be empty!");
        }
    }

    public void addEventTask(String[] input) {
        try {
            String[] extractDay = input[1].split("/");
            storage.add(new Event(extractDay[0].trim(), 0, extractDay[1].split(" ", 2)[1].trim(),
                    extractDay[2].split(" ", 2)[1].trim()));
            this.printTask();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Description of tasks or time of task cannot be empty!");
        }
    }

    public void deleteTask(String[] input) {
        try {
            int index = Integer.parseInt(input[1]);
            System.out.println("Noted. I've removed this task:");
            System.out.println(storage.get(index - 1).toString());
            // for (int count = index; count < counter; count++) {
            // storage[count - 1] = storage[count];
            // }
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
