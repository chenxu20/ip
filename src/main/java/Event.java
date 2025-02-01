public class Event extends Task {
    private String start;
    private String end;

    public Event(String des, int completed, String start, String end) {
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
        return "[E]" + super.toString() + " (from: " + this.start + " to: " + this.end + ")";
    }

    @Override
    public String writeTask() {
        return "D|" + (super.printCompletionStatus() ? 1 : 0) + "|" + super.printName() + "|" + this.start + "|"
                + this.end;
    }
}
