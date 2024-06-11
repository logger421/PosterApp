package org.logger421.poster.preload;

import org.logger421.poster.models.Post;
import org.logger421.poster.models.Role;
import org.logger421.poster.models.User;
import org.logger421.poster.repositiories.PostRepository;
import org.logger421.poster.repositiories.UserRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;

@Component
public class DataLoader implements ApplicationRunner {
    private final PasswordEncoder bcryptEncoder;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public DataLoader(UserRepository userRepository, PostRepository postRepository, PasswordEncoder bcryptEncoder) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.bcryptEncoder = bcryptEncoder;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        User user1 = new User(1L, "user1", bcryptEncoder.encode("user"), "user1@user.com", "Jan", "Kowalski", new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), null, Role.USER);
        User user2 = new User(2L, "user2", bcryptEncoder.encode("user"), "user2@user.com", "Adam", "Bartosik", new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), null, Role.USER);
        User adminUser = new User(3L, "admin", bcryptEncoder.encode("admin"), "admin@admin.com", "Karol", "Nowak", new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), null, Role.ADMIN);

        if (userRepository.count() == 0) {
            userRepository.save(user1);
            userRepository.save(user2);
            userRepository.save(adminUser);
        }
        if (postRepository.count() == 0) {
            postRepository.save(new Post(null, "Example content4", user1, new Timestamp(System.currentTimeMillis()), new HashSet<>(), new ArrayList<>()));
            postRepository.save(new Post(null, "Example content5", user2, new Timestamp(System.currentTimeMillis()), new HashSet<>(), new ArrayList<>()));
            postRepository.save(new Post(null, "Example content6", user1, new Timestamp(System.currentTimeMillis()), new HashSet<>(), new ArrayList<>()));
        }
    }
}
