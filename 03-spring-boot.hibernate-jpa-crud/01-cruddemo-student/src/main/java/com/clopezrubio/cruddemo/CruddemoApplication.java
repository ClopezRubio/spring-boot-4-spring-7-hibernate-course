package com.clopezrubio.cruddemo;

import com.clopezrubio.cruddemo.dao.StudentDAO;
import com.clopezrubio.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

    @Bean
    public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
        return runner -> {
            //createStudent(studentDAO);
            createMultipleStudents(studentDAO);
            //readStudent(studentDAO);
            //queryForStudents(studentDAO);
            //queryForStudentsByLastName(studentDAO);
            //updateStudent(studentDAO);
            //deleteStudent(studentDAO);
            //deleteAllStudents(studentDAO);
        };
    }

    private void deleteAllStudents(StudentDAO studentDAO) {

        System.out.println("Deleting all students...");
        int numRowsDeleted = studentDAO.deleteAll();
        System.out.println("Deleted " + numRowsDeleted + " students.");

    }

    private void deleteStudent(StudentDAO studentDAO) {

        int studentId = 3;
        System.out.println("Deleting student with id: " + studentId);
        studentDAO.delete(studentId);

    }

    private void updateStudent(StudentDAO studentDAO) {

        // retrieve student based on the id

        int studentId = 1;
        System.out.println("Getting student with id 1: " + studentDAO.findByID(studentId));
        Student student = studentDAO.findByID(studentId);

        // change first name

        System.out.println("Updating student with id 1");
        student.setFirstName("John");

        // update student

        studentDAO.update(student);

        // display updated student

        System.out.println("Updated student with id 1: " + student);

    }

    private void queryForStudentsByLastName(StudentDAO studentDAO) {

        // gest a list of students

        List<Student> listOfStudents = studentDAO.findByLastName(new Scanner(System.in).nextLine());

        // display list of students

        for (Student student : listOfStudents) {
            System.out.println(student);
        }
    }

    private void queryForStudents(StudentDAO studentDAO) {

        // get a list of students

        List<Student> students = studentDAO.findAll();

        // display the list

        for (Student student : students) {
            System.out.println(student);
        }

    }

    private void createMultipleStudents(StudentDAO studentDAO) {

        // creating multiple students

        System.out.println("Creating 3 new student");
        Student student1 = new Student("John", "Doe", "john@clopezrubio.com");
        Student student2 = new Student("Mary", "Public", "mary@clopezrubio.com");
        Student student3 = new Student("Bonita", "Applebum", "bonita@clopezrubio.com");

        // save the student objects

        System.out.println("Creating 3 new students");
        studentDAO.save(student1);
        studentDAO.save(student2);
        studentDAO.save(student3);

        System.out.println("Created 3 new students with ids: " + student1.getId() + ", " + student2.getId() + ", " + student3.getId());

    }

    private void createStudent(StudentDAO studentDAO) {

        // create the student object

        System.out.println("Creating new student");
        Student student = new Student("Paul", "Doe", "paul@clopezrubio.com");

        // save the student object

        studentDAO.save(student);

        // display the id of the saved student object

        System.out.println("Student created with id: "  + student.getId());

    }

    private void readStudent(StudentDAO studentDAO) {

        // create a student object

        System.out.println("Creating new student");
        Student tempStudent = new Student("Duffy", "Duck", "duffy@clopezrubio.com");

        // save the student

        System.out.println("Saving student");
        studentDAO.save(tempStudent);

        // display id of the student

        System.out.println("Student created with id: " + tempStudent.getId());

        // retrieve student based on the id (primary key)

        System.out.println("Retrieving student with id: " + tempStudent.getId());
        Student myStudent = studentDAO.findByID(tempStudent.getId());

        // display student

        System.out.println("Found the student: " + myStudent);
    }
}
