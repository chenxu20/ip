package mary.task;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public TaskList() {
    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    public void printAddStatus() {
        System.out.println("Got it. I've added this task:");
    }

    public void printNumberofTasks() {
        System.out.println("Now you have " + this.taskList.size() + " tasks in the list.");
    }

    public void printTask() {
        this.printAddStatus();
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

    public void markTask(String input) {
        try {
            taskList.get(Integer.parseInt(input) - 1).mark();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("There are no tasks at this position!");
        }
    }

    public void unmarkTask(String input) {
        try {
            taskList.get(Integer.parseInt(input) - 1).unmark();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("There are no tasks at this position!");
        }
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

    public void deleteTask(String input) {
        try {
            int index = Integer.parseInt(input);
            System.out.println(taskList.get(index - 1).toString());
            this.taskList.remove(index - 1);
            System.out.println("Noted. I've removed this task:");
            this.printNumberofTasks();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("There are no tasks at this position!");
        }
    }
}
