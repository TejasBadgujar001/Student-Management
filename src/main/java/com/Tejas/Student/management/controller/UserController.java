package com.Tejas.Student.management.controller;


import com.Tejas.Student.management.configuration.SecurityConfiguration;
import com.Tejas.Student.management.model.User;
import com.Tejas.Student.management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {
    private UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/user")
    public ResponseEntity<User> createUser(@RequestBody User user){
        return new ResponseEntity<>(service.createUser(user), HttpStatus.CREATED);
    }
    @GetMapping("/user/{username}")
    public ResponseEntity<User> findUserByUsername(@PathVariable String username){
        return  new ResponseEntity<>(service.getUserByUsername(username),HttpStatus.OK);
    }
}
