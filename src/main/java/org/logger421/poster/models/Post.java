package org.logger421.poster.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String content;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_class_id", nullable = false)
    private User author;
    private Date createdAt;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_class_id")
    private Set<User> likes;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    private Set<Comment> comments;
}
