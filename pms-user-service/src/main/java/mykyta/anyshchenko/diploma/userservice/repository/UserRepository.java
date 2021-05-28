package mykyta.anyshchenko.diploma.userservice.repository;

import mykyta.anyshchenko.diploma.userservice.model.UserDto;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends ReactiveMongoRepository<UserDto, String> {
}
