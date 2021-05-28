package mykyta.anyshchenko.diploma.model;

import mykyta.anyshchenko.diploma.model.enums.TaskStatus;

import java.time.LocalDateTime;

public interface Task {

    String getId();
    String getTitle();
    String getDescription();
    String getAssignedUserId();
    String getCreatorUserID();
    TaskStatus getStatus();
    LocalDateTime getStartFrom();
    LocalDateTime getCompleteTo();

}
