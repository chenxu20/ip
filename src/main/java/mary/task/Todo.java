package mary.task;

public class Todo extends Task {

    public Todo(String input, int completed) {
        super(input, completed);
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

    @Override
    public String writeTask() {
        return "T|" + (super.printCompletionStatus() ? 1 : 0) + "|" + super.printName();
    }
}
