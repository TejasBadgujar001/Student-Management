package com.Tejas.Student.management.controller;


import com.Tejas.Student.management.model.Student;
import com.Tejas.Student.management.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentController {
    private StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @GetMapping("/student")
    public ResponseEntity<List<Student>> getAllStudent(){
        return new ResponseEntity<>(service.getAllStudent(), HttpStatus.OK);
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<Student>getStudentById(@PathVariable Long id){
        Student stud = service.getStudentById(id);
        if (stud == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(stud,HttpStatus.OK);
    }

    @PostMapping("/student")
    private ResponseEntity<Student> addStudent(@RequestBody Student stud){
        return new ResponseEntity<>( service.addStudent(stud),HttpStatus.CREATED);
    }

    @PutMapping("/student")
    public ResponseEntity<Student> updateStudent(@RequestBody Student stud){
        Student student= service.updateStudent(stud);
        if (student == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(student,HttpStatus.OK);
    }

    @DeleteMapping("/student/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id){
        return new ResponseEntity<>(service.deleteStudent(id),HttpStatus.OK);
    }

    @GetMapping("/student/profile")
    public ResponseEntity<Student> getOwnProfile(Authentication authentication) {
        String username = authentication.getName();
        Student student = service.getStudentByUsername(username);
        if (student == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }
}
