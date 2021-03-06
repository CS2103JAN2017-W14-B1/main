package seedu.tasklist.logic.parser;

import static seedu.tasklist.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.tasklist.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.tasklist.logic.commands.AddCommand;
import seedu.tasklist.logic.commands.ClearCommand;
import seedu.tasklist.logic.commands.Command;
import seedu.tasklist.logic.commands.DeleteCommand;
import seedu.tasklist.logic.commands.DoneCommand;
import seedu.tasklist.logic.commands.EditCommand;
import seedu.tasklist.logic.commands.ExitCommand;
import seedu.tasklist.logic.commands.FindCommand;
import seedu.tasklist.logic.commands.HelpCommand;
import seedu.tasklist.logic.commands.IncorrectCommand;
import seedu.tasklist.logic.commands.ListCommand;
import seedu.tasklist.logic.commands.LoadCommand;
import seedu.tasklist.logic.commands.RedoCommand;
import seedu.tasklist.logic.commands.SaveCommand;
import seedu.tasklist.logic.commands.SortCommand;
import seedu.tasklist.logic.commands.UndoCommand;
import seedu.tasklist.logic.commands.UndoneCommand;

/**
 * Parses user input.
 */
public class Parser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    //@@author A0139747N
    /**
     * Default constructor that initialises the hashtables for flexible commands and prefixes at program startup.
     */
    public Parser() {
        ParserUtil.initialiseFlexibleCommands();
        ParserUtil.initialiseFlexiblePrefixes();
    }
    //@@author
    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     */
    public Command parseCommand(String userInput) {
        Hashtable<String, String> flexibleCommands = ParserUtil.getFlexibleCommands();
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        String commandWord = matcher.group("commandWord");
        commandWord = commandWord.toLowerCase();
        String acceptedCommandWord = commandWord;
        if (flexibleCommands.containsKey(commandWord)) {
            acceptedCommandWord = flexibleCommands.get(commandWord);
        }
        final String arguments = matcher.group("arguments");
        switch (acceptedCommandWord) {

        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case DoneCommand.COMMAND_WORD:
            return new DoneCommandParser().parse(arguments);

        case UndoneCommand.COMMAND_WORD:
            return new UndoneCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommandParser().parse(arguments);

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case UndoCommand.COMMAND_WORD:
            return new UndoCommand();

        case RedoCommand.COMMAND_WORD:
            return new RedoCommand();

        case SortCommand.COMMAND_WORD:
            return new SortCommandParser().parse(arguments);

        case LoadCommand.COMMAND_WORD:
            return new LoadCommandParser().parse(arguments);

        case SaveCommand.COMMAND_WORD:
            return new SaveCommandParser().parse(arguments);

        default:
            return new IncorrectCommand(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
