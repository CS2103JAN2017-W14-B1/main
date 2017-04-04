package guitests;

import org.junit.Test;

import guitests.guihandles.UpcomingTaskCardHandle;
import seedu.tasklist.testutil.DeadlineTaskBuilder;
import seedu.tasklist.testutil.TestTask;

//@@author A0143355J
public class TodayTaskListTest extends TaskListGuiTest {

    @Test
    public void addDeadlineTask_Success() throws Exception {
        commandBox.runCommand("add Meeting with Tom d/Today c/Clementi");
        TestTask taskToAdd = new DeadlineTaskBuilder()
                .withName("Meeting with Tom")
                .withNaturalDeadline("Today")
                .withComment("Clementi")
                .build();
        assertTaskInsideTodayList(taskToAdd);
    }

    private void assertTaskInsideTodayList(TestTask taskToAdd) {
        UpcomingTaskCardHandle addedCard = todayListPanel.navigateToTask(taskToAdd.getName().fullName);
        assertUpcomingTaskMatching(taskToAdd, addedCard);
    }
}
