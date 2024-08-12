package com.example.Wellnessapp.repository;

import com.example.Wellnessapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    public User findByEmail(String username);

    boolean existsByEmail(String email);
}
