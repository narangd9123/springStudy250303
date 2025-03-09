package com.myCho.springStudy250303.repository;

import com.myCho.springStudy250303.model.Board;
import com.myCho.springStudy250303.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);   //jpa query method

    boolean existsByUsername(String username);
}
