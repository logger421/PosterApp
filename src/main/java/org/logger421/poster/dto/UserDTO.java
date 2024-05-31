package org.logger421.poster.dto;

import lombok.*;
import org.logger421.poster.models.Role;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String username;
    private String password;
    private String email;
    private Role role;
}
