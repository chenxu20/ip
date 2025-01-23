public class Task {
    boolean completed = false;
    String taskname;

    public Task(String taskname) {
        this.taskname = taskname;
    }

    public void mark() {
        this.completed = true;
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(this.toString());
    }

    public void unmark() {
        this.completed = false;
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(this.toString());
    }

    public String toString() {
        if (this.completed) {
            return "[X] " + this.taskname;
        }
        return "[ ] " + this.taskname;
    }
}
