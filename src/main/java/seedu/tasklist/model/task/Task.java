package seedu.tasklist.model.task;

import seedu.tasklist.model.tag.UniqueTagList;
//@@author A0141993X
/**
 * Represents a Task in the task list.
 * Guarantees: details are present and not null, field values are validated.
 */
public abstract class Task implements ReadOnlyTask {

    /**
     * Sets name of task
     */
    public abstract void setName(Name name);

    /**
     * Returns name of task
     */
    public abstract Name getName();

    /**
     * Sets comment for a task
     */
    public abstract void setComment(Comment comment);

    /**
     * Returns comment for a task
     */
    public abstract Comment getComment();

    /**
     * Returns the tag list of task
     */
    public abstract UniqueTagList getTags();

    /**
     * Sets priority of a task
     */
    public abstract void setPriority(Priority priority);

    /**
     * Returns Priority of task
     */
    public abstract Priority getPriority();

    /**
     * Sets Status of task
     */
    public abstract void setStatus(Status status);

    /**
     * Returns Status of task
     */
    public abstract Status getStatus();

    /**
     * Replaces this task's tags with the tags in the argument tag list.
     */
    public abstract void setTags(UniqueTagList replacement);

}
