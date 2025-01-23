public class Event extends Task {
    String start;
    String end;

    public Event(String des, String start, String end) {
        super(des);
        this.start = start.split(" ", 2)[1].trim();
        this.end = end.split(" ", 2)[1].trim();
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
}
