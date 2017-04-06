package seedu.tasklist.commons.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class FileUtilTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void getPath() {

        // valid case
        assertEquals("folder" + File.separator + "sub-folder", FileUtil.getPath("folder/sub-folder"));

        // null parameter -> assertion failure
        thrown.expect(AssertionError.class);
        FileUtil.getPath(null);

        // no forwards slash -> assertion failure
        thrown.expect(AssertionError.class);
        FileUtil.getPath("folder");
    }
//@@author A0141993X
    @Test
    public void isFileExists_fileExists_returnsTrue() throws IOException {
        String filePath = "filePathExists.xml";
        File file = new File(filePath);
        FileUtil.createFile(file);
        assertTrue(FileUtil.isFileExists(file));
    }

    @Test
    public void isFileExists_fileDoesNotExist_returnsFalse() {
        String filePath = "fileDoesNotExist.xml";
        File file = new File (filePath);
        assertFalse(FileUtil.isFileExists(file));
    }

    @Test
    public void createDirs_validDirectory_successful() throws IOException {
        String dir = "Users/test/saveData";
        File dirFile = new File(dir);
        FileUtil.createDirs(dirFile);
    }

    @Test  (expected = IOException.class)
    public void createDirs_invalidDirectory_throwsException() throws IOException {
        String dir = "test< \0? save";
        File dirFile = new File(dir);
        FileUtil.createDirs(dirFile);
    }

}
