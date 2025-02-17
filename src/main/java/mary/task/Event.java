package mary.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Specific type of Task which is an event.
 */
public class Event extends Task {
    private LocalDateTime start;
    private LocalDateTime end;

    /**
     * Creates a new Event task with a specified description, completion status,
     * starting time and ending time.
     *
     * @param des       Description of task.
     * @param completed The completion status (0 for incomplete, 1 for completed).
     * @param start     Starting date and time of the task.
     * @param end       Ending date and time of the task.
     */
    public Event(String des, int completed, LocalDateTime start, LocalDateTime end) {
        super(des, completed);
        this.start = start;
        this.end = end;
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
        return "[E]" + super.toString() + " (from: "
                + this.start.format(DateTimeFormatter.ofPattern("d MMM YYYY h.mma"))
                + " to: " + this.end.format(DateTimeFormatter.ofPattern("d MMM YYYY h.mma")) + ")";
    }

    /**
     * Stores the details of the task in a specific format back into the file.
     */
    @Override
    public String writeTask() {
        return "E|" + (super.printCompletionStatus() ? 1 : 0) + "|" + super.printName() + "|" + this.start + "|"
                + this.end;
    }

    @Override
    public String taskType() {
        return "E";
    }

    @Override
    protected void updateStartTime(LocalDateTime newStart) {
        this.start = newStart;
    }

    @Override
    protected void updateEndTime(LocalDateTime newEnd) {
        this.end = newEnd;
    }

    @Override
    protected void updateDeadline(LocalDateTime newDeadline) {
        return;
    }

    @Override
    protected LocalDateTime getStartTime() {
        return this.start;
    }

    @Override
    protected LocalDateTime getEndTime() {
        return this.end;
    }
}
