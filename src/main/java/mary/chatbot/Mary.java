package mary.chatbot;

import mary.storage.Storage;
import mary.task.TaskList;
import mary.ui.Ui;
import mary.exception.MaryException;
import mary.parser.Parser;

import java.util.Scanner;

public class Mary {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Mary(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (MaryException e) {
            this.ui.showLoadingError();
            this.tasks = new TaskList();
        }
    }

    public void run() {
        Scanner inputScanner = new Scanner(System.in);
        this.ui.welcomeMessage();

        while (true) {
            String input = inputScanner.nextLine();
            String command = Parser.parseInput(input);
            String[] splitInput = input.split(" ", 2);
            this.ui.printLine();
            try {
                switch (command) {
                    case "bye":
                        this.ui.exit();
                        break;
                    case "todo":
                        if (splitInput.length < 2) {
                            throw new MaryException("Task description is missing!");
                        }
                        Parser.parseToDo(splitInput[1], this.tasks);
                        storage.store(this.tasks);
                        break;
                    case "deadline":
                        if (splitInput.length < 2) {
                            throw new MaryException("Task description and deadline are missing!");
                        }
                        Parser.parseDeadline(splitInput[1], this.tasks);
                        storage.store(this.tasks);
                        break;
                    case "event":
                        if (splitInput.length < 2) {
                            throw new MaryException("Task description and event duration are missing!");
                        }
                        Parser.parseEvent(splitInput[1], this.tasks);
                        storage.store(this.tasks);
                        break;
                    case "mark":
                        if (splitInput.length < 2) {
                            throw new MaryException("Choose task to be marked!");
                        }
                        this.tasks.markTask(splitInput[1]);
                        storage.store(this.tasks);
                        break;
                    case "unmark":
                        if (splitInput.length < 2) {
                            throw new MaryException("Choose task to be unmarked!");
                        }
                        this.tasks.unmarkTask(splitInput[1]);
                        storage.store(this.tasks);
                        break;
                    case "list":
                        this.tasks.listTasks();
                        break;
                    case "delete":
                        if (splitInput.length < 2) {
                            throw new MaryException("Choose task to be deleted!");
                        }
                        this.tasks.deleteTask(splitInput[1]);
                        storage.store(this.tasks);
                        break;
                    default:
                        System.out.println(
                                "Sorry I don't quite understand what you are saying, please use a different command!");
                }

                this.ui.printNewline();

                if (command.equals("bye")) {
                    break;
                }
            } catch (MaryException e) {
                System.out.println(e.getMessage());
            } catch (IndexOutOfBoundsException e) {
                System.out.println("There are no tasks at this position!");
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid numerical index!");
            }
        }
        inputScanner.close();
    }

    public static void main(String[] args) {
        new Mary("./Task.txt").run();
    }
}
