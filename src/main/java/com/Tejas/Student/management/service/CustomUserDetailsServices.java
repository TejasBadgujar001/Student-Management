package com.Tejas.Student.management.service;

import com.Tejas.Student.management.model.User;
import com.Tejas.Student.management.repo.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsServices implements UserDetailsService {
    private UserRepo urepo;

    public CustomUserDetailsServices(UserRepo urepo) {
        this.urepo = urepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = urepo.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("User not found");
        }

        return new CustomUserDetails(user);
    }
}
