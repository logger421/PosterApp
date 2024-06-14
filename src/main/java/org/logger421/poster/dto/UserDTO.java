package org.logger421.poster.dto;

import lombok.*;
import org.logger421.poster.models.Role;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String profilePictureUrl;
}
