# User Guide

**Duke** is a desktop application which helps the user to **manage their tasks**
using the Command Line Interface(CLI).

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

## Features 

* Viewing help: `help`
* Adding a task:
  * Adding todo task: `todo`
  * Adding deadline task: `deadline`
  * Adding event task: `event`
* Marking a task as done: `done`
* Deleting a task: `delete`
* Clearing all entries: `clear`
* Listing all tasks: `list`
* Searching tasks by keyword: `find`
* Exiting the program: `bye`
* Saving the data

---

### Viewing help: `help`

Shows a message explaining how to each feature is used.

**Format**: `help`
<br /> <br />


### Adding todo task: `todo`

Adds a todo task to the task list.

**Format**: `todo DESCRIPTION`
* Description of the task cannot be empty.

**Example of usage:**
* `todo CS2113T Homework`
<br /> <br />

### Adding deadline task: `deadline`

Adds a deadline task to the task list.

**Format**: `deadline DESCRIPTION /by DUE DATE`
* Description and due date of the task cannot be empty.
* When due date is inserted in YYYY-MM-DD format, Duke treats it as an actual
  date variable rather than treating it as just a String.

**Example of usage:**
* `deadline CS2113T Homework /by 2021-3-5`
<br /> <br />

### Adding event task: `event`

Adds an event task to the task list.

**Format**: `event DESCRIPTION /at EVENT DATE`
* Description and event date of the task cannot be empty.
* When event date is inserted in YYYY-MM-DD format, Duke treats it as an actual
  date variable rather than treating it as just a String.
  
**Example of usage:**
* `event CS2113T Homework /at 2021-3-5`
<br /> <br />

### Marking a task as done: `done`

Marks a specific task as done.

**Format**: `done INDEX`
* Marks the task at the specified INDEX as done.
* The index refers to the index number shown in the displayed task list.
* The index must be a positive integer.

**Example of usage:**
* `done 2`
* Above command marks the second task in the task list as done.

**Expected outcome:**
>Nice! I've marked this task as done:\
>\[T]\[X] CS2113T Homework 

<br />

### Deleting a task: `delete`

Deletes a specific task from the task list.

**Format**: `delete INDEX`
* Deletes the task at the specified INDEX.
* The index refers to the index number shown in the displayed task list.
* The index must be a positive integer.

**Example of usage:**
* `delete 2`
* Above command deletes the second task in the task list.
<br /> <br />
  
### Clearing all entries: `clear`

Deletes all the tasks in the task list.

**Format**: `clear`
<br /> <br />

### Listing all tasks: `list`

Prints out all the tasks in the task list.

**Format**: `list`

**Expected outcome:**
>Here are the tasks in your list:\
>1.\[T]\[ ] CS2113T Homework\
>2.\[D]\[ ] CS2113T IP (by: MAR 6 2021)

<br />

### Searching tasks by keyword: `find`

Prints out all the tasks with description that matches the keyword.

**Format**: `find INDEX`
* The search is case-sensitive

**Example of usage:**
* `find Homework`
* Above command finds all the tasks with 'Homework' in their description.
  
**Expected outcome:**
>Here are the matching tasks in your list:\
>1.\[T][ ] CS2113T Homework

<br />

### Exiting the program: `bye`

Prints out the terminating message and exits the program.

**Format**: `bye`
<br /> <br />

### Saving the data

The task list is automatically saved in `/data/task.txt` and is 
automatically loaded in subsequence usage.
<br /><br />

---

## Command Summary

Action | Format
------ | ---------------
Help | `help`
Add | `todo DESCRIPTION` <br /> `deadline DESCRIPTION /by DUE DATE` <br /> `event DESCRIPTION /at EVENT DATE`
Done | `done INDEX`
Delete | `delete INDEX`
Clear | `clear`
List | `list`
Find | `find KEYWORD`
Bye | `bye`