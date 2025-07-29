package com.example.demo.Controller;

import com.example.demo.module.Student;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/Load")
public class HelloControllerSpringSecurityFirst {
    private List<Student> std = new ArrayList<>();
    //KafkaTemplate

    public HelloControllerSpringSecurityFirst() {

        std.add(new Student(1,"Manoj","AI"));
        std.add(new Student(2,"Raj","ML"));
    }

    @GetMapping("/Home")
    public String Home(HttpServletRequest request)
    {
        return "Hello Home" + request.getSession().getId();
    }

    @GetMapping("/viewStudents")
    public List<Student> ViewStudentInfo()
    {
        return (List<Student>) std;
    }

    @GetMapping("/csrf-token")
    public ResponseEntity<?> getCSRFToken(CsrfToken token)
    {
        return  ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(token);
    }
    @PostMapping("/addStudents")
    public String addStudent(@RequestBody List<Student> stdadd)
    {
        for(Student stdadd1 : stdadd)
          std.add(stdadd1);
        return "Succesfully Inserted" ;
    }



}
