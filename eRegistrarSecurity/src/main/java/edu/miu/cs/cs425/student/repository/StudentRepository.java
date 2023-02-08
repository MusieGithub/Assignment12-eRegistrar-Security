package edu.miu.cs.cs425.student.repository;

import edu.miu.cs.cs425.student.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    List<Student> findAllByFirstNameContainingOrLastNameContaining(
            String firstName, String lastName);
}
