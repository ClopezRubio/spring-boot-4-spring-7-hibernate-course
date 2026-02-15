package com.clopezrubio.cruddemo.dao;

import com.clopezrubio.cruddemo.entity.Course;
import com.clopezrubio.cruddemo.entity.Instructor;
import com.clopezrubio.cruddemo.entity.InstructorDetail;
import com.clopezrubio.cruddemo.entity.Review;

import java.util.List;

public interface AppDAO {

    void save(Instructor instructor);

    Instructor findInstructorById(int theId);

    void deleteInstructorById(int theId);

    InstructorDetail findInstructorDetailById(int theId);

    void deleteInstructorDetailById(int theId);

    List<Course> findCoursesByInstructorId(int theId);

    Instructor findInstructorByIdJoinFetch(int theId);

    void update(Instructor tempInstructor);

    void update(Course tempCourse);

    Course findCourseById(int theId);

    void deleteCourseById(int theId);

    void save(Course tempCourse);

    Course findCourseAndReviewsByCourseId(int theId);

}
