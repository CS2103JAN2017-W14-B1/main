package seedu.tasklist.logic.commands;

import java.util.EmptyStackException;

//@@author A0139747N
/**
 * Reverts the effect of a preceding undo command on the task list.
 */
public class RedoCommand extends Command {
    public static final String COMMAND_WORD = "redo";

    public static final String MESSAGE_REDO_SUCCESS = "Redid the most recent action that is undone: ";
    public static final String MESSAGE_REDO_FAILED = "No command to redo.";

    @Override
    public CommandResult execute() {
        try {
            String userInput = model.setNextState();
            return new CommandResult(MESSAGE_REDO_SUCCESS + userInput);
        } catch (EmptyStackException e) {
            return new CommandResult(MESSAGE_REDO_FAILED);
        }
    }

}
