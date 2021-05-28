package mykyta.anyshchenko.diploma.taskservice.repository;

import mykyta.anyshchenko.diploma.taskservice.model.TaskDto;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends ReactiveMongoRepository<TaskDto, String> {
}
