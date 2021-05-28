package mykyta.anyshchenko.diploma.userservice.service;

import mykyta.anyshchenko.diploma.userservice.model.UserDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

public interface UserService {

    Mono<UserDto> getUserById(String id);
    Flux<UserDto> getAllUsers();
    Mono<Void> registrateClient(@Valid UserDto user);
    Mono<Void> registrateEmployee(@Valid UserDto user);
    Mono<Void> anonymizeUserById(String id);

}
