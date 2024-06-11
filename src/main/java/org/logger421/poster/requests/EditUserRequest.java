package org.logger421.poster.requests;

public record EditUserRequest(String userName, String userEmail, String firstName, String lastName) {
}
