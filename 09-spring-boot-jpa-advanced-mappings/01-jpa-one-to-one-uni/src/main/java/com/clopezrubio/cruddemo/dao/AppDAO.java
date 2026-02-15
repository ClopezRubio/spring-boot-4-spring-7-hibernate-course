package com.clopezrubio.cruddemo.dao;

import com.clopezrubio.cruddemo.entity.Instructor;

public interface AppDAO {

    void save(Instructor instructor);

    Instructor findInstructorById(int theId);

    void deleteInstructorById(int theId);
}
