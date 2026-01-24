package com.clopezrubio.springboot.cruddemo.dao;

import com.clopezrubio.springboot.cruddemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//@RepositoryRestResource(path="members")

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    // no need to write any code, we get all the CRUD methods for free

}
