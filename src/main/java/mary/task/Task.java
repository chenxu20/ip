package mary.task;

public abstract class Task {
    private boolean completed;
    private String taskName;

    public Task(String taskName, int completed) {
        this.taskName = taskName;
        if (completed == 1) {
            this.completed = true;
        } else {
            this.completed = false;
        }
    }

    public void mark() {
        this.completed = true;
        System.out.println("Nice! I've marked this task as done:");
    }

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

    public String printName() {
        return this.taskName;
    }

    public boolean printCompletionStatus() {
        return this.completed;
    }

    public abstract String writeTask();
}
