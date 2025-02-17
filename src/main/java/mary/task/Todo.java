package mary.task;

import java.time.LocalDateTime;

/**
 * Specific type of Task which is a Todo task.
 */
public class Todo extends Task {

    /**
     * Creates a new Event task with a specified description and completion status.
     *
     * @param input     Description of task.
     * @param completed The completion status (0 for incomplete, 1 for completed).
     */
    public Todo(String input, int completed) {
        super(input, completed);
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
        return "[T]" + super.toString();
    }

    /**
     * Stores the details of the task in a specific format back into the file.
     */
    @Override
    public String writeTask() {
        return "T|" + (super.printCompletionStatus() ? 1 : 0) + "|" + super.printName();
    }

    @Override
    public String taskType() {
        return "T";
    }

    @Override
    protected void updateDeadline(LocalDateTime newDeadline) {
        return;
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
