public class Todo extends Task {

    public Todo(String input) {
        super(input);
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
        return "[T]" + super.toString();
    }
}
