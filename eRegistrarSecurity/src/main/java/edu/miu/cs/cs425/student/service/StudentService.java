package edu.miu.cs.cs425.student.service;

import edu.miu.cs.cs425.student.model.Student;

import java.util.List;

public interface StudentService {
    Student addNewStudent(Student newStudnet);
    List<Student> getAllStudent();

    Student updateStudent(Student student);

    public Student getStudentById(Long studentId);

    void deleteStudent(Long studentId);

   List<Student> findStudentByName(String searchString);





}
