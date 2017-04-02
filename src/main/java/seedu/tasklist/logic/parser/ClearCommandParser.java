package seedu.tasklist.logic.parser;

import static seedu.tasklist.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.tasklist.logic.parser.CliSyntax.KEYWORDS_ARGS_FORMAT;
import static seedu.tasklist.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.tasklist.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Optional;
import java.util.regex.Matcher;

import seedu.tasklist.logic.commands.ClearCommand;
import seedu.tasklist.logic.commands.Command;
import seedu.tasklist.logic.commands.FindCommand;
import seedu.tasklist.logic.commands.IncorrectCommand;

/**
 * Parses input arguments and creates ClearCommand object.
 *
 */
public class ClearCommandParser {
    private boolean isByTags = false;
    private boolean isByStatus = false;

    public Command parse(String args) {
        args = ParserUtil.parseFlexiblePrefix(args);
        ArgumentTokenizer argsTokenizer = new ArgumentTokenizer(PREFIX_TAG, PREFIX_STATUS);
        argsTokenizer.tokenize(args);
        Optional<String> keyword;
        keyword = argsTokenizer.getValue(PREFIX_TAG);
        isByTags = true;
        if (!keyword.isPresent()) {
            keyword = argsTokenizer.getValue(PREFIX_STATUS);
            isByStatus = true;
            isByTags = false;
        }
        if (!keyword.isPresent()) {
            keyword = Optional.of(args.trim());
            isByStatus = false;
            isByTags = false;
        }
        assert keyword.isPresent();
        Matcher matcher = KEYWORDS_ARGS_FORMAT.matcher(keyword.get().trim());
        if (!matcher.matches()) {
            return new IncorrectCommand(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        if (isByTags) {
            return new ClearCommand(keyword.get()).isByTags();
        } else if (isByStatus) {
            if (!keyword.get().equals("completed") || !keyword.get().equals("uncompleted")) {
                return new IncorrectCommand(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
            } else {
                return new ClearCommand(keyword.get()).isByStatus();
            }
        } else {    //clear all
            return new ClearCommand();
        }




        //return new ClearCommand(parameter, arg) overload, if nothing then ClearCommand().

    }
}
