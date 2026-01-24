package com.clopezrubio.demo.rest;

import com.clopezrubio.demo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    private List<Student> theStudents;

    // define @PostConstruct to load the student data ONLY ONCE

    @PostConstruct
    public void loadData(){
        theStudents = new ArrayList<>();

        theStudents.add(new Student("Poornima", "Patel"));
        theStudents.add(new Student("Daniel", "Robin"));
        theStudents.add(new Student("Pedro", "Macareno"));
    }

    // define endpoint for ("/students") to return a list of students

    @GetMapping("/students")
    public List<Student> getStudents(){
        return theStudents;
    }

    // define endpoint for "/students/{studentId}" - return student at selected index

    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId){

        //check the studentId against the list size

        if(studentId < 0 || studentId >= theStudents.size()){
            throw new StudentNotFoundException("Student id not found: " + studentId);
        }

        return theStudents.get(studentId);
    }

}


