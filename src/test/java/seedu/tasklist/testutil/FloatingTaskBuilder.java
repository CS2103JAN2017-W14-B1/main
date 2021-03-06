//@@author A0139221N
package seedu.tasklist.testutil;

import seedu.tasklist.commons.exceptions.IllegalValueException;
import seedu.tasklist.model.tag.Tag;
import seedu.tasklist.model.tag.UniqueTagList;
import seedu.tasklist.model.task.Comment;
import seedu.tasklist.model.task.Name;
import seedu.tasklist.model.task.Priority;
import seedu.tasklist.model.task.Status;

public class FloatingTaskBuilder extends TaskBuilder {
    private TestFloatingTask task;

    /**
     * Creates a floating task builder
     */
    public FloatingTaskBuilder() {
        this.task = new TestFloatingTask();
    }

    /**
     * Initializes the TaskBuilder with the data of {@code taskToCopy}.
     */
    public FloatingTaskBuilder(TestFloatingTask taskToCopy) {
        this.task = new TestFloatingTask(taskToCopy);
    }

    /**
     * Sets the name of the task.
     */
    public FloatingTaskBuilder withName(String name) throws IllegalValueException {
        this.task.setName(new Name(name));
        return this;
    }

    /**
     * Sets the tags of the task.
     */
    public FloatingTaskBuilder withTags(String ... tags) throws IllegalValueException {
        task.setTags(new UniqueTagList());
        for (String tag: tags) {
            task.getTags().add(new Tag(tag));
        }
        return this;
    }

    /**
     * Sets the comment of the task.
     */
    public FloatingTaskBuilder withComment(String comment) throws IllegalValueException {
        this.task.setComment(new Comment(comment));
        return this;
    }

    /**
     * Returns the task built.
     */
    public TestTask build() {
        return this.task;
    }

    /**
     * Sets the status of the task.
     */
    @Override
    public FloatingTaskBuilder withStatus(Boolean completed) throws IllegalValueException {
        this.task.setStatus(new Status(completed));
        return this;
    }

    /**
     * Sets the priority of the task.
     */
    @Override
    public FloatingTaskBuilder withPriority(String priority) throws IllegalValueException {
        this.task.setPriority(new Priority(priority));
        return this;
    }
}
