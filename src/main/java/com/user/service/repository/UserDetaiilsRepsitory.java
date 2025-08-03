package com.user.service.repository;

import com.user.service.dto.Role;
import com.user.service.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserDetaiilsRepsitory extends JpaRepository<Users, Integer> {
    Users findByUsername(String username);

    List<Users> findAllByRole(Role role);
}
