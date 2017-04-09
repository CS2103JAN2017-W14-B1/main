package seedu.tasklist.commons.util;

import static org.junit.Assert.assertNotNull;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class AppUtilTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();


    //author A0141993X
    @Test
    public void getImage_existingImage() {
        assertNotNull(AppUtil.getImage("/images/flexiTaskAppIcon.png"));
    }

    @Test
    public void getImage_nonExistingImage() {
        thrown.expect(NullPointerException.class);
        AppUtil.getImage("/images/flexiTaskAppIcon2.png");
    }

//@@author
    @Test
    public void getImage_nullGiven_assertionError() {
        thrown.expect(AssertionError.class);
        AppUtil.getImage(null);
    }


}
