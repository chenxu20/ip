public class Deadline extends Task {
    private String deadline;

    public Deadline(String des, int completed, String deadline) {
        super(des, completed);
        this.deadline = deadline;
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

    @Override
    public String writeTask() {
        return "D|" + (super.printCompletionStatus() ? 1 : 0) + "|" + super.printName() + "|" + this.deadline;
    }
}
