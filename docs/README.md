# Mary User Guide

Mary is a graphical user interface (GUI) task manager bot designed to help you organize your tasks efficiently. It supports various features to manage your to-dos, deadlines and events while providing easy way to track and find tasks.
![image](https://github.com/user-attachments/assets/173f89fc-e65e-490c-9c4a-5cf3c82f3dab)


## Getting started

1. Ensure that you have Java 17 or above installed.
2. Download the latest .jar file from [here](https://github.com/chenxu20/ip/releases/tag/Level-10).
3. Copy the file to the folder you want to put the jar file in.
4. Run the application using this command java -jar mary.jar
5. You should be greeted with popup window with Mary inside.

## Features
### 1. Todo
- Adds a simple task without any date.
- Format: todo "description"

### 2. Deadline
- Adds a task with a due date.
- Format: deadline "description" /by "yyyy-MM-dd HH:MM"
### 3. Event
- Adds an event with a scheduled start and end date.
- Format: todo "description" /from "yyyy-MM-dd HH:MM" /to "yyyy-MM-dd HH:MM"

### 4. Find
- Search for tasks containing a specific keywords.
- Format: find "keyword"

### 5. List
- Displays all tasks in your list.
- Format: list

### 6. Mark/Unmark
- Mark or unmark a task as complete or not complete.
- Format: mark "task_number"
- Format: unmark "task_number"

### 7. Update
- Update details of a task on the list currently
- Format: update "task_number" /"task detail header" "task detail content"
- e.g. update 2 /by 2023-11-01 01:01

> [!TIP]
> You can update task details without following any strict orders.
> Command names are non-case sensitive
> Date must follow this format: yyyy-MM-dd HH:MM
