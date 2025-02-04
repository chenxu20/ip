package mary.parser;

import org.junit.jupiter.api.Test;

import mary.exception.MaryException;
import mary.task.TaskList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {

    @Test
    public void parseInput_success() {
        assertEquals("bye", Parser.parseInput("bye"));
        assertEquals("todo", Parser.parseInput("todo all my coding work"));
        assertEquals("deadline", Parser.parseInput("deadline work tomorrow"));
        assertEquals("mark", Parser.parseInput("mark everything"));
        assertEquals("event", Parser.parseInput("event project meeting"));
    }

    @Test
    public void parseDeadline_invalidInputs_throwsMaryException() {
        assertThrows(MaryException.class, () -> Parser.parseDeadline("return books /by 2023-01-02", new TaskList()));
        assertThrows(MaryException.class, () -> Parser.parseDeadline("return books", new TaskList()));
        assertThrows(MaryException.class,
                () -> Parser.parseDeadline("return books /by 2023-01-02 12:12 20 seconds", new TaskList()));
    }

    @Test
    public void parseEvent_invalidInputs_throwsMaryException() {
        assertThrows(MaryException.class, () -> Parser.parseEvent("event project meeting", new TaskList()));
        assertThrows(MaryException.class,
                () -> Parser.parseEvent("event project meeting /from 2023-01-02 12:00", new TaskList()));
        assertThrows(MaryException.class,
                () -> Parser.parseEvent("event project meeting /from 2023-01-02 12:00 /to", new TaskList()));
    }
}
