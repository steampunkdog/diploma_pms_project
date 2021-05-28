package mykyta.anyshchenko.diploma.adapter.impl;

import mykyta.anyshchenko.diploma.adapter.TaskServiceAdapter;
import mykyta.anyshchenko.diploma.discovery.ServiceWebClientProvider;
import mykyta.anyshchenko.diploma.model.Task;
import mykyta.anyshchenko.diploma.model.enums.TaskStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
public class RemoteTaskServiceAdapterImpl implements TaskServiceAdapter {

    public static final String USER_SERVICE_ID = "pms-task-service";

    private WebClient webClient;

    public RemoteTaskServiceAdapterImpl(ServiceWebClientProvider serviceWebClientProvider) {
        this.webClient = serviceWebClientProvider.buildWebClientForService(USER_SERVICE_ID);
    }

    @Override
    public Mono<Task> getTaskById(String id) {
        return webClient
                .get()
                .uri("/task/" + id)
                .retrieve()
                .bodyToMono(TaskDto.class)
                .cast(Task.class);
    }

    @Override
    public Flux<Task> getAllTasks() {
        return webClient
                .get()
                .uri("/task")
                .retrieve()
                .bodyToFlux(TaskDto.class)
                .cast(Task.class);
    }


    static class TaskDto implements Task{

        private String id;
        private String title;
        private String description;
        private String assignedUserId;
        private String creatorUserID;
        private TaskStatus status;
        private LocalDateTime startFrom;
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

}
