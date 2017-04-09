package guitests;

import org.junit.Test;

import guitests.guihandles.UpcomingTaskCardHandle;
import seedu.tasklist.testutil.DeadlineTaskBuilder;
import seedu.tasklist.testutil.EventTaskBuilder;
import seedu.tasklist.testutil.TestTask;

//@@author A0143355J
public class TodayTaskListTest extends TaskListGuiTest {

    @Test
    public void add_DeadlineTask_success() throws Exception {
        commandBox.runCommand("add Meeting with Tom d/Today c/Clementi");
        TestTask taskToAdd = new DeadlineTaskBuilder()
                .withName("Meeting with Tom")
                .withNaturalDeadline("Today")
                .withComment("Clementi")
                .build();
        assertTaskInsideTodayList(taskToAdd);
    }

    @Test
    public void add_EventTask_success() throws Exception {
        commandBox.runCommand("add Orientation camp d/today to next friday c/School");
        TestTask taskToAdd = new EventTaskBuilder()
                .withName("Orientation camp")
                .withNaturalStartDate("today")
                .withNaturalEndDate("next friday")
                .withComment("School")
                .build();
        assertTaskInsideTodayList(taskToAdd);
    }

    @Test
    public void edit_DeadlineTask_success() throws Exception {
        commandBox.runCommand("add Meet Jack for lunch d/Today");
        commandBox.runCommand("find Jack");
        commandBox.runCommand("edit 1 Meet Jack for dinner");
        TestTask taskToEdit = new DeadlineTaskBuilder()
                .withName("Meet Jack for dinner")
                .withNaturalDeadline("Today")
                .build();
        assertTaskInsideTodayList(taskToEdit);
    }

    @Test
    public void edit_EventTask_success() throws Exception {
        commandBox.runCommand("add Tour Europe d/Today to Next Sunday");
        commandBox.runCommand("find Europe");
        commandBox.runCommand("edit 1 Tour America d/Today to Next Friday");
        TestTask taskToEdit = new EventTaskBuilder()
                .withName("Tour America")
                .withNaturalStartDate("Today")
                .withNaturalEndDate("Next Friday")
                .build();
        assertTaskInsideTodayList(taskToEdit);
    }

    /**
     * Checks if the task is inside {@code todayListPanel}
     * @param taskToAdd is the task to be checked
     */
    private void assertTaskInsideTodayList(TestTask taskToAdd) {
        UpcomingTaskCardHandle addedCard = todayListPanel.navigateToTask(taskToAdd.getName().fullName);
        assertUpcomingTaskMatching(taskToAdd, addedCard);
    }
}
