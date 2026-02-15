package com.clopezrubio.cruddemo;

import com.clopezrubio.cruddemo.dao.AppDAO;
import com.clopezrubio.cruddemo.entity.Instructor;
import com.clopezrubio.cruddemo.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

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
            deleteInstructor(appDAO);
        };
    }

    private void deleteInstructor(AppDAO appDAO) {
        int theID = 3;

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
