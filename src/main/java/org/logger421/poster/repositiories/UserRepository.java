package org.logger421.poster.repositiories;

import org.logger421.poster.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
