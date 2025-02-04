package mary.task;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class TaskListTest {

    @Test
    public void markTask_invalidInput_throwsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> new TaskList().markTask("3"));
        assertThrows(NumberFormatException.class, () -> new TaskList().markTask("1one"));
        assertThrows(NumberFormatException.class, () -> new TaskList().markTask("adc"));
    }

    @Test
    public void unmarkTask_invalidInput_throwsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> new TaskList().unmarkTask("3"));
        assertThrows(NumberFormatException.class, () -> new TaskList().unmarkTask("1one"));
        assertThrows(NumberFormatException.class, () -> new TaskList().unmarkTask("adc"));
    }

    @Test
    public void deleteTask_invalidInput_throwsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> new TaskList().deleteTask("3"));
        assertThrows(NumberFormatException.class, () -> new TaskList().deleteTask("1one"));
        assertThrows(NumberFormatException.class, () -> new TaskList().deleteTask("adc"));
    }
}
