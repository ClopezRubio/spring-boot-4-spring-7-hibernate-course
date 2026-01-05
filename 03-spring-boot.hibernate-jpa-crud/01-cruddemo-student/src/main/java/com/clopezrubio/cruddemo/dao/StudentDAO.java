package com.clopezrubio.cruddemo.dao;

import com.clopezrubio.cruddemo.entity.Student;

import java.util.List;

public interface StudentDAO {

    void save(Student student);

    Student findByID(Integer id);

    List<Student> findAll();

    List<Student> findByLastName(String lastName);
}
