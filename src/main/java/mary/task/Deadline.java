package mary.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime deadline;

    public Deadline(String des, int completed, LocalDateTime deadline) {
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
        return "[D]" + super.toString() + " (by: "
                + this.deadline.format(DateTimeFormatter.ofPattern("d MMM YYYY h.mma"))
                + ")";
    }

    @Override
    public String writeTask() {
        return "D|" + (super.printCompletionStatus() ? 1 : 0) + "|" + super.printName() + "|" + this.deadline;
    }
}
