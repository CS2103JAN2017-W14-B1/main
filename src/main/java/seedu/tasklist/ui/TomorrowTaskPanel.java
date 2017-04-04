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

public class TomorrowTaskPanel extends UiPart<Region> {
    private final Logger logger = LogsCenter.getLogger(TomorrowTaskPanel.class);
    private static final String FXML = "TomorrowTaskPanel.fxml";

    @FXML
    private ListView<ReadOnlyTask> tomorrowTaskListView;

    public TomorrowTaskPanel(AnchorPane tomorrowTaskPlaceholder, ObservableList<ReadOnlyTask> tomorrowTask) {
        super(FXML);
        setTomorrowListView(tomorrowTask);
        addToPlaceholder(tomorrowTaskPlaceholder);
    }

    /*
     * Sets tomorrow's taskList in tomorrowTaskListView
     */
    private void setTomorrowListView(ObservableList<ReadOnlyTask> tomorrowList) {
        tomorrowTaskListView.setItems(tomorrowList);
        tomorrowTaskListView.setCellFactory(listView -> new UpcomingTaskViewCell());
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
