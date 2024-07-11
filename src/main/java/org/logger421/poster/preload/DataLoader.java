package org.logger421.poster.preload;

import org.logger421.poster.models.Comment;
import org.logger421.poster.models.Post;
import org.logger421.poster.models.Role;
import org.logger421.poster.models.User;
import org.logger421.poster.repositiories.CommentRepository;
import org.logger421.poster.repositiories.PostRepository;
import org.logger421.poster.repositiories.UserRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Component
public class DataLoader implements ApplicationRunner {
    private final PasswordEncoder bcryptEncoder;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public DataLoader(UserRepository userRepository, PostRepository postRepository, PasswordEncoder bcryptEncoder, CommentRepository commentRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.bcryptEncoder = bcryptEncoder;
        this.commentRepository = commentRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        User user1 = User
                .builder()
                .id(null)
                .firstName("John")
                .lastName("Smith")
                .username("TestUser")
                .email("testuser@gmail.com")
                .password(bcryptEncoder.encode("user"))
                .role(Role.USER)
                .posts(new ArrayList<>())
                .comments(new ArrayList<>())
                .friends(new ArrayList<>())
                .likedPosts(new ArrayList<>())
                .profilePictureUrl(null).build();
        User user2 = User
                .builder()
                .id(null)
                .firstName("Abbey")
                .lastName("Brown")
                .username("FamousUser")
                .email("famous@gmail.com")
                .password(bcryptEncoder.encode("user"))
                .role(Role.USER)
                .posts(new ArrayList<>())
                .comments(new ArrayList<>())
                .friends(new ArrayList<>())
                .likedPosts(new ArrayList<>())
                .profilePictureUrl(null).build();
        User user = User
                .builder()
                .id(null)
                .firstName("John")
                .lastName("Smith")
                .username("SimpleUser")
                .email("simpleUser@gmail.com")
                .password(bcryptEncoder.encode("user"))
                .role(Role.USER)
                .posts(new ArrayList<>())
                .comments(new ArrayList<>())
                .friends(List.of(user1, user2))
                .likedPosts(new ArrayList<>())
                .profilePictureUrl(null).build();
        User adminUser = User
                .builder()
                .id(null)
                .firstName("Charles")
                .lastName("Charles")
                .username("admin")
                .email("admin@poster.com")
                .password(bcryptEncoder.encode("user"))
                .role(Role.USER)
                .posts(new ArrayList<>())
                .comments(new ArrayList<>())
                .friends(new ArrayList<>())
                .likedPosts(new ArrayList<>())
                .profilePictureUrl(null).build();

        if (userRepository.count() == 0) {
            userRepository.save(user1);
            userRepository.save(user2);
            userRepository.save(user);
            userRepository.save(adminUser);
        }

        if (postRepository.count() == 0) {
            postRepository.save(new Post(null, "A little progress each day adds up to big results. Keep going!", user1, new Timestamp(System.currentTimeMillis()), new HashSet<>(), new ArrayList<>()));
            postRepository.save(new Post(null, "Challenges are what make life interesting and overcoming them is what makes life meaningful. Embrace your struggles and turn them into strengths. You have the power to conquer anything!", user1, new Timestamp(System.currentTimeMillis()), new HashSet<>(), new ArrayList<>()));
            postRepository.save(new Post(null, "Grateful for the little things in life. What are you thankful for today?", user2, new Timestamp(System.currentTimeMillis()), new HashSet<>(), new ArrayList<>()));
            postRepository.save(new Post(null, "It's easy to get caught up in the hustle and bustle of daily life. Remember to take a moment to appreciate the beauty around you. Breathe deeply, smile often, and enjoy the journey.", user2, new Timestamp(System.currentTimeMillis()), new HashSet<>(), new ArrayList<>()));
            postRepository.save(new Post(null, "Start your day with a smile and a positive thought. Happy Monday!", user, new Timestamp(System.currentTimeMillis()), new HashSet<>(), new ArrayList<>()));
            postRepository.save(new Post(null, "Life is better with friends by your side. Cherish every moment!", user, new Timestamp(System.currentTimeMillis()), new HashSet<>(), new ArrayList<>()));
            postRepository.save(new Post(null, "Believe in yourself and all that you are. You are capable of great things!", user, new Timestamp(System.currentTimeMillis()), new HashSet<>(), new ArrayList<>()));
        }

        if (commentRepository.count() == 0) {
            commentRepository.save(new Comment(null, postRepository.getPostById(1), userRepository.findById(1L), "Great post!", new Timestamp(System.currentTimeMillis())));
            commentRepository.save(new Comment(null, postRepository.getPostById(1), userRepository.findById(2L), "Awesome!", new Timestamp(System.currentTimeMillis())));
            commentRepository.save(new Comment(null, postRepository.getPostById(1), userRepository.findById(3L), "So happy for you!", new Timestamp(System.currentTimeMillis())));
            commentRepository.save(new Comment(null, postRepository.getPostById(1), userRepository.findById(1L), "Agree!", new Timestamp(System.currentTimeMillis())));

            commentRepository.save(new Comment(null, postRepository.getPostById(2), userRepository.findById(1L), "Great post!", new Timestamp(System.currentTimeMillis())));
            commentRepository.save(new Comment(null, postRepository.getPostById(2), userRepository.findById(2L), "One mind", new Timestamp(System.currentTimeMillis())));
            commentRepository.save(new Comment(null, postRepository.getPostById(2), userRepository.findById(3L), "100% truth", new Timestamp(System.currentTimeMillis())));
            commentRepository.save(new Comment(null, postRepository.getPostById(2), userRepository.findById(2L), "Indeed!", new Timestamp(System.currentTimeMillis())));

            commentRepository.save(new Comment(null, postRepository.getPostById(3), userRepository.findById(1L), "Great post!", new Timestamp(System.currentTimeMillis())));
            commentRepository.save(new Comment(null, postRepository.getPostById(3), userRepository.findById(2L), "Awesome!", new Timestamp(System.currentTimeMillis())));
            commentRepository.save(new Comment(null, postRepository.getPostById(3), userRepository.findById(3L), "So happy for you!", new Timestamp(System.currentTimeMillis())));
            commentRepository.save(new Comment(null, postRepository.getPostById(3), userRepository.findById(1L), "Agree!", new Timestamp(System.currentTimeMillis())));

            commentRepository.save(new Comment(null, postRepository.getPostById(4), userRepository.findById(1L), "Great post!", new Timestamp(System.currentTimeMillis())));
            commentRepository.save(new Comment(null, postRepository.getPostById(4), userRepository.findById(2L), "One mind", new Timestamp(System.currentTimeMillis())));
            commentRepository.save(new Comment(null, postRepository.getPostById(4), userRepository.findById(3L), "100% truth", new Timestamp(System.currentTimeMillis())));
            commentRepository.save(new Comment(null, postRepository.getPostById(4), userRepository.findById(2L), "Indeed!", new Timestamp(System.currentTimeMillis())));

            commentRepository.save(new Comment(null, postRepository.getPostById(5), userRepository.findById(1L), "Great post!", new Timestamp(System.currentTimeMillis())));
            commentRepository.save(new Comment(null, postRepository.getPostById(5), userRepository.findById(2L), "Awesome!", new Timestamp(System.currentTimeMillis())));
            commentRepository.save(new Comment(null, postRepository.getPostById(5), userRepository.findById(3L), "So happy for you!", new Timestamp(System.currentTimeMillis())));
            commentRepository.save(new Comment(null, postRepository.getPostById(5), userRepository.findById(1L), "Agree!", new Timestamp(System.currentTimeMillis())));

            commentRepository.save(new Comment(null, postRepository.getPostById(6), userRepository.findById(1L), "Great post!", new Timestamp(System.currentTimeMillis())));
            commentRepository.save(new Comment(null, postRepository.getPostById(6), userRepository.findById(2L), "One mind", new Timestamp(System.currentTimeMillis())));
            commentRepository.save(new Comment(null, postRepository.getPostById(6), userRepository.findById(3L), "100% truth", new Timestamp(System.currentTimeMillis())));
            commentRepository.save(new Comment(null, postRepository.getPostById(6), userRepository.findById(2L), "Indeed!", new Timestamp(System.currentTimeMillis())));

            commentRepository.save(new Comment(null, postRepository.getPostById(7), userRepository.findById(1L), "Great post!", new Timestamp(System.currentTimeMillis())));
            commentRepository.save(new Comment(null, postRepository.getPostById(7), userRepository.findById(2L), "Awesome!", new Timestamp(System.currentTimeMillis())));
            commentRepository.save(new Comment(null, postRepository.getPostById(7), userRepository.findById(3L), "So happy for you!", new Timestamp(System.currentTimeMillis())));
            commentRepository.save(new Comment(null, postRepository.getPostById(7), userRepository.findById(1L), "Agree!", new Timestamp(System.currentTimeMillis())));
        }
    }
}
