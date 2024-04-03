package org.logger421.poster.requests;

public record UserRegistrationRequest(String username, String password, String email, String phone, String address,
                                      String city) {
}
