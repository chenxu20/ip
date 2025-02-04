package mary.task;

/**
 * Stores the important fields to a task such as completion status and task
 * description.
 */
public abstract class Task {
    private boolean completed;
    private String taskName;

    /**
     * Records the completion status and task description.
     * 
     * @param taskname  Description of task.
     * @param completed The completion status (0 for incomplete, 1 for completed).
     */
    public Task(String taskName, int completed) {
        this.taskName = taskName;
        if (completed == 1) {
            this.completed = true;
        } else {
            this.completed = false;
        }
    }

    /**
     * Marks a task as completed.
     */
    public void mark() {
        this.completed = true;
        System.out.println("Nice! I've marked this task as done:");
    }

    /**
     * Marks a task as incomplete.
     */
    public void unmark() {
        this.completed = false;
        System.out.println("OK, I've marked this task as not done yet:");
    }

    public String toString() {
        if (this.completed) {
            return "[X] " + this.taskName;
        }
        return "[ ] " + this.taskName;
    }

    /**
     * 
     * @return Description of task.
     */
    public String printName() {
        return this.taskName;
    }

    /**
     * 
     * @return Completion status of task.
     */
    public boolean printCompletionStatus() {
        return this.completed;
    }

    /**
     * Stores the details of the task in a specific format back into the file.
     */
    public abstract String writeTask();
}
