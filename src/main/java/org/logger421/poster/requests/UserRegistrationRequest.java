package org.logger421.poster.requests;

import org.logger421.poster.models.Role;

public record UserRegistrationRequest(String username, String password, String email, Role role) {
}
