package com.clopezrubio.cruddemo.dao;

import com.clopezrubio.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO {

    // define field for entity manager

    private EntityManager entityManager;

    // inject entity manager using constructor injection

    @Autowired
    public StudentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // implement the save method

    @Override
    @Transactional
    public void save(Student student) {
        entityManager.persist(student);
    }

    @Override
    public Student findByID(Integer id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public List<Student> findAll(){

        // create query

        TypedQuery<Student> theQuery = entityManager.createQuery("FROM Student ORDER BY lastName", Student.class);

        // return query results

        return theQuery.getResultList();
    }

    @Override
    public List<Student> findByLastName(String lastName) {

        // create query

        TypedQuery<Student>  theQuery = entityManager.createQuery("FROM Student WHERE lastName =: theData", Student.class);

        // set query parameter

        theQuery.setParameter("theData", lastName);

        // return query result

        return theQuery.getResultList();

    }

    @Override
    @Transactional
    public void update(Student student) {
        entityManager.merge(student);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        entityManager.remove(entityManager.find(Student.class, id));
    }

    @Override
    @Transactional
    public int deleteAll() {
        int numberOfRowsDeleted = entityManager.createQuery("DELETE FROM Student").executeUpdate();
        return numberOfRowsDeleted;
    }
}
