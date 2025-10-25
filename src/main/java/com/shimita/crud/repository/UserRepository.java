package com.shimita.crud.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shimita.crud.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // find user by email/username
    Optional<User> findByUsername(String usernameString);
}
