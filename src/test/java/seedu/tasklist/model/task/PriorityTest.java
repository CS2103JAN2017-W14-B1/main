package seedu.tasklist.model.task;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

//@@author A0143355J
public class PriorityTest {

    @Test
    public void isValidPriority() {
        //valid Priority
        assertTrue(Priority.isValidPriority("high"));
        assertTrue(Priority.isValidPriority("medium"));
        assertTrue(Priority.isValidPriority("low"));

        //invalid Priority
        assertFalse(Priority.isValidPriority("important"));
        assertFalse(Priority.isValidPriority(""));
        assertFalse(Priority.isValidPriority("not important"));

    }
}
