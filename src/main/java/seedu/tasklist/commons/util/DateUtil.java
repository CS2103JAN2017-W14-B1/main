//@@author A0139221N
package seedu.tasklist.commons.util;

import java.util.Date;

import org.ocpsoft.prettytime.shade.org.apache.commons.lang.time.DateUtils;

/**
 * Helper functions for handling Dates.
 */
public class DateUtil {
    /**
     * @param dateToTest the date to be checked
     * @param lowerbound the starting date to be checked against
     * @param upperbound the ending date to be checked against
     * @return true if the dateToTest lies in between lowerbound and upperbound
     * The check is inclusive for lowerbound, meaning if dateToTest falls on lowerbound or upperbound, it returns true.
     */
    public static boolean isBetween (Date dateToTest, Date lowerbound, Date upperbound) {
        assert upperbound.after(lowerbound);
        return (DateUtils.isSameDay(lowerbound, dateToTest) || lowerbound.before(dateToTest))
                && (DateUtils.isSameDay(upperbound, dateToTest) || upperbound.after(dateToTest));
    }
}
