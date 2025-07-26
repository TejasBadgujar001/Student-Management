package com.Tejas.Student.management.repo;

import com.Tejas.Student.management.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepo extends JpaRepository<Student, Long> {
}
