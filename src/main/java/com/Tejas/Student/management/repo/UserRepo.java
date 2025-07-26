package com.Tejas.Student.management.repo;

import com.Tejas.Student.management.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    public User findByUsername(String username);
}
