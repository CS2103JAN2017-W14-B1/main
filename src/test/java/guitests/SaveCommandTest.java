package guitests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import seedu.tasklist.commons.core.Messages;
import seedu.tasklist.logic.commands.SaveCommand;
import seedu.tasklist.testutil.TestUtil;
//@@author A0141993X
public class SaveCommandTest extends TaskListGuiTest {
    private static String newFilePath = TestUtil.getFilePathInSandboxFolder("sampleData.xml");

    @Test
    public void save_invalidCommand_errorMessageShown() {
        String command = "saved testFile.xml";
        commandBox.runCommand(command);
        assertResultMessage(Messages.MESSAGE_UNKNOWN_COMMAND);
    }

    @Test
    public void save_invalidExtension_errorMessageShown() {
        String command = "save testFile";
        commandBox.runCommand(command);
        assertResultMessage(String.format(SaveCommand.MESSAGE_INVALID_PATH, "testFile"));
    }
    @Test
    public void save_validFilePathCreateFile_success() {
        String command = "save data/testTasklist.xml";
        commandBox.runCommand(command);
        assertResultMessage(String.format(SaveCommand.MESSAGE_SUCCESS, "data/testTasklist.xml"));
    }

    @Test
    public void save_validFilePathCreateDirectory_success() {
        String command = "save testSave/data/testTasklist.xml";
        commandBox.runCommand(command);
        assertResultMessage(String.format(SaveCommand.MESSAGE_SUCCESS, "testSave/data/testTasklist.xml"));
    }

    @Test
    public void save_validFilePath_success() {
        String command = "save " + newFilePath;
        commandBox.runCommand(command);
        assertResultMessage(String.format(SaveCommand.MESSAGE_SUCCESS, newFilePath));
        assertTrue(taskListPanel.isListMatching(td.getTypicalTasks()));
    }

}
