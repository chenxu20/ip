package mary.parser;

import java.time.DateTimeException;
import java.time.LocalDateTime;

import mary.exception.MaryException;
import mary.task.Deadline;
import mary.task.Event;
import mary.task.TaskList;
import mary.task.Todo;

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
        return taskList.addToDoTask(new Todo(input, 0));
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
            if (deadlineDateTime.length != 3) {
                throw new MaryException(
                        "Wrong format! Format of task deadline: "
                                + "\\\"deadline <task description> /by YYYY-MM-DD HH:MM\\\"");
            }
            String deadlineDate = deadlineDateTime[1];
            String deadlineTime = deadlineDateTime[2];
            LocalDateTime deadline = LocalDateTime.parse(deadlineDate + "T" + deadlineTime + ":00");

            response = taskList.addDeadlineTask(new Deadline(extractTaskDetails[0].trim(), 0, deadline));
        } catch (DateTimeException e) {
            System.out.println("Format of deadline is wrong!");
            System.out.println("Format of task deadline: " + "\"deadline <task description> /by YYYY-MM-DD HH:MM\"");
        }

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
            if (startDateTime.length != 3) {
                throw new MaryException(
                        "Missing starting date! Format of event duration: "
                                + "\\\"event <event description> /from YYYY-MM-DD HH:MM /to YYYY-MM-DD HH:MM\\\".");
            }
            String startDate = startDateTime[1];
            String startTime = startDateTime[2];
            LocalDateTime start = LocalDateTime.parse(startDate + "T" + startTime + ":00");

            String[] endDateTime = extractTaskDetails[2].split(" ");
            String endDate;
            String endTime;
            if (endDateTime.length == 2) {
                endDate = startDate;
                endTime = endDateTime[1];
            } else if (endDateTime.length == 3) {
                endDate = endDateTime[1];
                endTime = endDateTime[2];
            } else {
                throw new MaryException(
                        "Missing ending date! Format of event duration: "
                                + "\\\"event <event description> /from YYYY-MM-DD HH:MM /to YYYY-MM-DD HH:MM\\\".");
            }
            LocalDateTime end = LocalDateTime.parse(endDate + "T" + endTime + ":00");

            if (end.isBefore(start)) {
                throw new MaryException("End date cannot be before start date!");
            }

            response = taskList.addEventTask(new Event(extractTaskDetails[0].trim(), 0, start, end));
        } catch (DateTimeException e) {
            System.out.println("Format of start or end date is wrong!");
            System.out.println(
                    "Format of event duration: \"event <event description> /from YYYY-MM-DD HH:MM "
                            + "/to YYYY-MM-DD HH:MM\". "
                            + "You can omit the end date if it is the same as the starting date, "
                            + "but you cannot omit the end time!");
        }
        return response;
    }
}
