package com.Tejas.Student.management.service;

import com.Tejas.Student.management.model.Student;
import com.Tejas.Student.management.model.User;
import com.Tejas.Student.management.repo.StudentRepo;
import com.Tejas.Student.management.repo.UserRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepo srepo;
    private final UserRepo urepo;

    public StudentService(StudentRepo srepo, UserRepo urepo) {
        this.srepo = srepo;
        this.urepo = urepo;
    }

    public List<Student> getAllStudent(){
        return srepo.findAll();
    }
    public Student getStudentById(Long id){
        return srepo.findById(id).orElse(null);
    }
    public Student updateStudent(Student stud){
        if (srepo.existsById(stud.getId())) {
            return srepo.save(stud);
        } {
            return null; // or throw custom exception
        }
    }
    public String deleteStudent(Long id) {
        if (srepo.existsById(id)) {
            Student student = srepo.findById(id).orElse(null);

            if (student != null && student.getUser() != null) {
                User user = student.getUser();
                user.setStudent(null);
                student.setUser(null);
                urepo.delete(user);
                srepo.delete(student);
                return "User and Student deleted";
            }
        }
        return "not deleted";
    }

    public Student addStudent(Student stud){
        if (stud.getUser() != null) {
            Long id = stud.getUser().getId();
            User existinguser = urepo.findById(id).orElse(null);
            existinguser.setStudent(stud);
            stud.setUser(existinguser);
            return urepo.save(stud.getUser()).getStudent();
        }
        System.out.println("error");
        return srepo.save(stud); // 💾 saves student to database
    }

    public Student getStudentByUsername(String username) {
        User user = urepo.findByUsername(username);
        if(user==null)
            return  null;
        return user.getStudent();
    }
}
