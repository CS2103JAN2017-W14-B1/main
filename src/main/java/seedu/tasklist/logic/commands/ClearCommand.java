package seedu.tasklist.logic.commands;

import seedu.tasklist.model.TaskList;

/**
 * Clears the address book.
 */
public class ClearCommand extends Command {
    //boolean to know if the find command is for tags.
    private boolean isByTags = false;
    private boolean isByStatus = false;
    private String keyword;

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Task list has been cleared!";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Clears tasks from the list. "
            + "If 'clear' is followed by t/[TAG], "
            + "all tasks having the tag [TAG] will be removed.\n"
            + "If followed by s/[completed/uncompleted], + "
            + "all tasks having the status will be removed.\n"
            + "If nothing follows 'clear', all tasks will be removed"
            + "Example: " + COMMAND_WORD + " t/work";

    /**
     * Constructor for single clear command.
     */
    public ClearCommand() {
        isByTags = false;
        isByStatus = false;
    }
    public ClearCommand isByStatus() {
        isByStatus = true;
        return this;
    }

    public ClearCommand isByTags() {
        isByTags = true;
        return this;
    }
    public ClearCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public CommandResult execute() {
        assert model != null;
        model.clearRedoStack();
        model.enableUndoForClear();


        if (isByTags) {
            model.removeTasksForClearByTag(keyword);
        } else if (isByStatus) {
            model.removeTasksForClearByStatus(keyword);
        } else {
            model.resetData(new TaskList());
        }

        return new CommandResult(MESSAGE_SUCCESS);
    }
}
