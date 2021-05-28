package mykyta.anyshchenko.diploma.userservice.service.impl;

import mykyta.anyshchenko.diploma.discovery.exception.EntityNotFoundException;
import mykyta.anyshchenko.diploma.model.User;
import mykyta.anyshchenko.diploma.model.enums.Role;
import mykyta.anyshchenko.diploma.userservice.model.UserDto;
import mykyta.anyshchenko.diploma.userservice.repository.UserRepository;
import mykyta.anyshchenko.diploma.userservice.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.UUID;

@Service
@Validated
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Mono<UserDto> getUserById(String id) {
        return userRepository.findById(id)
                .switchIfEmpty(Mono.error(new EntityNotFoundException(id, User.class)));
    }

    public Flux<UserDto> getAllUsers() {
        return userRepository.findAll();
    }

    public Mono<Void> registrateClient(@Valid UserDto user) {
        if (Role.CLIENT.equals(user.getRole())) {
            return userRepository.save(user).then();
        } else {
            return Mono.error(new UnsupportedOperationException("Cannot use this API to registrate users with role different from CLIENT"));
        }
    }

    public Mono<Void> registrateEmployee(@Valid UserDto user) {
        return userRepository.save(user).then();
    }

    public Mono<Void> anonymizeUserById(String id) {
        return getUserById(id)
                .map(this::anonymizeUser)
                .flatMap(userRepository::save)
                .then();
    }

    private UserDto anonymizeUser(UserDto user) {
        UserDto anonymizedUser = new UserDto();
        anonymizedUser.setId(user.getId());
        anonymizedUser.setEmail("anonymized@anonymized.anonymized");
        anonymizedUser.setPhoneNumber("2222222222222");
        anonymizedUser.setName("Anonymized");
        anonymizedUser.setLastName("Anonymized");
        anonymizedUser.setPassword(UUID.randomUUID().toString());
        anonymizedUser.setRole(Role.ANONYMIZED);
        return anonymizedUser;
    }
}
