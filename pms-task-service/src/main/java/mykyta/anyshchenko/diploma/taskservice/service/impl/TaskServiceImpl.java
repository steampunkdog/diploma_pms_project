package mykyta.anyshchenko.diploma.taskservice.service.impl;

import mykyta.anyshchenko.diploma.discovery.exception.EntityNotFoundException;
import mykyta.anyshchenko.diploma.taskservice.model.TaskDto;
import mykyta.anyshchenko.diploma.taskservice.repository.TaskRepository;
import mykyta.anyshchenko.diploma.taskservice.service.TaskService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Validated
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Mono<TaskDto> getTaskById(String id) {
        return taskRepository.findById(id)
                .switchIfEmpty(Mono.error(new EntityNotFoundException(id, TaskDto.class)));
    }

    public Flux<TaskDto> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Mono<Void> addTask(TaskDto task) {
        return task.getId() == null
                ? taskRepository.save(task).then()
                : Mono.error(new UnsupportedOperationException("Task must not contain id"));
    }

    @Override
    public Mono<Void> updateTask(TaskDto task) {
        return getTaskById(task.getId())
                .then(taskRepository.save(task))
                .then();
    }
}
