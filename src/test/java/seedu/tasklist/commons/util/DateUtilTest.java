//@@author A0139221N
package seedu.tasklist.commons.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

public class DateUtilTest {

    @Test
    public void isBetweenTest() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        Date today = c.getTime();
        c.add(Calendar.DATE, 1);
        Date tomorrow = c.getTime();
        c.add(Calendar.DATE, 6);
        Date oneWeekLater = c.getTime();
        //normal case
        assertTrue(DateUtil.isBetween(tomorrow, today, oneWeekLater));
        //boundary cases
        assertTrue(DateUtil.isBetween(today, today, oneWeekLater));
        assertTrue(DateUtil.isBetween(oneWeekLater, today, oneWeekLater));
        //same day case
        assertTrue(DateUtil.isBetween(today, today, today));

        c.add(Calendar.DATE, 1);
        Date oneWeekAndOneDayLater = c.getTime();
        c.add(Calendar.DATE, -9);
        Date yesterday = c.getTime();
        //boundary case
        assertFalse(DateUtil.isBetween(oneWeekAndOneDayLater, today, oneWeekLater));
        assertFalse(DateUtil.isBetween(yesterday, today, oneWeekLater));
        //upperbound earlier than lowerbound
        try {
            DateUtil.isBetween(today, tomorrow, yesterday);
        } catch (AssertionError ae) {
            assertEquals(ae.getMessage(), "upperbound Date must be after lowerbound Date");
        }
    }
}
