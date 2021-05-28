package mykyta.anyshchenko.diploma.taskservice.service;

import mykyta.anyshchenko.diploma.taskservice.model.TaskDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TaskService {

    Mono<TaskDto> getTaskById(String id);
    Flux<TaskDto> getAllTasks();
    Mono<Void> addTask(TaskDto task);
    Mono<Void> updateTask(TaskDto task);

}
