package mary.task;

/**
 * Stores the important fields to a task such as completion status and task
 * description.
 */
public abstract class Task {
    private boolean isCompleted;
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
            this.isCompleted = true;
        } else {
            this.isCompleted = false;
        }
    }

    /**
     * Marks a task as completed.
     */
    public String mark() {
        this.isCompleted = true;
        return "Nice! I've marked this task as done:\n";
    }

    /**
     * Marks a task as incomplete.
     */
    public String unmark() {
        this.isCompleted = false;
        return "OK, I've marked this task as not done yet:\n";
    }

    @Override
    public String toString() {
        if (this.isCompleted) {
            return "[X] " + this.taskName;
        }
        return "[ ] " + this.taskName;
    }

    /**
     * Returns name of task
     *
     * @return Description of task.
     */
    public String printName() {
        return this.taskName;
    }

    /**
     * returns completion status of task
     *
     * @return Completion status of task.
     */
    public boolean printCompletionStatus() {
        return this.isCompleted;
    }

    /**
     * Stores the details of the task in a specific format back into the file.
     */
    public abstract String writeTask();
}
