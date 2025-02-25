package mary.task;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

import mary.exception.MaryException;

/**
 * An instance which contains the list of tasks that can be modified by users
 * during the execution of the program.
 */
public class TaskList {

    private ArrayList<Task> taskList = new ArrayList<>();

    /**
     * Initialises list of tasks
     *
     * @param taskList List of tasks read from file stored on the computer.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public TaskList() {
    }

    /**
     * Returns list of tasks
     *
     * @return List of tasks.
     */
    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * Informs user on the number of tasks in the list of tasks.
     *
     * @return Return a String containing number of tasks.
     */
    public String printNumberofTasks() {
        return "Now you have " + this.taskList.size() + " tasks in the list.";
    }

    /**
     * Informs user that task has been added successfully.
     *
     * @return Return details of task.
     */
    public String printTask() {
        return "\n" + "Got it. I've added this task:\n" + taskList.get(taskList.size() - 1).toString()
                + "\n" + this.printNumberofTasks();
    }

    /**
     * Lists out the details of the tasks in the list.
     */
    public String listTasks() {
        String response = "Here are the tasks in your list:\n";
        int count = 1;

        for (Task task : taskList) {
            response += (count++) + ". " + task.toString() + "\n";
        }
        return response;
    }

    /**
     * Facilitates the marking of task as completed.
     *
     * @param input Index of the task to be marked.
     * @return Response when task is marked.
     * @throws IndexOutOfBoundsException When the input index is greater than the
     *                                   size of the list.
     * @throws NumberFormatException     When the input is not an integer.
     */
    public String markTask(String input) throws IndexOutOfBoundsException, NumberFormatException {
        return taskList.get(Integer.parseInt(input) - 1).mark();
    }

    /**
     * Facilitates the marking of task as incomplete.
     *
     * @param input Index of the task to be unmarked.
     * @return Response when task is unmarked.
     * @throws IndexOutOfBoundsException When the input index is greater than the
     *                                   size of the list.
     * @throws NumberFormatException     When the input is not an integer.
     */
    public String unmarkTask(String input) throws IndexOutOfBoundsException, NumberFormatException {
        return taskList.get(Integer.parseInt(input) - 1).unmark();
    }

    /**
     * Facilitates the adding of Todo task to the list of tasks.
     *
     * @param task To be added to the list.
     * @return Response when a Todo Task is added.
     */
    public String addToDoTask(Task task) {
        this.taskList.add(task);
        assert taskList.size() > 0;
        return this.printTask();
    }

    /**
     * Facilitates the adding of Deadline task to the list of tasks.
     *
     * @param task To be added to the list.
     * @return Response when a Deadline Task is added.
     */
    public String addDeadlineTask(Task task) {
        this.taskList.add(task);
        assert taskList.size() > 0;
        return this.printTask();
    }

    /**
     * Facilitates the adding of Event task to the list of tasks.
     *
     * @param task To be added to the list.
     * @return Response when an Event Task is added.
     */
    public String addEventTask(Task task) {
        this.taskList.add(task);
        assert taskList.size() > 0;
        return this.printTask();
    }

    /**
     * Facilitates the deletion of task.
     *
     * @param input Index of the task to be deleted.
     * @return Response when a task is deleted.
     * @throws IndexOutOfBoundsException When the input index is greater than the
     *                                   size of the list.
     * @throws NumberFormatException     When the input is not an integer.
     */
    public String deleteTask(String input) throws IndexOutOfBoundsException, NumberFormatException {
        int index = Integer.parseInt(input);
        String response = taskList.get(index - 1).toString();
        this.taskList.remove(index - 1);

        response += "\nNoted. I've removed this task:\n";
        response += this.printNumberofTasks();
        return response;
    }

    /**
     * Finds tasks whose description contains the exact input from user.
     *
     * @param input Keywords used by user to filter for tasks.
     * @return Response when relevant tasks are found.
     */
    public String findTask(String input) {
        String response = "Here are the matching tasks in your list:\n";
        int count = 1;

        for (Task task : taskList) {
            if (task.printName().contains(input)) {
                response += (count++) + ". " + task.toString() + "\n";
            }
        }
        return response;
    }

    /**
     * Point of entry to updating a specific task, cannot update task to another type
     * @param index Index of the task to be updated.
     * @param updatedDetails Updated details for the task.
     * @return Response when task is updated successfully.
     * @throws IndexOutOfBoundsException When input index is greater than the size of the list.
     * @throws NumberFormatException When input is not an integer.
     * @throws MaryException When there are other formatting errors.
     */
    public String updateTask(String index, String[] updatedDetails)
            throws IndexOutOfBoundsException, NumberFormatException, MaryException {
        int taskIndex = Integer.parseInt(index);
        Task taskToBeUpdated = this.taskList.get(taskIndex - 1);
        String taskType = taskToBeUpdated.taskType();
        String[] filteredUpdatedDetails = Arrays.copyOfRange(updatedDetails, 1, updatedDetails.length);

        ArrayList<String> updatedTaskDetailHeader = new ArrayList<>();
        ArrayList<String> updatedTaskDetailContent = new ArrayList<>();

        splitUpdateArguments(updatedTaskDetailHeader, updatedTaskDetailContent, filteredUpdatedDetails);
        checkUpdateValidity(taskType, updatedTaskDetailHeader);

        switch (taskType) {
        case "T":
            updateTodoTask(taskToBeUpdated, updatedTaskDetailHeader, updatedTaskDetailContent);
            break;
        case "D":
            updateDeadlineTask(taskToBeUpdated, updatedTaskDetailHeader, updatedTaskDetailContent);
            break;
        case "E":
            updateEventTask(taskToBeUpdated, updatedTaskDetailHeader, updatedTaskDetailContent);
            break;
        default:
        }

        return "Successfully updated task!\n" + "Updated Task: " + taskToBeUpdated.toString();
    }

    /**
     * Updates a Todo Task.
     * @param task Task that needs to be updated.
     * @param detailHeader Header of the detail to be updated.
     * @param detailContent Content of the detail to input.
     */
    private void updateTodoTask(Task task, ArrayList<String> detailHeader, ArrayList<String> detailContent) {
        task.updateDescription(detailContent.get(0));
    }

    /**
     * Updates a Deadline Task.
     * @param task Task that needs to be updated.
     * @param detailHeader Header of the detail to be updated.
     * @param detailContent Content of the detail to input.
     * @throws MaryException When there are formatting errors.
     */
    private void updateDeadlineTask(Task task, ArrayList<String> detailHeader,
            ArrayList<String> detailContent) throws MaryException {
        try {
            String[] newDeadline;
            LocalDateTime formattedNewDeadline;
            for (int i = 0; i < detailHeader.size(); i++) {
                switch(detailHeader.get(i)) {
                case "by":
                    newDeadline = detailContent.get(i).split(" ");
                    formattedNewDeadline = LocalDateTime.parse(newDeadline[0] + "T" + newDeadline[1] + ":00");
                    break;
                default:
                }
            }

            for (int i = 0; i < detailHeader.size(); i++) {
                switch(detailHeader.get(i)) {
                case "description":
                    task.updateDescription(detailContent.get(i));
                    break;
                case "by":
                    newDeadline = detailContent.get(i).split(" ");
                    formattedNewDeadline = LocalDateTime.parse(newDeadline[0] + "T" + newDeadline[1] + ":00");
                    task.updateDeadline(formattedNewDeadline);
                    break;
                default:
                }
            }
        } catch (DateTimeException e) {
            throw new MaryException("Ensure your format for the dates are correct! Correct format: YYYY-MM-DD HH:MM");
        } catch (IndexOutOfBoundsException e) {
            throw new MaryException("Ensure your format for the dates are correct! Correct format: YYYY-MM-DD HH:MM");
        }
    }

    /**
     * Updates an Event Task.
     * @param task Task that needs to be updated.
     * @param detailHeader Header of the detail to be updated.
     * @param detailContent Content of the detail to input.
     * @throws MaryException When there are formatting errors.
     */
    private void updateEventTask(Task task, ArrayList<String> detailHeader,
            ArrayList<String> detailContent) throws MaryException {
        try {
            String[] newStartTime;
            LocalDateTime formattedNewStartTime = task.getStartTime();

            String[] newEndTime;
            LocalDateTime formattedNewEndTime = task.getEndTime();
            for (int i = 0; i < detailHeader.size(); i++) {
                switch(detailHeader.get(i)) {
                case "from":
                    newStartTime = detailContent.get(i).split(" ");
                    formattedNewStartTime = LocalDateTime.parse(newStartTime[0] + "T" + newStartTime[1] + ":00");
                    break;
                case "to":
                    newEndTime = detailContent.get(i).split(" ");
                    formattedNewEndTime = LocalDateTime.parse(newEndTime[0] + "T" + newEndTime[1] + ":00");
                    break;
                default:
                }
            }

            if (formattedNewEndTime.isBefore(formattedNewStartTime)) {
                throw new MaryException("End date cannot be before start date!");
            }

            for (int i = 0; i < detailHeader.size(); i++) {
                switch(detailHeader.get(i)) {
                case "description":
                    task.updateDescription(detailContent.get(i));
                    break;
                case "from":
                    task.updateStartTime(formattedNewStartTime);
                    break;
                case "to":
                    task.updateEndTime(formattedNewEndTime);
                    break;
                default:
                }
            }
        } catch (DateTimeException e) {
            throw new MaryException("Ensure your format for the dates are correct! Correct format: YYYY-MM-DD HH:MM");
        } catch (IndexOutOfBoundsException e) {
            throw new MaryException("Ensure your format for the dates are correct! Correct format: YYYY-MM-DD HH:MM");
        }
    }

    /**
     * Splits input into its corresponding header and content.
     * @param detailHeader Contains the different headers to be updated.
     * @param detailContent Contains the content to be updated.
     * @param updatedDetails Contains the headers and the content to be updated.
     */
    private void splitUpdateArguments(ArrayList<String> detailHeader,
            ArrayList<String> detailContent, String[] updatedDetails) {
        for (String arguments : updatedDetails) {
            String[] argumentHeaderAndContent = arguments.split(" ", 2);
            detailHeader.add(argumentHeaderAndContent[0]);
            detailContent.add(argumentHeaderAndContent[1].trim());
        }
    }

    /**
     * Checks whether the update is valid. Checks if the task type and headers are right.
     * @param taskType Task type of the task at the index.
     * @param detailHeader Checks whether header is valid.
     * @throws MaryException When there are formatting errors.
     */
    private void checkUpdateValidity(String taskType, ArrayList<String> detailHeader) throws MaryException {
        switch (taskType) {
        case "T":
            for (String header : detailHeader) {
                if (!header.equals("description") || detailHeader.size() > 1) {
                    throwTodoException();
                }
            }
            break;
        case "D":
            for (String header : detailHeader) {
                if ((!header.equals("description") && !header.equals("by")) || detailHeader.size() > 2) {
                    throwDeadlineException();
                }
            }
            break;
        case "E":
            for (String header : detailHeader) {
                if ((!header.equals("description") && !header.equals("from") && !header.equals("to"))
                    || detailHeader.size() > 3) {
                    throwEventException();
                }
            }
            break;
        default:
            throw new MaryException("Task file is corrupted!");
        }
    }

    /**
     * Throws an exception when task type is incompatible.
     * @throws MaryException When the task type is incompatible.
     */
    private void throwTodoException() throws MaryException {
        throw new MaryException("Enter a valid field to update! "
                + "Format: \"Update <number> /description <abc>\"");
    }

    /**
     * Throws an exception when task type is incompatible.
     * @throws MaryException When the task type is incompatible.
     */
    private void throwDeadlineException() throws MaryException {
        throw new MaryException("Enter a valid field to update! "
                + "Format: \"Update <number> /description <abc> /by YYYY-MM-DD HH:MM");
    }

    /**
     * Throws an exception when task type is incompatible.
     * @throws MaryException When the task type is incompatible.
     */
    private void throwEventException() throws MaryException {
        throw new MaryException("Enter a valid field to update! "
                + "Format: \"Update <number> /description <abc> /from YYYY-MM-DD HH:MM /to YYYY-MM-DD HH:MM");
    }
}
