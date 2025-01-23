import java.util.Scanner;

public class Mary {

    Task[] storage = new Task[100];
    int counter = 0;

    public void store() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();
            String[] splitInput = input.split(" ", 2);
            String command = splitInput[0];
            this.printLine();

            switch (command) {
                case "todo":
                    this.addToDoTask(splitInput);
                    break;
                case "deadline":
                    this.addDeadlineTask(splitInput);
                    break;
                case "event":
                    this.addEventTask(splitInput);
                    break;
                case "mark":
                    this.markTask(splitInput);
                    break;
                case "unmark":
                    this.unmarkTask(splitInput);
                    break;
                case "list":
                    this.listTasks();
                    break;
                case "bye":
                    this.exit();
                    break;
            }

            this.printNewline();

            if (command.equals("bye")) {
                break;
            }

            // if (splitInput.length < 2) {
            // if (splitInput[0].equals("bye")) {
            // System.out.println("Bye. Hope to see you again soon!");
            // System.out.println("_________________________________________________________\n");
            // break;
            // }
            // if (splitInput[0].equals("list")) {
            // for (int count = 0; (count < 100) && (storage[count] != null); count++) {
            // System.out.println((count + 1) + ". " + storage[count].toString());
            // }
            // System.out.println("_________________________________________________________\n");
            // continue;
            // }
            // }

            // if (splitInput[0].equals("mark") || splitInput[0].equals("unmark")) {
            // if (splitInput[1].matches("\\d+")) {
            // int index = Integer.parseInt(splitInput[1]);
            // if (splitInput[0].equals("mark")) {
            // storage[index - 1].mark();
            // } else {
            // storage[index - 1].unmark();
            // }
            // System.out.println("_________________________________________________________\n");
            // continue;
            // }
            // }

            // System.out.println("added: " + input);
            // storage[counter] = new Task(input);
            // counter++;
            // System.out.println("_________________________________________________________\n");
        }
        scanner.close();
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
        System.out.println("Now you have " + this.counter + " tasks in the list.");
    }

    public void printTask() {
        this.printAddStatus();
        System.out.println(storage[counter++].toString());
        this.printNumberofTasks();
    }

    public void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int count = 0; (count < 100) && (storage[count] != null); count++) {
            System.out.println((count + 1) + ". " + storage[count].toString());
        }
    }

    public void markTask(String[] input) {
        storage[Integer.parseInt(input[1]) - 1].mark();
    }

    public void unmarkTask(String[] input) {
        storage[Integer.parseInt(input[1]) - 1].unmark();
    }

    public void addToDoTask(String[] input) {
        storage[counter] = new Todo(input[1]);
        this.printTask();
    }

    public void addDeadlineTask(String[] input) {
        String[] extractDay = input[1].split("/");
        storage[counter] = new Deadline(extractDay[0].trim(), extractDay[1]);
        this.printTask();
    }

    public void addEventTask(String[] input) {
        String[] extractDay = input[1].split("/");
        storage[counter] = new Event(extractDay[0].trim(), extractDay[1], extractDay[2]);
        this.printTask();
    }

    public static void main(String[] args) {
        Mary mary = new Mary();
        String welcome = "Hello! I'm Mary \n"
                + "What can I do for you? \n\n"
                + "----------------------------";

        System.out.println(welcome);
        mary.store();
    }
}
