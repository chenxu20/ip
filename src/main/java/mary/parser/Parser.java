package mary.parser;

import mary.task.Deadline;
import mary.task.Event;
import mary.task.Todo;
import mary.task.TaskList;
import mary.exception.MaryException;

import java.time.DateTimeException;
import java.time.LocalDateTime;

public class Parser {

    public static String[] parseInput(String input) {
        return input.split(" ", 2);
    }

    public static void parseToDo(String input, TaskList taskList) {
        taskList.addToDoTask(new Todo(input, 0));
    }

    public static void parseDeadline(String input, TaskList taskList) {
        try {
            String[] extractTaskDetails = input.split("/");
            if (extractTaskDetails.length < 2) {
                throw new MaryException("Wrong format!");
            }

            String[] deadlineDateTime = extractTaskDetails[1].split(" ");
            if (deadlineDateTime.length < 3) {
                throw new MaryException("Missing deadline!");
            }
            String deadlineDate = deadlineDateTime[1];
            String deadlineTime = deadlineDateTime[2];
            LocalDateTime deadline = LocalDateTime.parse(deadlineDate + "T" + deadlineTime + ":00");

            taskList.addDeadlineTask(new Deadline(extractTaskDetails[0].trim(), 0, deadline));
        } catch (MaryException e) {
            System.out.println(e.getMessage());
            System.out.println("Format of task deadline: \"deadline <task description> /by YYYY-MM-DD HH:MM\"");
        } catch (DateTimeException e) {
            System.out.println("Format of deadline is wrong!");
            System.out.println("Format of task deadline: \"deadline <task description> /by YYYY-MM-DD HH:MM\"");
        }
    }

    public static void parseEvent(String input, TaskList taskList) {
        try {
            String[] extractTaskDetails = input.split("/");

            if (extractTaskDetails.length < 3) {
                throw new MaryException("Wrong format!");
            }

            String[] startDateTime = extractTaskDetails[1].split(" ");
            if (startDateTime.length < 3) {
                throw new MaryException("Missing starting date!");
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
                throw new MaryException("Missing ending date!");
            }
            LocalDateTime end = LocalDateTime.parse(endDate + "T" + endTime + ":00");

            if (end.isBefore(start)) {
                throw new MaryException("End date cannot be before start date!");
            }

            taskList.addEventTask(new Event(extractTaskDetails[0].trim(), 0, start, end));
        } catch (MaryException e) {
            System.out.println(e.getMessage());
            System.out.println(
                    "Format of event duration: \"event <event description> /from YYYY-MM-DD HH:MM /to YYYY-MM-DD HH:MM\". You can omit the end date if it is the same as the starting date, but you cannot omit the end time!");
        } catch (DateTimeException e) {
            System.out.println("Format of start or end date is wrong!");
            System.out.println(
                    "Format of event duration: \"event <event description> /from YYYY-MM-DD HH:MM /to YYYY-MM-DD HH:MM\". You can omit the end date if it is the same as the starting date, but you cannot omit the end time!");
        }
    }
}
