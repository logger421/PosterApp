package org.logger421.poster.preload;

import org.logger421.poster.models.Post;
import org.logger421.poster.models.Role;
import org.logger421.poster.models.User;
import org.logger421.poster.repositiories.PostRepository;
import org.logger421.poster.repositiories.UserRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;

@Component
public class DataLoader implements ApplicationRunner {
    private final PasswordEncoder bcryptEncoder = new BCryptPasswordEncoder(8);
    private final UserRepository userRepository;
    private final PostRepository postRepository;


    public DataLoader(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        User user = new User(252L, "user", bcryptEncoder.encode("user"), "user@user.com", new ArrayList<>(), Role.USER);
        User adminUser = new User(2L, "admin", bcryptEncoder.encode("admin"), "admin@admin.com", new ArrayList<>(), Role.ADMIN);
        if(userRepository.count() == 0) {
            userRepository.save(user);
            userRepository.save(adminUser);
        }
        if(postRepository.count() == 0) {
            postRepository.save(new Post(null, "Example content1", user, new Timestamp(System.currentTimeMillis()), new HashSet<>(), new HashSet<>()));
            postRepository.save(new Post(null, "Example content2", user, new Timestamp(System.currentTimeMillis()), new HashSet<>(), new HashSet<>()));
            postRepository.save(new Post(null, "Example content3", user, new Timestamp(System.currentTimeMillis()), new HashSet<>(), new HashSet<>()));
        }
    }
}