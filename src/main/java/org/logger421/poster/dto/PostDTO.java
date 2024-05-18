package org.logger421.poster.dto;

import org.logger421.poster.models.User;

public record PostDTO(User author, String title, String content) {
}
