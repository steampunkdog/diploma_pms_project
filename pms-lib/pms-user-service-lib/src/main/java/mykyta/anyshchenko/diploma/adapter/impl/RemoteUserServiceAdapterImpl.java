package mykyta.anyshchenko.diploma.adapter.impl;

import mykyta.anyshchenko.diploma.adapter.UserServiceAdapter;
import mykyta.anyshchenko.diploma.discovery.ServiceWebClientProvider;
import mykyta.anyshchenko.diploma.model.User;
import mykyta.anyshchenko.diploma.model.enums.Gender;
import mykyta.anyshchenko.diploma.model.enums.Role;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Service
public class RemoteUserServiceAdapterImpl implements UserServiceAdapter {

    public static final String USER_SERVICE_ID = "pms-user-service";

    private WebClient webClient;

    public RemoteUserServiceAdapterImpl(ServiceWebClientProvider serviceWebClientProvider) {
        this.webClient = serviceWebClientProvider.buildWebClientForService(USER_SERVICE_ID);
    }

    @Override
    public Mono<User> getUserById(String id) {
        return webClient
                .get()
                .uri("/user/" + id)
                .retrieve()
                .bodyToMono(UserDto.class)
                .cast(User.class);
    }

    @Override
    public Flux<User> getAllUsers() {
        return webClient
                .get()
                .uri("/user")
                .retrieve()
                .bodyToFlux(UserDto.class)
                .cast(User.class);
    }


    static class UserDto implements User {

        private String id;
        private String name;
        private String lastName;
        private String email;
        private String password;
        private Role role;
        private String phoneNumber;
        private LocalDate dateOfBirth;
        private Gender gender;
        private String homeCountry;

        @Override
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        @Override
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        @Override
        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        @Override
        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        @Override
        public Role getRole() {
            return role;
        }

        public void setRole(Role role) {
            this.role = role;
        }

        @Override
        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        @Override
        public LocalDate getDateOfBirth() {
            return dateOfBirth;
        }

        public void setDateOfBirth(LocalDate dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
        }

        @Override
        public Gender getGender() {
            return gender;
        }

        public void setGender(Gender gender) {
            this.gender = gender;
        }

        @Override
        public String getHomeCountry() {
            return homeCountry;
        }

        public void setHomeCountry(String homeCountry) {
            this.homeCountry = homeCountry;
        }
    }
}
