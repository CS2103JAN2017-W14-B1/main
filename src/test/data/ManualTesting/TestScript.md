# Test Script

### Overview
This is a document that explains the steps to perform manual testing for FlexiTask.<br>
Please note that the actual date and time displayed by FlexiTask will depend on the day the tests are performed.
<br>


## Before: Load SampleData
------
> **Instructions:**
 1. Create a folder and place the `FlexiTask.jar` executable in it.
 2. Access [SampleData.xml](https://raw.githubusercontent.com/CS2103JAN2017-W14-B1/main/master/src/test/data/ManualTesting/SampleData.xml) in FlexiTask Github repository. Copy the file contents to a text editor (e.g. Notepad) and save it in a file named `SampleData.xml`.
 3. Run `FlexiTask.jar`.
 4. Copy and paste `SampleData.xml` into the folder named data created by `FlexiTask.jar`.
 5. Enter `load data/SampleData.xml` in the command box.
 
 
---
## 1. Help Command
------
### 1.1 Open help window
> **Command:** `help` <br>
> **Result:**
- Result display panel posts message: <br>
`Opened help window.`
- Help window pops up and shows user guide.

## 2. Add Command
------
### 2.1.1 Add a floating task with tags, priority and comment
> **Command:** `add research project t/work p/high c/work with Tom`<br>
> **Result:** <br>
- Result display panel posts message: <br>
`New task added: research project Comment: work with Tom Priority: high Tags: [work]`<br>
- TaskList panel displays newly added task card at the bottom of the left task list panel.

### 2.1.2 Add a floating task without parameters
> **Command:** `add Exercise regularly` <br>
> **Result:** <br>
- Result display panel posts message: <br>
`New task added: Exercise regularly Comment: NIL Priority: NIL Tags:`<br>
- TaskList panel displays newly added task card at the bottom of the left task list panel.

### 2.1.3 Add a floating task using flexible command and prefix
> **Command:** `create Meeting report tag/work info/submit to boss urgency/high`<br>
- Result display panel posts message: <br>
`New task added: Meeting report Comment: submit to boss Priority: high Tags: [work]`<br>
- TaskList panel displays newly added task card at the bottom of the left task list panel.

### 2.2.1 Add a deadline task with tags and comment
> **Command:** `add project report t/assignment c/discuss with Tim d/7th Nov at 4pm`<br>
> **Result:**<br>
- Result display panel posts message:<br>
`New task added: project report Deadline: 07/11/2017 16:00 Comment: discuss with Tim Priority: NIL Tags: [assignment]`<br>
- TaskList panel displays newly added task card at the bottom of the left task list panel.

### 2.2.2 Add a deadline task with Natural Language
> **Command:** `add Submit presentation slides d/today c/discuss with Bob p/high`<br>
> **Result:**<br>
- Results display panel posts message:<br>
`New task added: Submit presentation slides Deadline: 14/04/2017 23:06 Comment: discuss with Bob Priority: high Tags:`<br>
- TaskList panel displays newly added task card at the bottom of the left task list panel<br>
- TodayList panel displays newly added task card in order sorted by time.

### 2.2.3 Add a deadline task with flexible command and prefix
> **Command:** `insert Email boss date/tomorrow c/about client request t/work`<br>
> **Result:**<br>
- Results display panel posts message:<br>
`New task added: Email boss Deadline: 09/04/2017 23:10 Comment: about client request Priority: NIL Tags: [work]`<br>
- TaskList panel displays newly added task card at the bottom of the left task list panel<br>
- The upcoming task panel displays newly added task card in "Tomorrow" sorted by time.

### 2.3.1 invalid command: wrong priority value
> **Command:** `add fetch son from school p/important`<br>
> **Result:**<br>
- Result display panel posts message:<br>
`Task priorities should only contain 'high', 'medium', or 'low', and it should not be blank`<br>
- Priorities can only contain 'high', 'medium' or 'low' if it is inputted

### 2.3.2 invalid command: more than two dates
> **Command:** `add fetch son from school d/today to tomorrow to day after tomorrow`<br>
> **Result:**<br>
- Result display panel posts message:<br>
`Please enter only one date for Deadlines and two dates for Events.`<br>
- Tasks can only have one date for Deadlines or two dates for Events.

### 2.3.3 invalid command: task already inside
> **Command:** `create Submit presentation slides d/today c/discuss with Bob p/high`<br>
> **Result:**<br>
- Result display panel posts message:<br>
`This task already exists in the task list`<br>
- Tasks that already exists cannot be added in again

### 2.4.1 Add an event with Natural Language
> **Command:** `add meeting with boss d/tomorrow at 4pm to tomorrow at 6pm c/take minutes p/medium`<br>
> **Result:**<br>
- Result display panel posts message:<br>
`New task added: meeting with boss StartDate: 09/04/2017 16:00 EndDate: 09/04/2017 18:00 Comment: take minutes Priority: medium Tags:`<br>
- TaskList panel displays newly added task card at the bottom of the left task list panel.<br>
- The upcoming task panel displays newly added task card in "Tomorrow" sorted by time.

### 2.4.2 Add an event with flexible command and prefix
> **Command:** `create Tom's birthday cruise d/tomorrow 9pm to day after tomorrow 11pm c/bring present`<br>
> **Result:**<br>
- Result display panel posts message:<br>
`New task added: Tom's birthday cruise StartDate: 09/04/2017 21:00 EndDate: 10/04/2017 23:00 Comment: bring present Priority: NIL Tags:`<br>
- TaskList panel displays newly added task at the bottom of the left task list panel.<br>
- The upcoming task panel displays newly added task card in "Tomorrow" sorted by time.




## 3. Find Command
------
### 3.1 Find by task name
> **Command:** `find dinner with wife`<br>
> **Result:**<br>
- Result display panel posts message:<br>
`1 tasks listed!`
- TaskList panel lists all events and tasks whose name contains dinner, with or wife.

### 3.2 Find by substring of task name
> **Command:** `find sub proj del`<br>
> **Result:**<br>
- Result display panel posts message:<br>
`1 tasks listed!`
- TaskList panel lists all events and tasks whose name contains sub, proj, or del as substrings.

### 3.3.1 Find by tag
> **Command:** `find t/project`<br>
> **Result:**<br>
- Result display panel posts message:<br>
`8 tasks listed!`
- TaskList Panel lists 8 tasks, which contains the tag `project` as one of its tags.

### 3.3.2 Find by multiple tags
> **Command:** `find t/work project`<br>
> **Result:**<br>
- Result display panel posts message:<br>
`2 tasks listed!`
- TaskList Panel lists 2 tasks, which contains BOTH work and project as tags.

### 3.3.3 Find by status
> **Command:** `find s/completed`<br>
> **Result:**<br>
- Result display panel posts message:<br>
`10 tasks listed!`
- TaskList panel lists all events and tasks whose status are `completed`, as indicated by a green tick on the right side of the task card.

### 3.4 Find using flexible commands
> **Command:** `LOCATE t/family`<br>
> **Result:**<br>
- Result display panel posts message:<br>
`9 tasks listed!`
- TaskList Panel lists all tasks which contains the tag `family` as one of its tags.



## 4. List Command
------
> **Command:** `list`<br>
> **Result:**<br>
- Result display panel posts message:<br>
`Listed all tasks`
- TaskList panel lists all tasks on the left panel.

<br>


## 5. Delete Command
------
### 5.1 Delete a task
> **Command:** `delete 1`<br>
> **Result:**<br>
- Result display panel posts message:<br>
`Deleted Task: Buy anniversary present Deadline: 07/04/2017 18:00 Comment: parents' wedding aniversary Priority: low Tags: [personal][family]`
- The deleted task will disappear from the TaskList panel.

### 5.2 Delete a task using flexible commands
> **Command:** `remove 3`<br>
> **Result:**<br>
- Result display panel posts message:<br>
`Deleted Task: Dinner with wife StartDate: 17/04/2017 17:00 EndDate: 17/04/2017 22:00 Comment: At Orchard Priority: medium Tags: [family]`
- The deleted task will disappear from the TaskList panel.

### 5.3 Delete a task after find command
> **Command:** `find boss`<br>
> **Result:**<br>
- Result display panel posts message:<br>
`2 tasks listed!`
<br>

> **Command:** `delete 1`<br>
> **Result:**<br>
- Result display panel posts message:<br>
`Deleted Task: Respond to Boss emails Deadline: 24/03/2017 15:00 Comment: work Priority: high Tags: [work]`

### 5.4.1 invalid commands: invalid task index
> **Command:** `delete 0`<br>
> **Result:**<br>
- Result display panel posts message:<br>
`Invalid command format! 
delete: Deletes the task identified by the index number used in the last task listing.
Parameters: INDEX (must be a positive integer)
Example: delete 1`
- The index specified must be valid (positive integer).

### 5.4.2 invalid commands: invalid task index
> **Command:** `delete 99`<br>
> **Result:**<br>
- Result display panel posts message:<br>
`The task index provided is invalid`
- The index specified must be valid (exists in the task list).

<br>


## 6. Done Command
------
### 6.1 mark a task as done
> **Command:** `list`<br>
> **Command:** `done 3`<br>
> **Result:**<br>
- Result display panel posts message:<br>
`Done Task: Buy groceries Comment: on the way home Priority: high Tags: [personal]`
- The task will be marked done, indicated by the green tick appearing on the right of its task card.

### 6.2.1 invalid commands: mark a task that is already done
> **Command:** `done 3`<br>
> **Result:**<br>
- Result display panel posts message:<br>
`Task is already marked as done!`

### 6.2.2 invalid commands: invalid task index
> **Command:** `done 0`<br>
> **Result:**<br>
- Result display panel posts message:<br>
`Invalid command format! 
done: Marks the task identified by the index number used in the last task listing as done.
Parameters: INDEX (must be a positive integer)
Example: done 1`
- The index specified must be valid (positive integer).

### 6.2.3 invalid commands: invalid task index
> **Command:** `done 100`<br>
> **Result:**<br>
- Result display panel posts message:<br>
`The task index provided is invalid`
- The index specified must be valid (exists in the task list).

<br>


## 7. Undone Command
------
### 7.1 mark a done task as undone
> **Command:** `list`<br>
> **Command:** `undone 2`<br>
> **Result:**<br>
- Result display panel posts message:<br>
`Undone Task: Apply for residence permit Deadline: 10/04/2017 23:59 Comment: check documents required Priority: medium Tags: [work]`
- The task will be marked undone, indicated by the green tick disappearing on the right of its task card.

### 7.2.1 invalid commands: mark a task that is already undone
> **Command:** `list`<br>
> **Command:** `undone 4`<br>
> **Result:**<br>
- Result display panel posts message:<br>
`Task has not been completed!` 

### 7.2.2 invalid commands: invalid task index
> **Command:** `list`<br>
> **Command:** `undone 0`<br>
> **Result:**<br>
- Result display panel posts message:<br>
`Invalid command format! 
undone: Marks the task identified by the index number used in the last task listing as undone.
Parameters: INDEX (must be a positive integer)
Example: undone 1`
- The index specified must be valid (positive integer).

### 7.2.3 invalid commands: invalid task index
> **Command:** `list`<br>
> **Command:** `undone 111`<br>
> **Result:**<br>
- Result display panel posts message:<br>
`The task index provided is invalid`
- The index specified must be valid (exists in the task list).
<br>


## 8. Edit Command
------
### 8.1 edit task name
> **Command:** `find Exercise`<br>
> **Command:** `edit 1 Exercise with sister`<br>
> **Result:**<br>
- Result display panel posts message:<br>
`Edited Task: Exercise with sister Comment: NIL Priority: NIL Tags:`
- Task card in left task list panel shows the updated task name.

### 8.2 edit tags
> **Command:** `find Email boss`<br>
> **Command:** `edit 1 t/work meeting`<br>
> **Result:**<br>
- Result display panel posts message:<br>
`Edited Task: Email boss Deadline: 09/04/2017 23:10 Comment: about client request Priority: NIL Tags: [work][meeting]`<br>
- Task card in left task list panel shows the updated tags.

### 8.3 edit comment
> **Command:** `find Meeting Report`<br>
> **Command:** `edit 1 c/submit to manager`<br>
> **Result:**<br>
- Result display panel posts message:<br>
`Edited Task: Meeting report Comment: submit to manager Priority: high Tags: [work]`<br>
- Task card in left task list panel shows the updated comment.

### 8.4 edit priority
> **Command:** `find project report`<br>
> **Command:** `edit 1 p/medium`<br>
> **Result:**<br>
- Result display panel posts message:<br>
`Edited Task: project report Deadline: 07/11/2017 16:00 Comment: discuss with Tim Priority: medium Tags: [assignment]`<br>
- Task card in left task list panel shows the updated priority.

### 8.5 edit deadline of deadline task
> **Command:** `find Submit presentation slides`<br>
> **Command:** `edit 1 d/tomorrow`<br>
> **Result:**<br>
- Result display panel posts message:<br>
`Edited Task: Submit presentation slides Deadline: 10/04/2017 00:06 Comment: discuss with Bob Priority: high Tags:`<br>
- Task card in left task list panel shows the updated deadline.

### 8.6 edit start and end date of event
> **Command:** `find birthday cruise`<br>
> **Command:** `edit 1 d/next Monday to next Tuesday`<br>
> **Result:**<br>
- Result display panel posts message:<br>
`Edited Task: Tom's birthday cruise StartDate: 17/04/2017 00:14 EndDate: 18/04/2017 00:14 Comment: bring present Priority: NIL Tags:`<br>
- Task card in left task list panel shows the updated start and end date.

### 8.7.1 invalid commands: edit a deadline for a floating task
> **Command:** `find Exercise`<br>
> **Command:** `edit 1 d/next Monday`<br>
> **Result:**<br>
- Result display panel posts message:<br>
`Dates cannot be edited in Floating Task.`<br>
- Floating tasks are not suppose to have dates.

### 8.7.2 invalid commands: edit start date and end date for a deadline
> **Command:** `find Email boss`<br>
> **Command:** `edit 1 d/today to tomorrow`<br>
> **Result:**<br>
- Result display panel posts message:<br>
`Only one date can be edited in Deadline Task.`<br>
- Deadline tasks only have a deadline and not start date and end date.

### 8.7.3 invalid commands: invalid task index
> **Command:** `edit 100 finish sales report`<br>
> **Result:**<br>
- Result display panel posts message:<br>
`The task index provided is invalid`<br>
- The index specified must be valid (exists in the task list).

### 8.7.4 invalid commands: no fields to edit
> **Command:** `edit 5`<br>
> **Result:**<br>
- Result displayy panel posts message:<br>
`At least one field to edit must be provided.`<br>
- Fields must be entered when editing.

## 9. Clear Command
------
### 9.1 Clear tasks according to tag
> **Command:** `list`<br>
> **Command:** `clear t/work`<br>
> **Result:**<br>
- Result display panel posts message:<br>
`Task list has been cleared!`<br>
- Clears all tasks that contain `work` as a tag.

### 9.1 Clear tasks according to status
> **Command:** `clear s/completed` <br>
> **Result:**<br>
- Result display panel posts message:<br>
`Task list has been cleared!`<br>
- Clears all tasks that have been marked done (have green tick(s)).

### 9.2 Clear all tasks
> **Command:** `clear` <br>
> **Result:**<br>
- Result display panel posts message:<br>
`Task list has been cleared!`<br>
- Clears all tasks from FlexiTask.
<br>

## 10. Undo Command
------
### 10.1 Undo from the previous clear command
> **Command:** `undo`<br>
> **Result:**<br>
- Result display panel posts message:<br>
`Task list has been cleared!`<br>

> **Command:** `undo`<br>
> **Result:**<br>
- Result display panel posts message:<br>
`Reverted the most recent action: clear s/completed`<br>

> **Command:** `undo`<br>
> **Result:**<br>
- Result display panel posts message:<br>
`Reverted the most recent action: clear tag/work`

### 10.2 Undo a command that mutates 
> **Command:** `add refill water bottle t/essentials`<br>
> **Result:**<br>
- Result display panel posts message:<br>
`New task added: refill water bottle Comment: NIL Priority: NIL Tags: [essentials]`
> **Command:** `undo`<br>
> **Result:**<br>
- Result display panel posts message:<br>
`Reverted the most recent action: add refill water bottle t/essentials`
- The effect of the add command is canceled, the task list reverts back to the state before the add command.



## 11. Redo Command
------
### 11.1 Redo a command after an undo
> **Command:** `add refill water bottle t/essentials`<br>
> **Command:** `undo`<br>
> **Command:** `redo`<br>
> **Result:**<br>
- Result display panel posts message:<br>
`Redid the most recent action that is undone: add refill water bottle t/essentials`
- The effect of the preceding undo command is canceled, the task list reverts back to the state before the undo command.

### 11.2.1 invalid command: more redo than undo
> **Command:** `redo`<br>
- Result display panel posts message:<br>
`No command to redo.`
- The user can only perform redo as much as the number of undo commands before a command that mutates the data.

### 11.2.2 invalid command: no undo before redo
> **Command:** `add refill water bottle again t/essentials`<br>
> **Command:** `redo`<br>
- Result display panel posts message:<br>
`No command to redo.`


## 12. Sort Command
------
### 12.1 Sort by name 
> **Command:** `sort n` <br>
> **Result:**<br>
- Result display panel posts message:<br>
`Task sorted according to given parameter.`
- TaskList panel displays task cards sorted by lexicographical order at the left task list panel.


### 12.2 Sort by date
> **Command:** `sort d` <br>
> **Result:**<br>
- Result display panel posts message:<br>
`Task sorted according to given parameter.`
- TaskList panel displays task cards sorted by date starting from the earliest at the left task list panel.


### 12.3 Sort by priority 
> **Command:** `sort p` <br>
> **Result:**<br>
- Result display panel posts message:<br>
`Task sorted according to given parameter.`
- TaskList panel displays task cards sorted by priority starting from tasks ranked with highest priority 
followed by medium and then low at the left task list panel.

### 12.4 invalid commands: sort by invalid parameter 
> **Command:** `sort q` <br>
> **Result:**<br>
- Result display panel posts message:<br>
`Invalid sorting parameter used!
sort: Sorts task according to the parameter specified. Only 1 of the 3. Name:n or Priority:p or Date:d
Parameters: n, p, d
Example: sort n`


## 13. Save Command
------
### 13.1 Save to existing folder 
> **Command:** `save data/myTaskList.xml` <br>
> **Result:**<br>
- Result display panel posts message:<br>
`File is successfully saved to: data/myTaskList.xml`<br>

### 13.2 Save to directory that doesn't exist 
> **Command:** `save data/Project/myTaskList.xml` <br>
> **Result:**<br>
- Result display panel posts message:<br>
`File is successfully saved to: data/Project/myTaskList.xml`

### 13.3 Save to invalid path 
> **Command:** `save data/myTaskList.png` <br>
> **Result:**<br>
- Result display panel posts message:<br>
`Invalid file path: data/myTaskList.png`


## 14. Load Command
------
### 14.1 Load from original given tasklist 
> **Command:** `load data/tasklist.xml` <br>
> **Result:**<br>
- Result display panel posts message:<br>
`Data successfully loaded from: data/tasklist.xml`

### 14.2 Load from file that doesn't exits 
> **Command:** `load data/file.xml` <br>
> **Result:**<br>
- Result display panel posts message:<br>
`The file cannot be loaded: data/file.xml`



