package edu.miu.cs.cs425.student;

import edu.miu.cs.cs425.student.model.Student;
import edu.miu.cs.cs425.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class Lab10Application implements CommandLineRunner {
    @Autowired
    StudentService studentService;
    public static void main(String[] args) {
        SpringApplication.run(Lab10Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Application Started");

//
//        Student dnna=new Student(null,"000-71-0001","Danna",
//                "Kames","Dnith",3.88, LocalDate.of(2009,03,24));
//        var savedDana=studentService.createNewStudent(dnna);
//        System.out.println(savedDana);
//
//        Student Anna=new Student(null,"000-61-0001","Anna",
//                "James","Smith",3.78, LocalDate.of(2019,05,12));
//        var savedAnna= studentService.createNewStudent(Anna);
//        System.out.println(savedAnna);



//        var studentList=studentService.getAllStudent();
//        System.out.println(studentList);


        System.out.println("Application Successfully ended");

    }
}
