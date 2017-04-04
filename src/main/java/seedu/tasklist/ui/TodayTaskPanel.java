package seedu.tasklist.ui;

import java.util.logging.Logger;

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

public class TodayTaskPanel extends UiPart<Region> {
    private final Logger logger = LogsCenter.getLogger(TodayTaskPanel.class);
    private static final String FXML = "TodayTaskPanel.fxml";

    @FXML
    private ListView<ReadOnlyTask> todayTaskListView;

    public TodayTaskPanel(AnchorPane todayTaskPlaceholder, ObservableList<ReadOnlyTask> todayTask) {
        super(FXML);
        setTodayListView(todayTask);
        addToPlaceholder(todayTaskPlaceholder);
    }

    /*
     * Sets today's taskList in todayTaskListView
     */
    private void setTodayListView(ObservableList<ReadOnlyTask> todayList) {
        todayTaskListView.setItems(todayList);
        todayTaskListView.setCellFactory(listView -> new UpcomingTaskViewCell());
    }

    private void addToPlaceholder(AnchorPane placeHolderPane) {
        SplitPane.setResizableWithParent(placeHolderPane, false);
        FxViewUtil.applyAnchorBoundaryParameters(getRoot(), 0.0, 0.0, 0.0, 0.0);
        placeHolderPane.getChildren().add(getRoot());
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
