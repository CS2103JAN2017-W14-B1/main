package seedu.tasklist.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.tasklist.model.task.DeadlineTask;
import seedu.tasklist.model.task.EventTask;
import seedu.tasklist.model.task.ReadOnlyDeadlineTask;
import seedu.tasklist.model.task.ReadOnlyEventTask;
import seedu.tasklist.model.task.ReadOnlyTask;

//@@author A0143355J
public class UpcomingTaskCard extends UiPart<Region> {

    private static final String FXML = "UpcomingTaskListCard.fxml";

    @FXML
    private HBox upcomingCardPane;
    @FXML
    private Label name;

    @FXML
    private Label firstDate;
    @FXML
    private Label secondDate;

    /**
     * Creates an {@code UpcomingTaskCard} Object and sets the values of the Labels
     * @param task is the {@code ReadOnlyTask} to be shown in the UpcomingTaskCard
     */
    public UpcomingTaskCard(ReadOnlyTask task) {
        super(FXML);
        setName(task);
        setDate(task);
    }

    /**
     * Sets the name for the {@code TaskCard}
     * @param task is the {@code ReadOnlyTask} to be displayed on this UpcomingTaskCard
     */
    private void setName(ReadOnlyTask task) {
        name.setText(task.getName().fullName);
        name.setStyle("-fx-font-size: 120%");
        name.setTranslateX(27);
    }

    /**
     * Sets the date(s) for the {@code TaskCard}
     * @param task is the {@code ReadOnlyTask} to be displayed on this UpcomingTaskCard
     */
    private void setDate(ReadOnlyTask task) {
        String taskType = task.getType();
        firstDate.setStyle("-fx-font-size: 120%");
        secondDate.setStyle("-fx-font-size: 120%");
        switch (taskType) {
        case DeadlineTask.TYPE:
            firstDate.setText("Deadline :");
            secondDate.setText(((ReadOnlyDeadlineTask) task).getDeadlineStringForUpcomingTask());
            break;
        case EventTask.TYPE:
            firstDate.setText(((ReadOnlyEventTask) task).getStartDateStringForUpcomingTask());
            secondDate.setText(((ReadOnlyEventTask) task).getEndDateStringForUpcomingTask());
            break;
        default:
            assert false;
        }
    }
}
