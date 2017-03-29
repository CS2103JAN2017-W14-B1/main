package seedu.tasklist.commons.events.storage;

import seedu.tasklist.commons.events.BaseEvent;

//@@author A0141993X
/**
 * Indicates a changed in the file storage location
 */
public class TaskListFilePathChangedEvent extends BaseEvent {

    public String filePath;

    public TaskListFilePathChangedEvent(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public String toString() {
        return "File is saved at: " + filePath;
    }

}
