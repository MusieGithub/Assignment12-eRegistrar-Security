package edu.miu.cs.cs425.student.controller;

import edu.miu.cs.cs425.student.model.Student;
import edu.miu.cs.cs425.student.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StudentController {
    @Autowired
    StudentService studentService;
    @GetMapping(value = {"/secured/student/home","/"})
    public String displayHome(){
        return "public/index";
    }
    @GetMapping(value = {"/secured/student/about"})
    public String displayAbout(){
        return "public/about";
    }
    @GetMapping(value = "/secured/student/list")
    public ModelAndView displayListOfStudent(){
        var modelAndView=new ModelAndView();
        var student=studentService.getAllStudent();
        modelAndView.addObject("fetchedStudent",student);
        modelAndView.setViewName("secured/student/list");
        return modelAndView;
    }

    @GetMapping(value={"/secured/student/new"})
    public String displayAddingPage(Model model){
        model.addAttribute("student",new Student());
        return "secured/student/new";
    }
    @PostMapping(value={"/secured/student/new"})
    public String addStudent(@Valid @ModelAttribute("student")Student student,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("student",student);
            model.addAttribute("error",result.getAllErrors());
            return "secured/student/new";
        }

        studentService.addNewStudent(student);
        return "redirect:/secured/student/list";
    }

    //Delete

        @GetMapping(value = {"/secured/student/list/delete/{studentId}"})
    public String deletePublisher(@PathVariable Long studentId) {
            studentService.deleteStudent(studentId);
        return "redirect:/secured/student/list";
    }

    //Editing

    @GetMapping(value = {"/secured/student/list/edit/{studentId}"})
    public String editStudent(@PathVariable Long studentId, Model model) {
        var student = studentService.getStudentById(studentId);
        if(student != null) {
            model.addAttribute("student", student);
            return "secured/student/update";
        }
        return "redirect:/secured/student/list";
    }

        @PostMapping(value = {"/secured/student/update/{studentId}"})
    public String updateStudent(@Valid @ModelAttribute("studentId") Student student,
                                  BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("student", student);
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "secured/student/update";
        }
       var studentSaved= studentService.updateStudent(student);
            System.out.println(studentSaved);
        return "redirect:/secured/student/list";
    }

    @GetMapping(value = {"/secured/student/searchview"})
    public String displaySearch(){
        return "secured/searched/search";
    }


    @GetMapping(value = {"/secured/student/search"})
    public ModelAndView searchStudent(@RequestParam String searchString) {
        var modelAndView = new ModelAndView();
        var student = studentService.findStudentByName(searchString);
        modelAndView.addObject("student", student);
        modelAndView.addObject("searchString", searchString);
        modelAndView.setViewName("secured/searched/search");
        return modelAndView;
    }
}
