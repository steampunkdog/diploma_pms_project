package mykyta.anyshchenko.diploma.taskservice.controller;

import mykyta.anyshchenko.diploma.taskservice.model.TaskDto;
import mykyta.anyshchenko.diploma.taskservice.service.TaskService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("task")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/{id}")
    Mono<TaskDto> getTaskById(@PathVariable String id) {
        return taskService.getTaskById(id);
    }

    @GetMapping
    Flux<TaskDto> getAllTasks() {
        return taskService.getAllTasks();
    }

    @PostMapping
    Mono<Void> addTask(@RequestBody TaskDto task) {

        return taskService.addTask(task);
    }

    @PutMapping
    Mono<Void> updateTask(@RequestBody TaskDto task) {
        return taskService.updateTask(task);
    }
}
