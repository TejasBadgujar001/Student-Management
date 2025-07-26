package com.Tejas.Student.management.service;

import com.Tejas.Student.management.model.User;
import com.Tejas.Student.management.repo.UserRepo;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepo urepo;

    public UserService(UserRepo urepo) {
        this.urepo = urepo;
    }

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public User createUser(User user){
        user.setPassword(encoder.encode(user.getPassword()));
        return urepo.save(user);
    }
    public User getUserByUsername(String username){
        return  urepo.findByUsername(username);
    }

}
