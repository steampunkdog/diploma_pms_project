package mykyta.anyshchenko.diploma.adapter;


import mykyta.anyshchenko.diploma.model.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserServiceAdapter {

    Mono<User> getUserById(String id);
    Flux<User> getAllUsers();

}
