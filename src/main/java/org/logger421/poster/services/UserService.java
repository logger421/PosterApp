package org.logger421.poster.services;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.logger421.poster.models.Role;
import org.logger421.poster.models.User;
import org.logger421.poster.repositiories.UserRepository;
import org.logger421.poster.requests.EditUserRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public record UserService(UserRepository repository, PasswordEncoder passwordEncoder) {

    private static final String UPLOAD_DIRECTORY = "src/main/resources/static/uploads";

    public User findByUsername(String username) {
        return repository.findByUsername(username);
    }

    public User findById(Long id) {
        Optional<User> user = repository.findById(id);
        return user.orElse(null);
    }

    public void registerCustomer(User userData) {
        log.info("Registering user {}", userData);
        User user = User.builder()
                .username(userData.getUsername())
                .password(passwordEncoder.encode(userData.getPassword()))
                .email(userData.getEmail())
                .role(userData.getRole() == null ? Role.USER : userData.getRole())
                .build();

        repository.save(user);
    }

    public void editUserData(EditUserRequest request, String userName) {
        User user = repository.findByUsername(userName);
        user.setUsername(request.userName());
        user.setEmail(request.userEmail());
        user.setFirstName(request.firstName());
        user.setLastName(request.lastName());

        repository.save(user);
    }

    public void uploadProfilePhoto(MultipartFile profilePicture, String username) {
        String extension = FilenameUtils.getExtension(profilePicture.getOriginalFilename());
        String uniqueFileName = UUID.randomUUID() + "_" + username + "." + extension;
        Path uploadPath = Path.of(UPLOAD_DIRECTORY);
        Path filePath = uploadPath.resolve(uniqueFileName);

        try {
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            Files.copy(profilePicture.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            log.error(ex.getMessage());
        }

        String imageUrl = "/uploads/" + uniqueFileName;

        log.info("Uploading profile photo to {}", imageUrl);

        User user = repository.findByUsername(username);
        user.setProfilePictureUrl(imageUrl);
        repository.save(user);
    }

    public List<User> getFriends(String userName) {
        User user = repository.findByUsername(userName);
        return user.getFriends();
    }

    public boolean addFriend(String username, String friendName) {
        User user = repository.findByUsername(username);
        User friend = repository.findByUsername(friendName);
        if (user == null || friend == null)
            return false;
        List<User> friends = user.getFriends();
        friends.add(friend);
        user.setFriends(friends);
        repository.save(user);
        return true;
    }

    public boolean deleteFriend(String username, String friendName) {
        User user = repository.findByUsername(username);
        User friend = repository.findByUsername(friendName);
        if (user == null || friend == null)
            return false;
        List<User> friends = user.getFriends();
        friends.remove(friend);
        user.setFriends(friends);
        repository.save(user);
        return true;
    }

    public boolean checkIfFriendExists(String username, String friendName) {
        User user = repository.findByUsername(username);
        User friend = repository.findByUsername(friendName);
        List<User> friends = user.getFriends();
        log.info("Checking if friend {} exists", friends.contains(friend));
        return friends.contains(friend);
    }

    public List<User> getSearchUsers(String searchPrompt) {
        return repository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrUsernameContainingIgnoreCase(searchPrompt, searchPrompt, searchPrompt);
    }


}
