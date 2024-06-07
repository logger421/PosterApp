package org.logger421.poster.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentDTO {
    private Long postId;
    private String userName;
    private String comment;
    private Date createdAt;
}
