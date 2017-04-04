package seedu.tasklist.ui;

import java.util.logging.Logger;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import seedu.tasklist.commons.core.LogsCenter;
import seedu.tasklist.commons.util.FxViewUtil;
import seedu.tasklist.model.task.ReadOnlyTask;

//@@author A0143355J
public class UpcomingTaskPanel extends UiPart<Region> {
    private final Logger logger = LogsCenter.getLogger(UpcomingTaskPanel.class);
    private static final String FXML = "UpcomingTaskPanel.fxml";

    @FXML
    private ListView<ReadOnlyTask> todayTaskListView;
    @FXML
    private ListView<ReadOnlyTask> tomorrowTaskListView;

    public UpcomingTaskPanel(AnchorPane upcomingTaskPlaceholder, ObservableList<ReadOnlyTask> todayTask,
            ObservableList<ReadOnlyTask> tomorrowTask) {
        super(FXML);
        setTodayListView(todayTask);
        setTomorrowListView(tomorrowTask);
        addToPlaceholder(upcomingTaskPlaceholder);
    }

    private void setTodayListView(ObservableList<ReadOnlyTask> todayList) {
        todayTaskListView.setItems(todayList);
        todayTaskListView.setCellFactory(listView -> new UpcomingTaskViewCell());
    }

    private void setTomorrowListView(ObservableList<ReadOnlyTask> tomorrowList) {
        tomorrowTaskListView.setItems(tomorrowList);
        tomorrowTaskListView.setCellFactory(listView -> new UpcomingTaskViewCell());
    }

    private void addToPlaceholder(AnchorPane placeHolderPane) {
        SplitPane.setResizableWithParent(placeHolderPane, false);
        FxViewUtil.applyAnchorBoundaryParameters(getRoot(), 0.0, 0.0, 0.0, 0.0);
        placeHolderPane.getChildren().add(getRoot());
    }

    public void todayScrollTo(int index) {
        Platform.runLater(() -> {
            todayTaskListView.scrollTo(index);
        });
    }

    public void tomorrowScrollTo(int index) {
        Platform.runLater(() -> {
            tomorrowTaskListView.scrollTo(index);
        });
    }

    class UpcomingTaskViewCell extends ListCell<ReadOnlyTask> {

        @Override
        protected void updateItem(ReadOnlyTask task, boolean empty) {
            super.updateItem(task, empty);

            if (empty || task == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new UpcomingTaskCard(task).getRoot());
            }
        }
    }
}
