package guitests.guihandles;

import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import guitests.GuiRobot;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import seedu.tasklist.TestApp;
import seedu.tasklist.model.task.DeadlineTask;
import seedu.tasklist.model.task.EventTask;
import seedu.tasklist.model.task.ReadOnlyDeadlineTask;
import seedu.tasklist.model.task.ReadOnlyEventTask;
import seedu.tasklist.model.task.ReadOnlyTask;
import seedu.tasklist.testutil.TestUtil;

public class TomorrowListPanelHandle extends GuiHandle {

    public static final int NOT_FOUND = -1;
    public static final String CARD_PANE_ID = "#upcomingCardPane";

    private static final String TOMORROW_LIST_VIEW_ID = "#tomorrowTaskListView";

    public TomorrowListPanelHandle(GuiRobot guiRobot, Stage primaryStage) {
        super(guiRobot, primaryStage, TestApp.APP_TITLE);
    }

    /*
     * Returns a ListView<ReadOnlyTask> of tasks inside TomorrowListPanel
     */
    public ListView<ReadOnlyTask> getTomorrowListView() {
        return getNode(TOMORROW_LIST_VIEW_ID);
    }

    public boolean isTomorrowListMatching(ReadOnlyTask... tasks) {
        return this.isTomorrowListMatching(0, tasks);
    }

    public boolean isTomorrowListMatching(int startPosition, ReadOnlyTask... tasks) throws IllegalArgumentException {
        if (tasks.length + startPosition != getTomorrowListView().getItems().size()) {
            throw new IllegalArgumentException("List size mismatched\n" +
                    "Expected " + (getTomorrowListView().getItems().size() - 1) + " tasks");
        }
        assertTrue(this.containsInOrder(startPosition, tasks));
        for (int i = 0; i < tasks.length; i++) {
            final int scrollTo = i + startPosition;
            guiRobot.interact(() -> getTomorrowListView().scrollTo(scrollTo));
            guiRobot.sleep(200);
            if (!TestUtil.compareUpcomingCardAndTask(getUpcomingTaskCardHandle(startPosition + i), tasks[i])) {
                return false;
            }
        }
        return true;
    }

    public boolean containsInOrder(int startPosition, ReadOnlyTask... tasks) {
        List<ReadOnlyTask> taskList = getTomorrowListView().getItems();

     // Return false if the list in panel is too short to contain the given list
        if (startPosition + tasks.length > taskList.size()) {
            return false;
        }

        // Return false if any of the tasks doesn't match
        for (int i = 0; i < tasks.length; i++) {
            if (!taskList.get(startPosition + i).getName().fullName.equals(tasks[i].getName().fullName)) {
                return false;
            }
        }

        return true;
    }

    public UpcomingTaskCardHandle navigateToTask(String name) {
        guiRobot.sleep(500); //Allow a bit of time for the list to be updated
        final Optional<ReadOnlyTask> task = getTomorrowListView().getItems().stream()
                                                    .filter(p -> p.getName().fullName.equals(name))
                                                    .findAny();
        if (!task.isPresent()) {
            throw new IllegalStateException("Name not found: " + name);
        }

        return navigateToTask(task.get());
    }

    /**
     * Navigates the listview to display and select the task.
     */
    public UpcomingTaskCardHandle navigateToTask(ReadOnlyTask task) {
        int index = getTaskIndex(task);

        guiRobot.interact(() -> {
            getTomorrowListView().scrollTo(index);
            guiRobot.sleep(150);
            getTomorrowListView().getSelectionModel().select(index);
        });
        guiRobot.sleep(100);

        return getUpcomingTaskCardHandle(task);
    }

    /**
     * Returns the position of the task given, {@code NOT_FOUND} if not found in the list.
     */
    public int getTaskIndex(ReadOnlyTask targetTask) {
        List<ReadOnlyTask> tasksInList = getTomorrowListView().getItems();
        for (int i = 0; i < tasksInList.size(); i++) {
            if (tasksInList.get(i).getName().equals(targetTask.getName())) {
                return i;
            }
        }
        return NOT_FOUND;
    }

    /**
     * Gets a task from the list by index
     */
    public ReadOnlyTask getTask(int index) {
        return getTomorrowListView().getItems().get(index);
    }

    public UpcomingTaskCardHandle getUpcomingTaskCardHandle(int index) {
        ReadOnlyTask current = getTomorrowListView().getItems().get(index);
        String type = current.getType();
        switch (type) {
        case DeadlineTask.TYPE:
            return getUpcomingTaskCardHandle(new DeadlineTask((ReadOnlyDeadlineTask) current));
        case EventTask.TYPE:
            return getUpcomingTaskCardHandle(new EventTask((ReadOnlyEventTask) current));
        default:
            return null;
        }
    }

    public UpcomingTaskCardHandle getUpcomingTaskCardHandle(ReadOnlyTask task) {
        Set<Node> nodes = getAllCardNodes();
        Optional<Node> taskCardNode = nodes.stream()
                .filter(n -> new UpcomingTaskCardHandle(guiRobot, primaryStage, n).isSameTask(task))
                .findFirst();

        if (taskCardNode.isPresent()) {
            return new UpcomingTaskCardHandle(guiRobot, primaryStage, taskCardNode.get());
        } else {
            return null;
        }
    }

    protected Set<Node> getAllCardNodes() {
        return guiRobot.lookup(CARD_PANE_ID).queryAll();
    }
}
