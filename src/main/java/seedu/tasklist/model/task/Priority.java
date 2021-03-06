package seedu.tasklist.model.task;

import java.util.Optional;

import seedu.tasklist.commons.exceptions.IllegalValueException;

public class Priority {
    public static final String MESSAGE_PRIORITY_CONSTRAINTS =
            "Task priorities should only contain 'high', 'medium', or 'low', and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String PRIORITY_VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";
    public static final String PRIORITY_LOW = "low";
    public static final String PRIORITY_MEDIUM = "medium";
    public static final String PRIORITY_HIGH = "high";
    public static final String PRIORITY_NIL = "NIL";

    public final String value;

    /**
     * Validates given priority.
     *
     * @throws IllegalValueException if given name string is invalid.
     */
    public Priority(String priority) throws IllegalValueException {
        assert priority != null;
        String trimmedPriority = priority.trim();
        if (!isValidPriority(trimmedPriority)) {
            throw new IllegalValueException(MESSAGE_PRIORITY_CONSTRAINTS);
        }
        this.value = trimmedPriority;
    }

    //@@author A0143355J
    /**
     * Creates a Priority object with the appropriate {@code value}
     * @param priority is an Optional<String> Object containing priority from the parser
     * @throws IllegalValueException if invalid priority is entered
     */
    public Priority(Optional<String> priority) throws IllegalValueException {
        if (priority.isPresent()) {
            String priorityString = priority.get();
            assert priority != null;
            if (!isValidPriority(priorityString)) {
                throw new IllegalValueException(MESSAGE_PRIORITY_CONSTRAINTS);
            }
            this.value = getPriorityType(priorityString);
        } else {
            this.value = "NIL";
        }
    }

    /**
     * Returns true if Priority is present
     */
    //@@author A0143355J
    public boolean isPriorityPresent(Optional<String> priority) {
        return priority.isPresent();
    }

    /**
     * Returns true if a given string is a valid Priority.
     */
    public static boolean isValidPriority(String test) {
        switch (test) {
        case PRIORITY_HIGH:
            return true;
        case PRIORITY_MEDIUM:
            return true;
        case PRIORITY_LOW:
            return true;
        case PRIORITY_NIL:
            return true;
        default:
            return false;
        }
    }

    /**
     * Returns the priority type. Throws IllegalArgumentException if not available
     */
    public static String getPriorityType(String priority) throws IllegalArgumentException {
        String lowerCasePriority = priority.toLowerCase();
        if (lowerCasePriority.equals(PRIORITY_LOW)) {
            return PRIORITY_LOW;
        } else if (lowerCasePriority.equals(PRIORITY_MEDIUM)) {
            return PRIORITY_MEDIUM;
        } else if (lowerCasePriority.equals(PRIORITY_HIGH)) {
            return PRIORITY_HIGH;
        } else {
            throw new IllegalArgumentException(MESSAGE_PRIORITY_CONSTRAINTS);
        }
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Priority // instanceof handles nulls
                && this.value.equals(((Priority) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
//@@author A0141993X
    /** Compares the priority of one task with another */
    public int compareTo(Priority priority) {
        Integer thisValue = 0;
        Integer otherValue = 0;

        if (this.toString().equals(PRIORITY_HIGH)) {
            thisValue = 0;
        } else if (this.toString().equals(PRIORITY_MEDIUM)) {
            thisValue = 1;
        } else if (this.toString().equals(PRIORITY_LOW)) {
            thisValue = 2;
        } else if (this.toString().equals(PRIORITY_NIL)) {
            thisValue = 3;
        }
        if (priority.toString().equals(PRIORITY_HIGH)) {
            otherValue = 0;
        } else if (priority.toString().equals(PRIORITY_MEDIUM)) {
            otherValue = 1;
        } else if (priority.toString().equals(PRIORITY_LOW)) {
            otherValue = 2;
        }  else if (priority.toString().equals(PRIORITY_NIL)) {
            otherValue = 3;
        }

        return thisValue.compareTo(otherValue);
    }
}
