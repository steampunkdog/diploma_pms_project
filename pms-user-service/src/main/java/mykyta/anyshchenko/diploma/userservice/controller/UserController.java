package mykyta.anyshchenko.diploma.userservice.controller;

import mykyta.anyshchenko.diploma.userservice.model.UserDto;
import mykyta.anyshchenko.diploma.userservice.service.UserService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    Mono<UserDto> getUserById(@PathVariable String id) {
        return userService.getUserById(id);
    }

    @GetMapping
    Flux<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    Mono<Void> registrateClient(@RequestBody UserDto user) {
        return userService.registrateClient(user);
    }

    @PostMapping("/employee")
    Mono<Void> registrateEmployee(@RequestBody UserDto user) {
        return userService.registrateEmployee(user);
    }

    @PutMapping("/anonymize/{id}")
    Mono<Void> anonymizeUserById(@PathVariable String id) {
        return userService.anonymizeUserById(id);
    }
}
