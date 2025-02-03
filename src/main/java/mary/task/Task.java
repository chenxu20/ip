package mary.task;

public abstract class Task {
    private boolean completed;
    private String taskname;

    public Task(String taskname, int completed) {
        this.taskname = taskname;
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
            return "[X] " + this.taskname;
        }
        return "[ ] " + this.taskname;
    }

    public String printName() {
        return this.taskname;
    }

    public boolean printCompletionStatus() {
        return this.completed;
    }

    public abstract String writeTask();
}
