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
     * @param taskList List of tasks read from file stored on the computer.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public TaskList() {
    }

    /**
     * Returns list of tasks
     * @return List of tasks.
     */
    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * Informs user on the number of tasks in the list of tasks.
     */
    public void printNumberofTasks() {
        System.out.println("Now you have " + this.taskList.size() + " tasks in the list.");
    }

    /**
     * Informs user that task has been added successfully.
     */
    public void printTask() {
        System.out.println("");
        System.out.println("Got it. I've added this task:");
        System.out.println(taskList.get(taskList.size() - 1).toString());
        this.printNumberofTasks();
    }

    /**
     * Lists out the details of the tasks in the list.
     */
    public void listTasks() {
        int count = 1;
        System.out.println("Here are the tasks in your list:");
        for (Task task : taskList) {
            System.out.println((count++) + ". " + task.toString());
        }
    }

    /**
     * Facilitates the marking of task as completed.
     *
     * @param input Index of the task to be marked.
     * @throws IndexOutOfBoundsException When the input index is greater than the
     *                                   size of the list.
     * @throws NumberFormatException     When the input is not an integer.
     */
    public void markTask(String input) throws IndexOutOfBoundsException, NumberFormatException {
        taskList.get(Integer.parseInt(input) - 1).mark();
    }

    /**
     * Facilitates the marking of task as incomplete.
     *
     * @param input Index of the task to be unmarked.
     * @throws IndexOutOfBoundsException When the input index is greater than the
     *                                   size of the list.
     * @throws NumberFormatException     When the input is not an integer.
     */
    public void unmarkTask(String input) throws IndexOutOfBoundsException, NumberFormatException {
        taskList.get(Integer.parseInt(input) - 1).unmark();
    }

    /**
     * Facilitates the adding of Todo task to the list of tasks.
     *
     * @param task To be added to the list.
     */
    public void addToDoTask(Task task) {
        this.taskList.add(task);
        this.printTask();
    }

    /**
     * Facilitates the adding of Deadline task to the list of tasks.
     *
     * @param task To be added to the list.
     */
    public void addDeadlineTask(Task task) {
        this.taskList.add(task);
        this.printTask();
    }

    /**
     * Facilitates the adding of Event task to the list of tasks.
     *
     * @param task To be added to the list.
     */
    public void addEventTask(Task task) {
        this.taskList.add(task);
        this.printTask();
    }

    /**
     * Facilitates the deletion of task.
     *
     * @param input Index of the task to be deleted.
     * @throws IndexOutOfBoundsException When the input index is greater than the
     *                                   size of the list.
     * @throws NumberFormatException     When the input is not an integer.
     */
    public void deleteTask(String input) throws IndexOutOfBoundsException, NumberFormatException {
        int index = Integer.parseInt(input);
        System.out.println(taskList.get(index - 1).toString());
        this.taskList.remove(index - 1);
        System.out.println("Noted. I've removed this task:");
        this.printNumberofTasks();
    }
}
