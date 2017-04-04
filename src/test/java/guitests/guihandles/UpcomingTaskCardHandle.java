package guitests.guihandles;

import guitests.GuiRobot;
import javafx.scene.Node;
import javafx.stage.Stage;
import seedu.tasklist.model.task.DeadlineTask;
import seedu.tasklist.model.task.EventTask;
import seedu.tasklist.model.task.FloatingTask;
import seedu.tasklist.model.task.ReadOnlyDeadlineTask;
import seedu.tasklist.model.task.ReadOnlyEventTask;
import seedu.tasklist.model.task.ReadOnlyTask;

//@@author A0143355J
public class UpcomingTaskCardHandle extends GuiHandle {
    private static final String NAME_FIELD_ID = "#name";
    private static final String DATE_ONE_FIELD_ID = "#firstDate";
    private static final String DATE_TWO_FIELD_ID = "#secondDate";

    private Node node;

    protected UpcomingTaskCardHandle(GuiRobot guiRobot, Stage primaryStage, Node node) {
        super(guiRobot, primaryStage, null);
        this.node = node;
    }

    /*
     * Returns the text inside the label
     */
    protected String getTextFromLabel(String fieldId) {
        return getTextFromLabel(fieldId, node);
    }

    /*
     * Returns Task Name inside the UpcomingTaskCard
     */
    protected String getTaskName() {
        return getTextFromLabel(NAME_FIELD_ID);
    }

    /*
     * Returns Deadline date from UpcomingTaskCard
     */
    protected String getDeadline() {
        return getTextFromLabel(DATE_TWO_FIELD_ID);
    }

    /*
     * Returns Start Date from UpcomingTaskCard
     */
    protected String getStartDate() {
        return getTextFromLabel(DATE_ONE_FIELD_ID);
    }

    /*
     * Returns End Date from UpcomingTaskCard
     */
    protected String getEndDate() {
        return getTextFromLabel(DATE_TWO_FIELD_ID);
    }

    /*
     * Returns true if task is the same as the task inside UpcomingTaskCard
     */
    public boolean isSameTask(ReadOnlyTask task) {
        String taskType = task.getType();
        switch (taskType) {
        case FloatingTask.TYPE:
            return isSameName(task);
        case DeadlineTask.TYPE:
            System.out.println(isSameDeadline(task));
            return isSameName(task) && isSameDeadline(task);
        case EventTask.TYPE:
            return isSameName(task) && isSameStartDate(task) && isSameEndDate(task);
        default:
            return false;
        }
    }

    /*
     * Returns true if task has the same name as task inside UpcomingTaskCard
     */
    private boolean isSameName(ReadOnlyTask task) {
        return getTaskName().equals(task.getName().fullName);
    }

    /*
     * Returns true if task has the same deadline as task inside UpcomingTaskCard
     */
    private boolean isSameDeadline(ReadOnlyTask task) {
        System.out.println(getDeadline());
        System.out.println(((ReadOnlyDeadlineTask) task).getDeadlineStringForUpcomingTask());
        return getDeadline().equals(((ReadOnlyDeadlineTask) task).getDeadlineStringForUpcomingTask());
    }

    /*
     * Returns true if task has the same Start Date as task inside UpcomingTaskCard
     */
    private boolean isSameStartDate(ReadOnlyTask task) {
        return getStartDate().equals(((ReadOnlyEventTask) task).getStartDateStringForUpcomingTask());
    }

    /*
     * Returns true if task has the same End Date as task inside UpcomingTaskCard
     */
    private boolean isSameEndDate(ReadOnlyTask task) {
        return getEndDate().equals(((ReadOnlyEventTask) task).getEndDateStringForUpcomingTask());
    }
}
