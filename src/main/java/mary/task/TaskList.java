package mary.task;

import java.util.ArrayList;

/**
 * An instance which contains the list of tasks that can be modified by users
 * during the execution of the program.
 */
public class TaskList {

    private ArrayList<Task> taskList = new ArrayList<>();

    /**
     * Initialises list of tasks
     *
     * @param taskList List of tasks read from file stored on the computer.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public TaskList() {
    }

    /**
     * Returns list of tasks
     *
     * @return List of tasks.
     */
    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * Informs user on the number of tasks in the list of tasks.
     */
    public String printNumberofTasks() {
        return "Now you have " + this.taskList.size() + " tasks in the list.";
    }

    /**
     * Informs user that task has been added successfully.
     */
    public String printTask() {
        return "\n" + "Got it. I've added this task:\n" + taskList.get(taskList.size() - 1).toString()
                + "\n" + this.printNumberofTasks();
    }

    /**
     * Lists out the details of the tasks in the list.
     */
    public String listTasks() {
        String response = "";
        int count = 1;
        response += "Here are the tasks in your list:\n";
        for (Task task : taskList) {
            response += (count++) + ". " + task.toString() + "\n";
        }
        return response;
    }

    /**
     * Facilitates the marking of task as completed.
     *
     * @param input Index of the task to be marked.
     * @throws IndexOutOfBoundsException When the input index is greater than the
     *                                   size of the list.
     * @throws NumberFormatException     When the input is not an integer.
     */
    public String markTask(String input) throws IndexOutOfBoundsException, NumberFormatException {
        return taskList.get(Integer.parseInt(input) - 1).mark();
    }

    /**
     * Facilitates the marking of task as incomplete.
     *
     * @param input Index of the task to be unmarked.
     * @throws IndexOutOfBoundsException When the input index is greater than the
     *                                   size of the list.
     * @throws NumberFormatException     When the input is not an integer.
     */
    public String unmarkTask(String input) throws IndexOutOfBoundsException, NumberFormatException {
        return taskList.get(Integer.parseInt(input) - 1).unmark();
    }

    /**
     * Facilitates the adding of Todo task to the list of tasks.
     *
     * @param task To be added to the list.
     */
    public String addToDoTask(Task task) {
        this.taskList.add(task);
        return this.printTask();
    }

    /**
     * Facilitates the adding of Deadline task to the list of tasks.
     *
     * @param task To be added to the list.
     */
    public String addDeadlineTask(Task task) {
        this.taskList.add(task);
        return this.printTask();
    }

    /**
     * Facilitates the adding of Event task to the list of tasks.
     *
     * @param task To be added to the list.
     */
    public String addEventTask(Task task) {
        this.taskList.add(task);
        return this.printTask();
    }

    /**
     * Facilitates the deletion of task.
     *
     * @param input Index of the task to be deleted.
     * @throws IndexOutOfBoundsException When the input index is greater than the
     *                                   size of the list.
     * @throws NumberFormatException     When the input is not an integer.
     */
    public String deleteTask(String input) throws IndexOutOfBoundsException, NumberFormatException {
        String response = "";
        int index = Integer.parseInt(input);
        response += taskList.get(index - 1).toString();
        this.taskList.remove(index - 1);
        response += "\nNoted. I've removed this task:\n";
        response += this.printNumberofTasks();
        return response;
    }

    /**
     * Finds tasks whose description contains the exact input from user.
     *
     * @param input Keywords used by user to filter for tasks.
     */
    public String findTask(String input) {
        String response = "";
        int count = 1;
        response += "Here are the matching tasks in your list:\n";
        for (Task task : taskList) {
            if (task.printName().contains(input)) {
                response += (count++) + ". " + task.toString() + "\n";
            }
        }
        return response;
    }
}
