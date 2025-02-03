package mary.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime start;
    private LocalDateTime end;

    public Event(String des, int completed, LocalDateTime start, LocalDateTime end) {
        super(des, completed);
        this.start = start;
        this.end = end;
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
        return "[E]" + super.toString() + " (from: "
                + this.start.format(DateTimeFormatter.ofPattern("d MMM YYYY h.mma"))
                + " to: " + this.end.format(DateTimeFormatter.ofPattern("d MMM YYYY h.mma")) + ")";
    }

    @Override
    public String writeTask() {
        return "E|" + (super.printCompletionStatus() ? 1 : 0) + "|" + super.printName() + "|" + this.start + "|"
                + this.end;
    }
}
