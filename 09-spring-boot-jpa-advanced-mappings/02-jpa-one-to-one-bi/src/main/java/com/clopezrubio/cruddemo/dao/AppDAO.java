package com.clopezrubio.cruddemo.dao;

import com.clopezrubio.cruddemo.entity.Instructor;
import com.clopezrubio.cruddemo.entity.InstructorDetail;

public interface AppDAO {

    void save(Instructor instructor);

    Instructor findInstructorById(int theId);

    void deleteInstructorById(int theId);

    InstructorDetail findInstructorDetailById(int theId);

    void deleteInstructorDetailById(int theId);
}
