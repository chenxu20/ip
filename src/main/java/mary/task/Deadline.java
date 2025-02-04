package mary.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Specific type of Task which has a deadline.
 */
public class Deadline extends Task {
    private LocalDateTime deadline;

    /**
     * Creates a new Deadline task with a specified description, completion status,
     * and due date.
     * 
     * @param des       Description of task.
     * @param completed The completion status (0 for incomplete, 1 for completed).
     * @param deadline  Due date and time of task.
     */
    public Deadline(String des, int completed, LocalDateTime deadline) {
        super(des, completed);
        this.deadline = deadline;
    }

    /**
     * Marks a task as completed.
     */
    @Override
    public void mark() {
        super.mark();
        System.out.println(this.toString());
    }

    /**
     * Marks a task as incomplete.
     */
    @Override
    public void unmark() {
        super.unmark();
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.deadline.format(DateTimeFormatter.ofPattern("d MMM YYYY h.mma"))
                + ")";
    }

    /**
     * Stores the details of the task in a specific format back into the file.
     */
    @Override
    public String writeTask() {
        return "D|" + (super.printCompletionStatus() ? 1 : 0) + "|" + super.printName() + "|" + this.deadline;
    }
}
