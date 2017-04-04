package guitests;

import org.junit.Test;

import guitests.guihandles.UpcomingTaskCardHandle;
import seedu.tasklist.testutil.DeadlineTaskBuilder;
import seedu.tasklist.testutil.EventTaskBuilder;
import seedu.tasklist.testutil.TestTask;

//@@author A0143355J
public class TomorrowTaskListTest extends TaskListGuiTest {

    @Test
    public void addDeadlineTask_Success() throws Exception {
        commandBox.runCommand("add Finish up report d/tomorrow");
        TestTask taskToAdd = new DeadlineTaskBuilder()
                .withName("Finish up report")
                .withNaturalDeadline("tomorrow")
                .build();
        assertTaskInsideTomorrowList(taskToAdd);
    }

    @Test
    public void addEventTask_Success() throws Exception {
        commandBox.runCommand("add Visit Cousin d/tomorrow to next friday");
        TestTask taskToAdd = new EventTaskBuilder()
                .withName("Visit Cousin")
                .withNaturalStartDate("tomorrow")
                .withNaturalEndDate("next friday")
                .build();
        assertTaskInsideTomorrowList(taskToAdd);
    }

    @Test
    public void editDeadlineTask_Success() throws Exception {
        commandBox.runCommand("add Submit survey d/tomorrow");
        commandBox.runCommand("find survey");
        commandBox.runCommand("edit 1 d/tomorrow 5pm");
        TestTask taskToEdit = new DeadlineTaskBuilder()
                .withName("Submit survey")
                .withNaturalDeadline("tomorrow 5pm")
                .build();
        assertTaskInsideTomorrowList(taskToEdit);
    }

    @Test
    public void editEventTask_Success() throws Exception {
        commandBox.runCommand("add attend conference overseas d/tomorrow to next monday");
        commandBox.runCommand("find conference");
        commandBox.runCommand("edit 1 attend conference in Malaysia d/tomorrow to next tuesday");
        TestTask taskToEdit = new EventTaskBuilder()
                .withName("attend conference in Malaysia")
                .withNaturalStartDate("tomorrow")
                .withNaturalEndDate("next tuesday")
                .build();
        assertTaskInsideTomorrowList(taskToEdit);
    }

    private void assertTaskInsideTomorrowList(TestTask taskToAdd) {
        UpcomingTaskCardHandle taskCard = tomorrowListPanel.navigateToTask(taskToAdd.getName().fullName);
        assertUpcomingTaskMatching(taskToAdd, taskCard);
    }
}
