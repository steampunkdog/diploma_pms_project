package mykyta.anyshchenko.diploma.taskservice.model;

import mykyta.anyshchenko.diploma.model.Task;
import mykyta.anyshchenko.diploma.model.enums.TaskStatus;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Document(collection = "task")
public class TaskDto implements Task {

    @Id
    private String id;

    @NotNull
    @NotBlank
    private String title;

    private String description;

    @NotNull
    @NotBlank
    private String assignedUserId;

    @NotNull
    @NotBlank
    private String creatorUserID;

    @NotNull
    @NotBlank
    private TaskStatus status;

    private LocalDateTime startFrom;

    @NotNull
    @NotBlank
    private LocalDateTime completeTo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAssignedUserId() {
        return assignedUserId;
    }

    public void setAssignedUserId(String assignedUserId) {
        this.assignedUserId = assignedUserId;
    }

    public String getCreatorUserID() {
        return creatorUserID;
    }

    public void setCreatorUserID(String creatorUserID) {
        this.creatorUserID = creatorUserID;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public LocalDateTime getStartFrom() {
        return startFrom;
    }

    public void setStartFrom(LocalDateTime startFrom) {
        this.startFrom = startFrom;
    }

    public LocalDateTime getCompleteTo() {
        return completeTo;
    }

    public void setCompleteTo(LocalDateTime completeTo) {
        this.completeTo = completeTo;
    }
}
