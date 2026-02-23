# Genie User Guide

Genie is a **task manager chatbot** that helps you track your todos, deadlines, and events — all from the command line.

---

## Quick Start

1. Ensure you have **Java 11 or above** installed.
2. Download `Genie.jar` from the latest release.
3. Open a terminal in the folder containing the jar file.
4. Run: `java -jar Genie.jar`
5. Type a command and press Enter.

---

## Features

### Add a Todo — `todo`

Adds a task with no date attached.

**Format:** `todo DESCRIPTION`

**Example:**
```
todo read book
```
```
 Got it. I've added this task:
   [T][ ] read book
 Now you have 1 tasks in the list.
```

---

### Add a Deadline — `deadline`

Adds a task with a due date.

**Format:** `deadline DESCRIPTION /by DATE`

- `DATE` must be in `yyyy-MM-dd` format (e.g., `2025-12-01`)

**Example:**
```
deadline submit assignment /by 2025-12-01
```
```
 Got it. I've added this task:
   [D][ ] submit assignment (by: Dec 01 2025)
 Now you have 2 tasks in the list.
```

---

### Add an Event — `event`

Adds a task with a start and end time.

**Format:** `event DESCRIPTION /from START /to END`

**Example:**
```
event team meeting /from 2pm /to 4pm
```
```
 Got it. I've added this task:
   [E][ ] team meeting (from: 2pm to: 4pm)
 Now you have 3 tasks in the list.
```

---

### List All Tasks — `list`

Shows all tasks currently in your list.

**Format:** `list`

**Example:**
```
list
```
```
 Here are the tasks in your list:
 1.[T][ ] read book
 2.[D][ ] submit assignment (by: Dec 01 2025)
 3.[E][ ] team meeting (from: 2pm to: 4pm)
```

---

### Mark a Task as Done — `mark`

Marks the specified task as completed.

**Format:** `mark INDEX`

**Example:**
```
mark 2
```
```
 Nice! I've marked this task as done:
   [X] submit assignment (by: Dec 01 2025)
```

---

### Unmark a Task — `unmark`

Marks the specified task as not done.

**Format:** `unmark INDEX`

**Example:**
```
unmark 2
```
```
 OK, I've marked this task as not done yet:
   [ ] submit assignment (by: Dec 01 2025)
```

---

### Delete a Task — `delete`

Removes the specified task from the list.

**Format:** `delete INDEX`

**Example:**
```
delete 1
```
```
 Noted. I've removed this task:
   [T][ ] read book
 Now you have 2 tasks in the list.
```

---

### Find Tasks by Keyword — `find`

Searches for tasks whose description contains the given keyword. Case-insensitive.

**Format:** `find KEYWORD`

**Example:**
```
find book
```
```
 Here are the matching tasks in your list:
 1.[T][ ] read book
 2.[T][ ] return book
```

---

### Exit — `bye`

Exits Genie. Your tasks are automatically saved and will reload next time.

**Format:** `bye`

---

## Task Format Reference

| Symbol | Meaning      |
|--------|--------------|
| `T`    | Todo         |
| `D`    | Deadline     |
| `E`    | Event        |
| `X`    | Done         |
| ` `    | Not done     |

Each task is displayed as: `[TYPE][STATUS] description`

---

## Data Storage

Tasks are automatically saved to `data/genie.txt` after every change. No manual saving needed. The file is reloaded automatically on the next launch.
