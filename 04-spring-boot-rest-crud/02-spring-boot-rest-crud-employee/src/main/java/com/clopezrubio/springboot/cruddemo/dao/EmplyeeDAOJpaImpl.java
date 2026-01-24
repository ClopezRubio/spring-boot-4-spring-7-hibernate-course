package com.clopezrubio.springboot.cruddemo.dao;

import com.clopezrubio.springboot.cruddemo.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmplyeeDAOJpaImpl implements EmployeeDAO {

    // define fields for EntityManager

    private EntityManager entityManager;

    // set up constructor injection

    @Autowired
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> findAll() {

        // create a Query

        TypedQuery<Employee> theQuery = entityManager.createQuery("from Employee", Employee.class);

        // execute query and get result list

        List<Employee> employees = theQuery.getResultList();

        // return the results
        return employees;
    }

    @Override
    public Employee findById(int id) {
        // get employee
        Employee employee = entityManager.find(Employee.class, id);
        // return employee
        return employee;
    }

    @Override
    public Employee save(Employee employee) {
        // save employee
        Employee dbEmployee = entityManager.merge(employee);
        // return the saved employee
        return dbEmployee;
    }

    @Override
    public void deleteById(int id) {
        // find employee by id
        Employee employee = entityManager.find(Employee.class, id);
        // delete employee
        entityManager.remove(employee);
    }

}
