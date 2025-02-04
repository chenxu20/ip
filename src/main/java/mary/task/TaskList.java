package mary.task;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskList = new ArrayList<>();

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public TaskList() {
    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    public void printNumberofTasks() {
        System.out.println("Now you have " + this.taskList.size() + " tasks in the list.");
    }

    public void printTask() {
        System.out.println("");
        System.out.println("Got it. I've added this task:");
        System.out.println(taskList.get(taskList.size() - 1).toString());
        this.printNumberofTasks();
    }

    public void listTasks() {
        int count = 1;
        System.out.println("Here are the tasks in your list:");
        for (Task task : taskList) {
            System.out.println((count++) + ". " + task.toString());
        }
    }

    public void markTask(String input) throws IndexOutOfBoundsException, NumberFormatException {
        taskList.get(Integer.parseInt(input) - 1).mark();
    }

    public void unmarkTask(String input) throws IndexOutOfBoundsException, NumberFormatException {
        taskList.get(Integer.parseInt(input) - 1).unmark();
    }

    public void addToDoTask(Task task) {
        this.taskList.add(task);
        this.printTask();
    }

    public void addDeadlineTask(Task task) {
        this.taskList.add(task);
        this.printTask();
    }

    public void addEventTask(Task task) {
        this.taskList.add(task);
        this.printTask();
    }

    public void deleteTask(String input) throws IndexOutOfBoundsException, NumberFormatException {
        int index = Integer.parseInt(input);
        System.out.println(taskList.get(index - 1).toString());
        this.taskList.remove(index - 1);
        System.out.println("Noted. I've removed this task:");
        this.printNumberofTasks();
    }

    /**
     * Finds tasks whose description contains the exact input from user.
     * @param input Keywords used by user to filter for tasks.
     */
    public void findTask(String input) {
        int count = 1;
        System.out.println("Here are the matching tasks in your list:");
        for (Task task : taskList) {
            if (task.printName().contains(input)) {
                System.out.println((count++) + ". " + task.toString());
            }
        }
    }
}
