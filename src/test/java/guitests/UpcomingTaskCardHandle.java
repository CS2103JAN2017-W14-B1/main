package guitests;

import guitests.guihandles.GuiHandle;
import javafx.scene.Node;
import javafx.stage.Stage;

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
}
