package guitests;

import static org.junit.Assert.assertTrue;

import java.text.ParseException;

import org.junit.Test;

import guitests.guihandles.TaskCardHandle;
import seedu.tasklist.commons.core.Messages;
import seedu.tasklist.commons.exceptions.IllegalValueException;
import seedu.tasklist.logic.commands.SortCommand;
import seedu.tasklist.testutil.DeadlineTaskBuilder;
import seedu.tasklist.testutil.EventTaskBuilder;
import seedu.tasklist.testutil.FloatingTaskBuilder;
import seedu.tasklist.testutil.TestTask;
//@@author A0141993X
public class SortCommandTest extends TaskListGuiTest {


    @Test
    public void sort_invalidParameter_errorMessageShown() {
        String command = "sort a";
        commandBox.runCommand(command);
        assertResultMessage(String.format(SortCommand.MESSAGE_FAILURE));
    }

    @Test
    public void sort_moreThanOneParameter_errorMessageShown() {
        String command = "sort n p";
        commandBox.runCommand(command);
        assertResultMessage(String.format(SortCommand.MESSAGE_FAILURE));
    }

    @Test
    public void sort_invalidCommand_errorMessageShown() {
        commandBox.runCommand("sorts newTask");
        assertResultMessage(Messages.MESSAGE_UNKNOWN_COMMAND);
    }

    @Test
    public void sort() {
        //sort by name
        TestTask[] expectedListName = {td.groceries, td.tutorial, td.homework, td.drink, td.project, td.java};
        String commandName = "sort n";
        commandBox.runCommand(commandName);
        assertSortSuccess(expectedListName);

        //sort by date
        TestTask[] expectedListDate = {td.tutorial, td.project, td.groceries, td.homework, td.drink, td.java };
        String commandDate = "sort d";
        commandBox.runCommand(commandDate);
        assertResultMessage(SortCommand.MESSAGE_SUCCESS);
        assertSortSuccess(expectedListDate);

        //sort by start priority
        TestTask[] expectedListPriority = {td.tutorial, td.project, td.drink, td.java, td.groceries, td.homework};
        String commandPriority = "sort p";
        commandBox.runCommand(commandPriority);
        assertResultMessage(SortCommand.MESSAGE_SUCCESS);
        assertSortSuccess(expectedListPriority);

        //sort by date after adding floating task
        try {
            TestTask floatingTaskToAdd = new FloatingTaskBuilder().withName("Shop clothes")
                    .withTags("shopping").withComment("save money")
                    .withPriority("low").withStatus(false).build();
            TestTask[] expectedListAddFloating = {td.tutorial, td.project, td.groceries,
                                                  td.homework, td.drink, td.java, floatingTaskToAdd};
            commandBox.runCommand("add Shop clothes t/shopping c/save money p/low");
            commandBox.runCommand("sort d");
            assertResultMessage(SortCommand.MESSAGE_SUCCESS);
            assertSortSuccessAdd(floatingTaskToAdd, expectedListAddFloating);

        } catch (IllegalValueException e) {
            e.printStackTrace();
        }

        //sort by date after adding deadline task
        try {
            TestTask deadlineTaskToAdd = new DeadlineTaskBuilder().withName("Project Report Submission")
                    .withTags("work").withComment("discuss with teammates")
                    .withDeadline("15 April 6pm").withPriority("high").withStatus(false).build();
            TestTask[] expectedListAddDeadline = {td.tutorial, td.project, deadlineTaskToAdd, td.groceries,
                                                  td.homework, td.drink, td.java};
            commandBox.runCommand("add Project Report Submission d/15 April 6pm "
                                   + "t/work c/discuss with teammates p/high");
            commandBox.runCommand("sort d");
            assertResultMessage(SortCommand.MESSAGE_SUCCESS);
            assertSortSuccessAdd(deadlineTaskToAdd, expectedListAddDeadline);
        } catch (IllegalValueException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //sort by date after adding event task
        try {
            TestTask eventTaskToAdd = new EventTaskBuilder().withName("Project Meeting")
                    .withTags("work").withComment("discuss with teammates")
                    .withStartDate("12 April 4pm").withEndDate("12 April 6pm")
                    .withPriority("high").withStatus(false).build();
            TestTask[] expectedListAddEvent = {td.tutorial, td.project, eventTaskToAdd,
                                               td.groceries, td.homework, td.drink, td.java};
            commandBox.runCommand("add Project Meeting d/12 April 4pm to 6pm "
                    + "t/work c/discuss with teammates p/high");
            commandBox.runCommand("sort d");
            assertResultMessage(SortCommand.MESSAGE_SUCCESS);
            assertSortSuccessAdd(eventTaskToAdd, expectedListAddEvent);
        } catch (IllegalValueException ive) {
            ive.printStackTrace();
        } catch (ParseException pe) {
            pe.printStackTrace();
        }
    }

    private void assertSortSuccess(TestTask... expectedList) {
        //confirm list contains all tasks in the correct sorted order
        assertTrue(taskListPanel.isListMatching(expectedList));
    }

    private void assertSortSuccessAdd(TestTask taskToAdd, TestTask... expectedList) {
      //confirm the new card contains the right data
        TaskCardHandle addedCard = taskListPanel.navigateToTask(taskToAdd.getName().fullName);
        assertMatching(taskToAdd, addedCard);

        assertTrue(taskListPanel.isListMatching(expectedList));
    }

}
