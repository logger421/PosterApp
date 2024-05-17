package org.logger421.poster.services;

import lombok.extern.slf4j.Slf4j;
import org.logger421.poster.models.Role;
import org.logger421.poster.models.User;
import org.logger421.poster.repositiories.UserRepository;
import org.logger421.poster.requests.UserRegistrationRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public record UserService(UserRepository repository, PasswordEncoder passwordEncoder) {

    public void registerCustomer(UserRegistrationRequest request) {
        log.info("Registering user {}", request);
        User user = User.builder()
                .username(request.username())
                .password(passwordEncoder.encode(request.password()))
                .email(request.email())
                .role(request.role() == null ? Role.USER : request.role())
                .build();
        repository.save(user);
    }
}
