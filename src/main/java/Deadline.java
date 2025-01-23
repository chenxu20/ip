public class Deadline extends Task {
    String deadline;

    public Deadline(String des, String deadline) {
        super(des);
        this.deadline = deadline.split(" ", 2)[1];
    }

    public void mark() {
        super.mark();
        System.out.println(this.toString());
    }

    public void unmark() {
        super.unmark();
        System.out.println(this.toString());
    }

    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline + ")";
    }
}
