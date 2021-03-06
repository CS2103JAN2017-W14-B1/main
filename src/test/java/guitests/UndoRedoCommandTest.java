package guitests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import seedu.tasklist.commons.exceptions.IllegalValueException;
import seedu.tasklist.logic.commands.RedoCommand;
import seedu.tasklist.logic.commands.UndoCommand;
import seedu.tasklist.model.task.Name;
import seedu.tasklist.testutil.TestTask;
import seedu.tasklist.testutil.TestUtil;


//@@author A0139747N
public class UndoRedoCommandTest extends TaskListGuiTest {

    @Test
    public void assert_undo_redo_add_success() {
        TestTask taskToBeAdded = td.internship;
        commandBox.runCommand(taskToBeAdded.getAddCommand());
        commandBox.runCommand("undo");
        TestTask[] expectedTaskList = td.getTypicalTasks();
        assertTrue(taskListPanel.isListMatching(expectedTaskList));
        assertResultMessage(String.format(UndoCommand.MESSAGE_UNDO_SUCCESS + taskToBeAdded.getAddCommand()));

        //redo
        commandBox.runCommand("redo");
        TestTask[] currentList = TestUtil.addTasksToList(td.getTypicalTasks(), td.internship);
        assertTrue(taskListPanel.isListMatching(currentList));
        assertResultMessage(String.format(RedoCommand.MESSAGE_REDO_SUCCESS + taskToBeAdded.getAddCommand()));
    }

    /**
     * Test to edit the name. Beyond MVP can edit all other parameters for more coverage.
     * @throws IllegalValueException
     */

    @Test
    public void assert_undo_redo_edit_success() throws IllegalValueException {
        commandBox.runCommand("edit 1 Evalution Report");
        commandBox.runCommand("undo");
        TestTask[] expectedTaskList = td.getTypicalTasks();
        assertTrue(taskListPanel.isListMatching(expectedTaskList));
        assertResultMessage(String.format(UndoCommand.MESSAGE_UNDO_SUCCESS + "edit 1 Evalution Report"));

        //redo
        commandBox.runCommand("redo");
        expectedTaskList[0].setName(new Name("Evalution Report"));
        assertTrue(taskListPanel.isListMatching(expectedTaskList));
        assertResultMessage(String.format(RedoCommand.MESSAGE_REDO_SUCCESS + "edit 1 Evalution Report"));
    }

    @Test
    public void assert_undo_redo_delete_success() {
        TestTask[] currentList = TestUtil.removeTaskFromList(td.getTypicalTasks(), 6);
        commandBox.runCommand("delete 6");
        commandBox.runCommand("undo");
        TestTask[] expectedTaskList = td.getTypicalTasks();
        assertTrue(taskListPanel.isListMatching(expectedTaskList));
        assertResultMessage(String.format(UndoCommand.MESSAGE_UNDO_SUCCESS + "delete 6"));

        //redo
        commandBox.runCommand("redo");
        currentList = TestUtil.removeTaskFromList(td.getTypicalTasks(), 6);
        assertTrue(taskListPanel.isListMatching(currentList));
        assertResultMessage(String.format(RedoCommand.MESSAGE_REDO_SUCCESS + "delete 6"));

    }

    /**
     * Test for events where multiple undo/redo commands are entered, and go over the stack limit.
     */
    @Test
    public void assert_undo_redo_fail() {
        TestTask taskToBeAdded = td.internship;
        commandBox.runCommand(taskToBeAdded.getAddCommand());
        commandBox.runCommand("undo");
        assertResultMessage(UndoCommand.MESSAGE_UNDO_SUCCESS + taskToBeAdded.getAddCommand());
        commandBox.runCommand("undo");
        assertResultMessage(UndoCommand.MESSAGE_UNDO_FAILED);

        commandBox.runCommand("redo");
        assertResultMessage(String.format(RedoCommand.MESSAGE_REDO_SUCCESS + taskToBeAdded.getAddCommand()));
        commandBox.runCommand("redo");
        assertResultMessage(String.format(RedoCommand.MESSAGE_REDO_FAILED));

    }

    @Test
    public void assert_undo_redo_clear_success() {
        commandBox.runCommand("clear");
        commandBox.runCommand("undo");
        assertResultMessage(UndoCommand.MESSAGE_UNDO_SUCCESS + "clear");
        TestTask[] expectedTaskList = td.getTypicalTasks();
        assertTrue(taskListPanel.isListMatching(expectedTaskList));

        commandBox.runCommand("redo");
        assertResultMessage(RedoCommand.MESSAGE_REDO_SUCCESS + "clear");
        assertTrue(taskListPanel.isListMatching());
    }

    @Test
    public void undoRedoWithFlexibleCommands() {
        TestTask taskToBeAdded = td.internship;
        commandBox.runCommand(taskToBeAdded.getAddCommand());
        commandBox.runCommand("UNDO");
        assertResultMessage(UndoCommand.MESSAGE_UNDO_SUCCESS + taskToBeAdded.getAddCommand());

        commandBox.runCommand("REDO");
        assertResultMessage(String.format(RedoCommand.MESSAGE_REDO_SUCCESS + taskToBeAdded.getAddCommand()));

    }

    /**
     * A "illegal redo" is the redo command that is not preceded by an "undo" command.
     */
    @Test
    public void assert_illegal_redo_fail() {
        TestTask taskToBeAdded = td.internship;
        commandBox.runCommand(taskToBeAdded.getAddCommand());
        commandBox.runCommand("undo");
        TestTask anotherTaskToBeAdded = td.exam;
        commandBox.runCommand(anotherTaskToBeAdded.getAddCommand());
        commandBox.runCommand("redo");
        assertResultMessage(RedoCommand.MESSAGE_REDO_FAILED);

    }
}


