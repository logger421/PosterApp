package org.logger421.poster.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name = "post")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String title;
    private String content;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_class_id", nullable = false)
    private User author;
}
