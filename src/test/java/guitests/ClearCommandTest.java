package guitests;

import static org.junit.Assert.assertTrue;
import static seedu.tasklist.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import org.junit.Test;

import seedu.tasklist.logic.commands.ClearCommand;

import seedu.tasklist.model.task.Status;
import seedu.tasklist.testutil.TestTask;
import seedu.tasklist.testutil.TestUtil;


public class ClearCommandTest extends TaskListGuiTest {

    @Test
    public void clear() {

        //verify a non-empty list can be cleared
        assertTrue(taskListPanel.isListMatching(td.getTypicalTasks()));
        assertClearCommandSuccess();

        //verify other commands can work after a clear command
        commandBox.runCommand(td.internship.getAddCommand());
        assertTrue(taskListPanel.isListMatching(td.internship));
        commandBox.runCommand("delete 1");
        assertListSize(0);

        //verify clear command works when the list is empty
        assertClearCommandSuccess();
    }

    //@@author A0139747N
    @Test
    public void clearWithFlexibleCommand() {
        commandBox.runCommand("CLEan");
        assertListSize(0);
        assertResultMessage(ClearCommand.MESSAGE_SUCCESS);

    }


    public void clear_with_flexible_command() {
        commandBox.runCommand("clean");
        assertListSize(0);
        assertResultMessage(ClearCommand.MESSAGE_SUCCESS);
    }
    //tests for clear by tag.
    @Test
    public void clear_by_tag_success() {
        TestTask[] sampleTasks = td.getTypicalTasks();
        commandBox.runCommand("clear t/2103");
        sampleTasks = TestUtil.removeTasksFromList(sampleTasks, td.java, td.tutorial);
        assertTrue(taskListPanel.isListMatching(sampleTasks));
        assertListSize(4);
    }

    @Test
    public void clear_by_non_existent_tag() {
        commandBox.runCommand("clear t/tagThatDoesNotExist");
        assertTrue(taskListPanel.isListMatching(td.getTypicalTasks()));
    }

    @Test
    public void clear_by_multiple_tags() {
        commandBox.runCommand("clear t/2103 2104");
        assertResultMessage(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ClearCommand.MESSAGE_USAGE));
    }

    //tests for clear by status
    @Test
    public void clear_by_completed_success() {
        TestTask[] sampleTasks = td.getTypicalTasks();
        commandBox.runCommand("done 1");
        commandBox.runCommand("done 3");
        commandBox.runCommand("clear s/completed");
        sampleTasks = TestUtil.removeTasksFromList(sampleTasks, td.groceries, td.tutorial);
        assertTrue(taskListPanel.isListMatching(sampleTasks));
        assertListSize(4);
    }

    @Test
    public void clear_by_uncompleted_success() {
        TestTask[] sampleTasks = td.getTypicalTasks();
        commandBox.runCommand("done 1");
        sampleTasks[0].setStatus(new Status(true));
        commandBox.runCommand("done 2");
        sampleTasks[1].setStatus(new Status(true));
        commandBox.runCommand("clear s/uncompleted");
        sampleTasks = TestUtil.removeTasksFromList(sampleTasks, td.groceries, td.java, td.project, td.drink);
        assertTrue(taskListPanel.isListMatching(sampleTasks));
        assertListSize(2);
    }

    @Test
    public void clear_by_multiple_status() {
        commandBox.runCommand("clear s/completed uncompleted");
        assertResultMessage(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ClearCommand.MESSAGE_USAGE));

    }

    //@@author
    private void assertClearCommandSuccess() {
        commandBox.runCommand("clear");
        assertListSize(0);
        assertResultMessage(ClearCommand.MESSAGE_SUCCESS);
    }
}
