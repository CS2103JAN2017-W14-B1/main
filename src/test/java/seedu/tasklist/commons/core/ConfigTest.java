package seedu.tasklist.commons.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ConfigTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void toString_defaultObject_stringReturned() {
        String defaultConfigAsString = "App title : FlexiTask\n" +
                "Current log level : INFO\n" +
                "Preference file Location : preferences.json\n" +
                "Local data file location : data/tasklist.xml\n" +
                "TaskList name : MyTaskList";

        assertEquals(defaultConfigAsString, new Config().toString());
    }
    //@@author A0141993X
    @Test
    public void equalsMethod() {
        Config defaultConfig = new Config();
        assertNotNull(defaultConfig);
        assertTrue(defaultConfig.equals(defaultConfig));

        Config config = new Config();
        assertFalse(config.equals(new Object()));
    }

    @Test
    public void hashCodeMethod() {
        Config defaultConfig = new Config();
        Config config = new Config();
        assertEquals(config.hashCode(), defaultConfig.hashCode());
    }
}
