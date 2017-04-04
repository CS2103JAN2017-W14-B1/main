package guitests.guihandles;

import guitests.GuiRobot;
import javafx.stage.Stage;
import seedu.tasklist.TestApp;

/**
 * Provides a handle for the main GUI.
 */
public class MainGuiHandle extends GuiHandle {

    public MainGuiHandle(GuiRobot guiRobot, Stage primaryStage) {
        super(guiRobot, primaryStage, TestApp.APP_TITLE);
    }

    public TaskListPanelHandle getTaskListPanel() {
        return new TaskListPanelHandle(guiRobot, primaryStage);
    }

    public ResultDisplayHandle getResultDisplay() {
        return new ResultDisplayHandle(guiRobot, primaryStage);
    }

    public CommandBoxHandle getCommandBox() {
        return new CommandBoxHandle(guiRobot, primaryStage, TestApp.APP_TITLE);
    }

    public MainMenuHandle getMainMenu() {
        return new MainMenuHandle(guiRobot, primaryStage);
    }

    //@@author A0143355J
    public TodayListPanelHandle getTodayListPanel() {
        return new TodayListPanelHandle(guiRobot, primaryStage);
    }

    public TomorrowListPanelHandle getTomorrowListPanel() {
        return new TomorrowListPanelHandle(guiRobot, primaryStage);
    }

    //@@author
    public AlertDialogHandle getAlertDialog(String title) {
        guiRobot.sleep(1000);
        return new AlertDialogHandle(guiRobot, primaryStage, title);
    }
}
