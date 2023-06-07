package com.example.fulllogin.repository;

import com.example.fulllogin.user.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

  Optional<User> findByEmail(String email );
}
