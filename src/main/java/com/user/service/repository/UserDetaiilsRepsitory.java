package com.user.service.repository;

import com.user.service.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDetaiilsRepsitory extends JpaRepository<Users, Integer> {
    Optional<Users> findByUsername(String username);
}
