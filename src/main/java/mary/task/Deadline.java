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
    public String mark() {
        return super.mark() + this.toString();
    }

    /**
     * Marks a task as incomplete.
     */
    @Override
    public String unmark() {
        return super.unmark() + this.toString();
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

    @Override
    public String taskType() {
        return "D";
    }

    @Override
    protected void updateDeadline(LocalDateTime newDeadline) {
        this.deadline = newDeadline;
    }

    @Override
    protected void updateStartTime(LocalDateTime newStart) {
        return;
    }

    @Override
    protected void updateEndTime(LocalDateTime newEnd) {
        return;
    }

    @Override
    protected LocalDateTime getStartTime() {
        return null;
    }

    @Override
    protected LocalDateTime getEndTime() {
        return null;
    }
}
