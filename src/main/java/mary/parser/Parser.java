package mary.parser;

import java.time.DateTimeException;
import java.time.LocalDateTime;

import mary.exception.MaryException;
import mary.task.Deadline;
import mary.task.Event;
import mary.task.TaskList;
import mary.task.Todo;
import mary.util.Constants;

/**
 * Parses input from users to ensure that the right commands are given to the
 * chatbot. Creates tasks accordingly after ensuring that input is valid.
 */
public class Parser {

    /**
     * Takes in raw input from the user to return the first word which is the
     * command.
     *
     * @param input Raw input from the user.
     * @return A word to check if it is in the valid list of commands.
     */
    public static String parseInput(String input) {
        return input.split(" ", 2)[0].toLowerCase();
    }

    /**
     * Helps to add a ToDo task to the list of tasks.
     *
     * @param input    Task description.
     * @param taskList An instance of the TaskList containing the list of tasks.
     */
    public static String parseToDo(String input, TaskList taskList) {
        return taskList.addToDoTask(new Todo(input, Constants.INCOMPLETE));
    }

    /**
     * Helps to add a Deadline task to the list of tasks.
     *
     * @param input    String containing task description and the deadline of the
     *                 task.
     * @param taskList An instance of the TaskList containing the list of tasks.
     * @throws MaryException If format of string is incorrect due to missing task
     *                       description or invalid date and time format.
     */
    public static String parseDeadline(String input, TaskList taskList) throws MaryException {
        String response = "";
        try {
            String[] extractTaskDetails = input.split("/");
            if (extractTaskDetails.length < 2) {
                throw new MaryException(
                        "Wrong format! Format of task deadline: "
                                + "\\\"deadline <task description> /by YYYY-MM-DD HH:MM\\\"");
            }

            String[] deadlineDateTime = extractTaskDetails[1].split(" ");
            LocalDateTime deadline = parseDeadlineDateTime(deadlineDateTime);
            response = taskList.addDeadlineTask(new Deadline(extractTaskDetails[0].trim(),
                Constants.INCOMPLETE, deadline));
        } catch (DateTimeException e) {
            throw new MaryException("Format of deadline is wrong! Format of task deadline: "
                + "\"deadline <task description> /by YYYY-MM-DD HH:MM\"");
        }
        assert response != null;
        return response;
    }

    /**
     * Helps to add an Event task to the list of tasks.
     *
     * @param input    String containing task description, the starting date of the
     *                 task and the ending date of the task.
     * @param taskList An instance of the TaskList containing the list of tasks.
     * @throws MaryException If format of string is incorrect due to missing task
     *                       description or invalid date and time format.
     */
    public static String parseEvent(String input, TaskList taskList) throws MaryException {
        String response = "";
        try {
            String[] extractTaskDetails = input.split("/");

            if (extractTaskDetails.length < 3) {
                throw new MaryException(
                        "Wrong format! Format of event duration: "
                                + "\\\"event <event description> /from YYYY-MM-DD HH:MM /to YYYY-MM-DD HH:MM\\\".");
            }

            String[] startDateTime = extractTaskDetails[1].split(" ");
            String[] endDateTime = extractTaskDetails[2].split(" ");
            LocalDateTime[] startAndEndTime = parseEventDateTime(startDateTime, endDateTime);

            LocalDateTime start = startAndEndTime[0];
            LocalDateTime end = startAndEndTime[1];

            if (end.isBefore(start)) {
                throw new MaryException("End date cannot be before start date!");
            }

            response = taskList.addEventTask(new Event(extractTaskDetails[0].trim(), Constants.INCOMPLETE, start, end));
        } catch (DateTimeException e) {
            throw new MaryException("Format of start or end date is wrong!"
                    + "Format of event duration: \"event <event description> /from YYYY-MM-DD HH:MM "
                    + "/to YYYY-MM-DD HH:MM\". "
                    + "You can omit the end date if it is the same as the starting date, "
                    + "but you cannot omit the end time!");
        }
        assert response != null;
        return response;
    }

    /**
     * Parses the Date and Time for Deadline Tasks
     * @param deadlineDateTime The String array containing the details of the deadline
     * @return LocalDateTime for Deadline Tasks
     * @throws MaryException If the format is incorrect
     */
    private static LocalDateTime parseDeadlineDateTime(String[] deadlineDateTime) throws MaryException {
        if (deadlineDateTime.length != 3) {
            throw new MaryException(
                    "Wrong format! Format of task deadline: "
                            + "\\\"deadline <task description> /by YYYY-MM-DD HH:MM\\\"");
        }

        String deadlineDate = deadlineDateTime[1];
        String deadlineTime = deadlineDateTime[2];
        return LocalDateTime.parse(deadlineDate + "T" + deadlineTime + ":00");
    }

    /**
     * Parses the Date and Time for the Event Tasks
     * @param eventStartTime The String array containing details of the start time
     * @param eventEndTime The String array containing details of the end time
     * @return Array for LocalDateTime containing start and end time of the event
     * @throws MaryException If the format is incorrect
     */
    private static LocalDateTime[] parseEventDateTime(String[] eventStartTime,
            String[] eventEndTime) throws MaryException {
        LocalDateTime[] startAndEndTime = new LocalDateTime[2];

        if (eventStartTime.length != 3) {
            throw new MaryException(
                    "Missing starting date! Format of event duration: "
                            + "\\\"event <event description> /from YYYY-MM-DD HH:MM /to YYYY-MM-DD HH:MM\\\".");
        }

        String startDate = eventStartTime[1];
        String startTime = eventStartTime[2];
        startAndEndTime[0] = LocalDateTime.parse(startDate + "T" + startTime + ":00");

        String endDate;
        String endTime;
        if (eventEndTime.length == 2) {
            endDate = startDate;
            endTime = eventEndTime[1];
        } else if (eventEndTime.length == 3) {
            endDate = eventEndTime[1];
            endTime = eventEndTime[2];
        } else {
            throw new MaryException(
                    "Missing ending date! Format of event duration: "
                            + "\\\"event <event description> /from YYYY-MM-DD HH:MM /to YYYY-MM-DD HH:MM\\\".");
        }
        startAndEndTime[1] = LocalDateTime.parse(endDate + "T" + endTime + ":00");

        return startAndEndTime;
    }

    /**
     * Parses the input for updating tasks.
     * @param input String containing task description and the deadline of the task.
     * @param taskList An instance of the TaskList containing the list of tasks.
     * @return Return the response for updating Task.
     * @throws IndexOutOfBoundsException When input index is greater than the size of the list.
     * @throws NumberFormatException When input is not an integer.
     * @throws MaryException When there are other formatting errors.
     */
    public static String parseUpdateTask(String input, TaskList taskList)
            throws IndexOutOfBoundsException, NumberFormatException, MaryException {
        String[] extractIndex = input.split(" ", 2);
        String index = extractIndex[0];

        String[] updatedDetails = extractIndex[1].split("/");
        String response = taskList.updateTask(index, updatedDetails);

        return response;
    }
}
