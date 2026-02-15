package com.clopezrubio.cruddemo;

import com.clopezrubio.cruddemo.dao.AppDAO;
import com.clopezrubio.cruddemo.entity.Course;
import com.clopezrubio.cruddemo.entity.Instructor;
import com.clopezrubio.cruddemo.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

    @Bean
    public CommandLineRunner commandLineRunner(AppDAO appDAO) {
        return runner -> {
            //createInstructor(appDAO);

            //findInstructor(appDAO);

            //deleteInstructor(appDAO);

            //findInstructorDetail(appDAO);

            //deleteInstructorDetail(appDAO);

            //createInstructorWithCourses(appDAO);

            //findInstructorWithCourses(appDAO);

            //findCoursesForInstructor(appDAO);

            //findInstructorWithCoursesJoinFetch(appDAO);

            //updateInstructor(appDAO);

            //updateCourse(appDAO);

            deleteCourseById(appDAO);
        };
    }

    private void deleteCourseById(AppDAO appDAO) {

        int theId = 10;

        System.out.println("Deleting course by id " + theId);
        appDAO.deleteCourseById(theId);

        System.out.println("Done!");
    }


    private void updateCourse(AppDAO appDAO) {

        int theId = 10;

        System.out.println("Finding course with id " + theId);
        Course theCourse = appDAO.findCourseById(theId);

        // updating the course
        System.out.println("Updating course with id " + theId);
        theCourse.setTitle("Enjoy the Simple Things");

        appDAO.update(theCourse);

        System.out.println("Done!");
    }

    private void updateInstructor(AppDAO appDAO) {

        int theId = 1;

        System.out.println("Updating Instructor with id " + theId);
        Instructor tempInstructor = appDAO.findInstructorById(theId);

        // updating the instructor
        System.out.println("Updating instructor with id " + theId);
        tempInstructor.setLastName("TESTER");

        appDAO.update(tempInstructor);

        System.out.println("Done!");
    }

    private void findInstructorWithCoursesJoinFetch(AppDAO appDAO) {

        int theId = 1;

        System.out.println("Finding Courses for Instructor with id " + theId);

        // Find the instructor
        Instructor tempInstructor = appDAO.findInstructorByIdJoinFetch(theId);

        System.out.println("Instructor: " +  tempInstructor);
        System.out.println("The associated courses: " + tempInstructor.getCourses());

        System.out.println("Done!");

    }

    private void findCoursesForInstructor(AppDAO appDAO) {

        int theId = 1;

        System.out.println("Finding Courses for Instructor with id " + theId);
        Instructor tempInstructor = appDAO.findInstructorById(theId);
        System.out.println("Found Instructor with given id " + tempInstructor);

        // find courses for instructor ( since it's lazy loading )
        System.out.println("Finding Courses for Instructor with id " + theId);
        List<Course> courses =  appDAO.findCoursesByInstructorId(theId);

        // associate the objects
        tempInstructor.setCourses(courses);

        System.out.println("The associated courses: " + tempInstructor.getCourses());
        System.out.println("Done!");
    }

    private void findInstructorWithCourses(AppDAO appDAO) {

        int theId = 1;

        System.out.println("Finding instructor with id " + theId);

        Instructor tempInstructor = appDAO.findInstructorById(theId);

        System.out.println("Found instructor with given id " + tempInstructor);
        System.out.println("The associated courses: "  + tempInstructor.getCourses());
        System.out.println("Done!");

    }

    private void createInstructorWithCourses(AppDAO appDAO) {

        // create the instructor
        Instructor tempInstructor = new Instructor("Susan", "Public", "susan@blablabla.com");

        // create the Instructor Detail
        InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.youtube.com", "Video Games");

        // associate the objects
        tempInstructor.setInstructorDetail(tempInstructorDetail);

        // create some courses

        Course course1 = new Course("Air Guitar - The Ultimate Guide");
        Course course2 = new Course("The Pinball Masterclass");

        // add courses to the instructor
        // NOTE: this will also save the courses because of CascadeType.PERSIST

        tempInstructor.add(course1);
        tempInstructor.add(course2);

        // saving instructor to the database

        System.out.println("Saving Instructor: " +  tempInstructor);
        System.out.println("the Courses: " + tempInstructor.getCourses());

        appDAO.save(tempInstructor);

        System.out.println("Done!");

    }

    private void deleteInstructorDetail(AppDAO appDAO) {
        int theId = 4;

        System.out.println("Deleting Instructor Detail with id: " + theId);

        appDAO.deleteInstructorDetailById(theId);

        System.out.println("Deleted Instructor Detail with id: " + theId);
    }

    private void findInstructorDetail(AppDAO appDAO) {
        int theId = 1;

        // get the instructor detail object
        InstructorDetail tempInstructorDetail = appDAO.findInstructorDetailById(theId);

        // print the instructor detail
        System.out.println("Instructor Detail: " + tempInstructorDetail);

        // print the associated instructor
        System.out.println("Associated Instructor: " +  tempInstructorDetail.getInstructor());

    }

    private void deleteInstructor(AppDAO appDAO) {
        int theID = 1;

        System.out.println("Deleting Instructor with id: " + theID);

        appDAO.deleteInstructorById(theID);

        System.out.println("Deleted Instructor with id: " + theID);

    }

    private void findInstructor(AppDAO appDAO) {
        int theID = 3;
        System.out.println("Finding instructor by ID " + theID);

        Instructor tempInstructor = appDAO.findInstructorById(theID);

        System.out.println("Instructor: " + tempInstructor);
        System.out.println("the associated details: " +  tempInstructor.getInstructorDetail());
    }

    private void createInstructor(AppDAO appDAO) {

        /*
        // create the instructor
        Instructor tempInstructor = new Instructor("Carlos", "Lopez", "clopezrubio@blablabla.com");

        // create the Instructor Detail
        InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.luv2code.com/youtube", "Coding");
        */

        // create the instructor
        Instructor tempInstructor = new Instructor("Madhu", "Patel", "madhu@blablabla.com");

        // create the Instructor Detail
        InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.luv2code.com/youtube", "Guitar");

        // associate the objects
        tempInstructor.setInstructorDetail(tempInstructorDetail);

        // save the instructor
        // NOTE: this will also save the details object because of CascadeType.ALL
        System.out.println("Saving Instrcutor: " +  tempInstructor);
        appDAO.save(tempInstructor);

    }

}
