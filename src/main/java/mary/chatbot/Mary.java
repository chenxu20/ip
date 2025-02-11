package mary.chatbot;

import mary.exception.MaryException;
import mary.parser.Parser;
import mary.storage.Storage;
import mary.task.TaskList;
import mary.ui.Ui;

/**
 * A chatbot that helps to organise and manage list of tasks that users key in.
 * Mary keeps track of a list of tasks in <code>TaskList</code> which is stored
 * locally on the computer.
 */
public class Mary {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Initialises TaskList by based on the input filepath.
     *
     * @param filePath filepath to where the file containing list of tasks is
     *                 located.
     */
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

    public String getResponse(String input) {

        String response = "";
        String command = Parser.parseInput(input);
        String[] splitInput = input.split(" ", 2);
        try {
            switch (command) {
            case "bye":
                response = this.ui.exit();
                break;
            case "todo":
                if (splitInput.length < 2) {
                    throw new MaryException("Task description is missing!");
                }
                response = Parser.parseToDo(splitInput[1], this.tasks);
                storage.store(this.tasks);
                break;
            case "deadline":
                if (splitInput.length < 2) {
                    throw new MaryException("Task description and deadline are missing!");
                }
                response = Parser.parseDeadline(splitInput[1], this.tasks);
                storage.store(this.tasks);
                break;
            case "event":
                if (splitInput.length < 2) {
                    throw new MaryException("Task description and event duration are missing!");
                }
                response = Parser.parseEvent(splitInput[1], this.tasks);
                storage.store(this.tasks);
                break;
            case "mark":
                if (splitInput.length < 2) {
                    throw new MaryException("Choose task to be marked!");
                }
                response = this.tasks.markTask(splitInput[1]);
                storage.store(this.tasks);
                break;
            case "unmark":
                if (splitInput.length < 2) {
                    throw new MaryException("Choose task to be unmarked!");
                }
                response = this.tasks.unmarkTask(splitInput[1]);
                storage.store(this.tasks);
                break;
            case "list":
                response = this.tasks.listTasks();
                break;
            case "delete":
                if (splitInput.length < 2) {
                    throw new MaryException("Choose task to be deleted!");
                }
                response = this.tasks.deleteTask(splitInput[1]);
                storage.store(this.tasks);
                break;
            case "find":
                if (splitInput.length < 2) {
                    throw new MaryException("Key in what you want to find!");
                }
                response = this.tasks.findTask(splitInput[1]);
                break;
            default:
                response = "Sorry I don't quite understand what you are saying, "
                        + "please use a different command!";
            }
        } catch (MaryException e) {
            System.out.println(e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("There are no tasks at this position!");
        } catch (NumberFormatException e) {
            System.out.println("Enter a valid numerical index!");
        }
        return response;
    }

    // public static void main(String[] args) {
    // new Mary("./Task.txt").run();
    // }
}
