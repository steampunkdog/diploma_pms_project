package mykyta.anyshchenko.diploma.adapter;


import mykyta.anyshchenko.diploma.model.Task;
import mykyta.anyshchenko.diploma.model.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TaskServiceAdapter {

    Mono<Task> getTaskById(String id);
    Flux<Task> getAllTasks();

}
