package com.clopezrubio.cruddemo.dao;

import com.clopezrubio.cruddemo.entity.Course;
import com.clopezrubio.cruddemo.entity.Instructor;
import com.clopezrubio.cruddemo.entity.InstructorDetail;
import com.clopezrubio.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AppDAOImpl implements AppDAO {

    // define field for Entity Manager

    private EntityManager entityManager;

    // inject Entity Manager using constructor injection

    @Autowired
    public AppDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Instructor theInstructor){
        entityManager.persist(theInstructor);
    }

    @Override
    public Instructor findInstructorById(int theId) {
        return entityManager.find(Instructor.class, theId);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int theId) {

        // retrieve the Instructor
        Instructor tempInstructor = entityManager.find(Instructor.class, theId);

        // get the courses
        List<Course> tempCourses = tempInstructor.getCourses();

        // break association of all courses for the instructor
        for(Course tempCourse : tempCourses){
            tempCourse.setInstructor(null);
        }

        // delete the instructor
        entityManager.remove(tempInstructor);
    }

    @Override
    public InstructorDetail findInstructorDetailById(int theId) {
        return entityManager.find(InstructorDetail.class, theId);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int theId) {

        // retrieve instructor detail
        InstructorDetail tempInstructorDetail = entityManager.find(InstructorDetail.class, theId);

        // remove the associated object reference
        // break bidirectional link
        tempInstructorDetail.getInstructor().setInstructorDetail(null);

        // delete the instructor detail
        entityManager.remove(tempInstructorDetail);
    }

    @Override
    public List<Course>  findCoursesByInstructorId(int theId) {

        // create query
        TypedQuery<Course> query = entityManager.createQuery(
                "from Course where instructor.id = :data" , Course.class);

        query.setParameter("data", theId);

        // execute the query
        List<Course> courses = query.getResultList();

        return courses;
    }

    @Override
    public Instructor findInstructorByIdJoinFetch(int theId) {

        // create query
        TypedQuery<Instructor> query = entityManager.createQuery(
                "select i from Instructor i JOIN FETCH i.courses JOIN FETCH i.instructorDetail where i.id = :data" , Instructor.class);

        query.setParameter("data", theId);

        // execute query

        Instructor tempInstructor = query.getSingleResult();

        return tempInstructor;
    }

    @Override
    @Transactional
    public void update(Instructor theInstructor) {
        entityManager.merge(theInstructor);
    }

    @Override
    public Course findCourseById(int theId) {

        Course tempCourse = entityManager.find(Course.class, theId);

        return tempCourse;

    }

    @Override
    @Transactional
    public void update(Course theCourse) {
        entityManager.merge(theCourse);
    }

    @Override
    @Transactional
    public void deleteCourseById(int theId) {

        Course tempCourse = entityManager.find(Course.class, theId);

        entityManager.remove(tempCourse);
    }

    @Override
    @Transactional
    public void save(Course theCourse) {
        entityManager.persist(theCourse);
    }

    @Override
    public Course findCourseAndReviewsByCourseId(int theId) {

        // create query
        TypedQuery<Course> query = entityManager.createQuery(
                "select c from Course c "
                + "JOIN FETCH c.reviews "
                + "where c.id = :data" , Course.class);

        query.setParameter("data", theId);

        // execute the query
        Course tempCourse = query.getSingleResult();

        return tempCourse;
    }

    @Override
    public Course findCourseAndStudentsByCourseId(int theId) {

        // create query
        TypedQuery<Course> query = entityManager.createQuery(
                "select c from Course c "
                        + "JOIN FETCH c.students "
                        + "where c.id = :data" , Course.class);

        query.setParameter("data", theId);

        // execute query
        Course tempCourse = query.getSingleResult();

        return tempCourse;
    }

    @Override
    public Student findStudentAndCoursesByStudentId(int theId) {

        // create query
        TypedQuery<Student> query = entityManager.createQuery(
                "select s from Student s "
                +"JOIN FETCH s.courses "
                +"where s.id = :data" , Student.class);

        query.setParameter("data", theId);

        // execute query
        Student tempStudent = query.getSingleResult();

        return tempStudent;
    }

    @Override
    @Transactional
    public void update(Student theStudent) {
        entityManager.merge(theStudent);
    }

    @Override
    @Transactional
    public void deleteStudentById(int theId) {

        // get the student
        Student tempStudent = entityManager.find(Student.class, theId);

        // get the courses
        List<Course> courses = tempStudent.getCourses();

        // break association between student and courses
        for(Course course : courses){
            course.getStudents().remove(tempStudent);
        }

        // remove the student
        entityManager.remove(tempStudent);
    }
}
