# Poodle User Guide

hiiiiii from
           ╔╦╗
           ║║║
╔══╦══╦══╦═╝║║╔══╗
║╔╗║╔╗║╔╗║╔╗║║║║═╣
║╚╝║╚╝║╚╝║╚╝║╚╣║═╣
║╔═╩══╩══╩══╩═╩══╝
║║
╚╝

is there anything i can do for you?

Poodle is a friendly task management chatbot that helps you keep track of your todos, deadlines, and events. With its cute personality and simple command-based interface, Poodle makes task management both efficient and enjoyable!

## Adding todos

Add a simple task without any date or time constraints.

Format: `todo <description>`

Example: `todo read book`

```
--------------------------------------------
okie i added your task:
[T][ ] read book
now you have 1 tasks to dooo
--------------------------------------------
```

## Adding deadlines

Add a task that needs to be done before a specific date/time.

Format: `deadline <description> /by <date>`

Example: `deadline submit assignment /by 2023-12-01`

```
--------------------------------------------
okie i added your task:
[D][ ] submit assignment (by: 2023-12-01)
now you have 2 tasks to dooo
--------------------------------------------
```

## Adding events

Add a task that starts at a specific time and ends at another time.

Format: `event <description> /from <start_time> /to <end_time>`

Example: `event team meeting /from 2pm /to 4pm`

```
--------------------------------------------
okie i added your task:
[E][ ] team meeting (from: 2pm to: 4pm)
now you have 3 tasks to dooo
--------------------------------------------
```

## Viewing all tasks

Display all your current tasks in a numbered list.

Format: `list`

Example: `list`

```
--------------------------------------------
1.[T][ ] read book
2.[D][ ] submit assignment (by: 2023-12-01)
3.[E][ ] team meeting (from: 2pm to: 4pm)
--------------------------------------------
```

## Marking tasks as done

Mark a task as completed by specifying its number from the list.

Format: `mark <task_number>`

Example: `mark 1`

```
--------------------------------------------
yay good job! the task is done c:
 [T][X] read book
--------------------------------------------
```

## Unmarking tasks

Mark a previously completed task as not done by specifying its number.

Format: `unmark <task_number>`

Example: `unmark 1`

```
--------------------------------------------
oh nooo go do your task :c
 [T][ ] read book
--------------------------------------------
```

## Deleting tasks

Remove a task from your list permanently by specifying its number.

Format: `delete <task_number>`

Example: `delete 2`

```
--------------------------------------------
okie i deleted your task:
[D][ ] submit assignment (by: 2023-12-01)
now you have 2 tasks left to dooo
--------------------------------------------
```

## Finding tasks

Search for tasks containing specific keywords in their description.

Format: `find <keyword>`

Example: `find book`

```
--------------------------------------------
yay here are the tasks i found: 
1.[T][ ] read book
--------------------------------------------
```

## Exiting the program

Close Poodle and save your tasks.

Format: `bye`

Example: `bye`

```
--------------------------------------------
awwwww bye :c hope to see you again soon! <3
--------------------------------------------
```

## Command Format

- Words in `<>` are parameters to be supplied by you
    - e.g. in `todo <description>`, `<description>` is a parameter which can be used as `todo read book`
- For deadlines, use `/by` to specify the deadline
- For events, use `/from` and `/to` to specify start and end times
- Task numbers refer to the numbers shown in the `list` command

## Error Handling

Poodle will show helpful error messages if you:
- Use unknown commands
- Forget to provide required arguments
- Use incorrect formats for deadlines or events

All your tasks are automatically saved and will be loaded when you restart Poodle!

