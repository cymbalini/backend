package com.app.backend.repository;

import com.app.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT p FROM User p WHERE LOWER(p.firstname) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(p.lastname) LIKE LOWER(CONCAT('%', :keyword, '%'))"
    )
    List<User> searchUsers(String keyword);

    User findByUsername(String username);
}
