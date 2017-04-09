package seedu.tasklist.logic.parser;

import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.ocpsoft.prettytime.nlp.PrettyTimeParser;

import seedu.tasklist.commons.exceptions.IllegalValueException;

//@@author A0143355J
public class DateParser {

    public static final String NO_DATES_MESSAGE = "No dates were detected, please enter a valid date after /d.";
    public static final String EXCESS_DATES = "Please enter only one date for Deadlines and two dates for Events.";

    private static Pattern ddmmRegex = Pattern.compile("\\b(0?[12][0-9]|3[01])[/-](0?[1-9]|1[012])\\b");
    /**
     * Uses PrettyTime NLP library to parse the dates into a list of Date objects
     */
    public static List<Date> parse (String date) throws IllegalValueException {
        List<Date> dates = new PrettyTimeParser().parse(changeDateFormat(date));
        if (dates.isEmpty()) {
            throw new IllegalValueException(NO_DATES_MESSAGE);
        }
        if (dates.size() > 2) {
            throw new IllegalValueException(EXCESS_DATES);
        }
        return dates;
    }

    /**
     * PrettyTime parser reads dates in MMDD format
     * Changes date to that format to be read by PrettyTimeParser
     * @param date is the String for date captured by the parser
     * @return returns the string with DDMM swapped to be MMDD
     */
    private static String changeDateFormat(String date) {
        Matcher ddmm = ddmmRegex.matcher(date);
        while (ddmm.find()) {
            String dateFound = ddmm.group();
            String day = ddmm.group(1);
            String month = ddmm.group(2);
            String changedDate = month + "/" + day;
            date = date.replaceAll(dateFound, changedDate);
        }
        return date;
    }
}
