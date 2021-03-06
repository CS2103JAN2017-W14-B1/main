package seedu.tasklist.storage;

import java.io.IOException;
import java.util.Optional;

import seedu.tasklist.commons.events.model.TaskListChangedEvent;
import seedu.tasklist.commons.events.storage.DataSavingExceptionEvent;
import seedu.tasklist.commons.exceptions.DataConversionException;
import seedu.tasklist.model.ReadOnlyTaskList;
import seedu.tasklist.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends TaskListStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(UserPrefs userPrefs) throws IOException;

    @Override
    String getTaskListFilePath();

    @Override
    Optional<ReadOnlyTaskList> readTaskList() throws DataConversionException, IOException;

    @Override
    void saveTaskList(ReadOnlyTaskList taskList) throws IOException;

    /**
     * Saves the current version of the Task List to the hard disk.
     *   Creates the data file if it is missing.
     * Raises {@link DataSavingExceptionEvent} if there was an error during saving.
     */
    void handleTaskListChangedEvent(TaskListChangedEvent abce);
  //@@author A0141993X
    /**
     * Loads new XmlTaskListStorage from file path.
     */
    void loadTaskList(String filePath);

    /**
     * Sets new file path for task list to be stored
     * @param filepath
     * @throws IOException
     */
    void setTaskListFilePath(String filePath) throws IOException;
}
