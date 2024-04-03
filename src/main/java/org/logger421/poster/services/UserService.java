package org.logger421.poster.services;

import org.logger421.poster.models.User;
import org.logger421.poster.repositiories.UserRepository;
import org.logger421.poster.requests.UserRegistrationRequest;
import org.springframework.stereotype.Service;

@Service
public record UserService(UserRepository repository) {
    public void registerCustomer(UserRegistrationRequest request) {
        User user = User.builder()
                .username(request.username())
                .password(request.password())
                .email(request.email())
                .phone(request.phone())
                .city(request.city())
                .build();
        repository.save(user);
    }
}
