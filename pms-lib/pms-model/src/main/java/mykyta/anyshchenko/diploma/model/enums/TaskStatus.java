package mykyta.anyshchenko.diploma.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public class TaskStatus extends DynamicEnum<TaskStatus> {
    public static final TaskStatus OPEN = new TaskStatus("open");
    public static final TaskStatus IN_PROGRESS = new TaskStatus("in progress");
    public static final TaskStatus COMPLETED = new TaskStatus("completed");
    public static final TaskStatus FAILED = new TaskStatus("failed");

    public TaskStatus(String value) {
        super(value);
    }

    @JsonCreator
    public static TaskStatus create(String status) {
        return new TaskStatus(status);
    }
}
